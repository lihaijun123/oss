package com.focustech.oss2008.acegi.ldap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.acegisecurity.AbstractAuthenticationManager;
import org.acegisecurity.AccountExpiredException;
import org.acegisecurity.AcegiMessageSource;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.CredentialsExpiredException;
import org.acegisecurity.DisabledException;
import org.acegisecurity.LockedException;
import org.acegisecurity.concurrent.ConcurrentLoginException;
import org.acegisecurity.concurrent.ConcurrentSessionController;
import org.acegisecurity.concurrent.NullConcurrentSessionController;
import org.acegisecurity.event.authentication.AbstractAuthenticationEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureBadCredentialsEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureConcurrentLoginEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureCredentialsExpiredEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureDisabledEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureExpiredEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureLockedEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureProviderNotFoundEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureProxyUntrustedEvent;
import org.acegisecurity.event.authentication.AuthenticationFailureServiceExceptionEvent;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.acegisecurity.providers.AbstractAuthenticationToken;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.ProviderNotFoundException;
import org.acegisecurity.providers.cas.ProxyUntrustedException;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.Assert;

import com.focustech.oss2008.exception.acegi.LdapAccountExpiredException;
import com.focustech.oss2008.exception.acegi.LdapAuthenticationFailureException;
import com.focustech.oss2008.exception.acegi.TooSimpleLdapPasswordException;

/**
 * <li></li>
 *
 * @author yangpeng 2008-12-12 下午01:55:55
 */
public class OssLdapProviderManager extends AbstractAuthenticationManager implements InitializingBean,
        ApplicationEventPublisherAware, MessageSourceAware {
    // ~ Static fields/initializers
    // =====================================================================================
    private static final Log logger = LogFactory.getLog(ProviderManager.class);
    private static final Properties DEFAULT_EXCEPTION_MAPPINGS = new Properties();
    // ~ Instance fields
    // ================================================================================================
    private ApplicationEventPublisher applicationEventPublisher;
    private ConcurrentSessionController sessionController = new NullConcurrentSessionController();
    private List providers;
    protected MessageSourceAccessor messages = AcegiMessageSource.getAccessor();
    private Properties exceptionMappings = new Properties();
    static {
        DEFAULT_EXCEPTION_MAPPINGS.put(AccountExpiredException.class.getName(), AuthenticationFailureExpiredEvent.class
                .getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(AuthenticationServiceException.class.getName(),
                AuthenticationFailureServiceExceptionEvent.class.getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(LockedException.class.getName(), AuthenticationFailureLockedEvent.class
                .getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(CredentialsExpiredException.class.getName(),
                AuthenticationFailureCredentialsExpiredEvent.class.getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(DisabledException.class.getName(), AuthenticationFailureDisabledEvent.class
                .getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(BadCredentialsException.class.getName(),
                AuthenticationFailureBadCredentialsEvent.class.getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(UsernameNotFoundException.class.getName(),
                AuthenticationFailureBadCredentialsEvent.class.getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(ConcurrentLoginException.class.getName(),
                AuthenticationFailureConcurrentLoginEvent.class.getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(ProviderNotFoundException.class.getName(),
                AuthenticationFailureProviderNotFoundEvent.class.getName());
        DEFAULT_EXCEPTION_MAPPINGS.put(ProxyUntrustedException.class.getName(),
                AuthenticationFailureProxyUntrustedEvent.class.getName());
    }

    public OssLdapProviderManager() {
        exceptionMappings.putAll(DEFAULT_EXCEPTION_MAPPINGS);
    }

    // ~ Methods
    // ========================================================================================================
    public void afterPropertiesSet() throws Exception {
        checkIfValidList(this.providers);
        Assert.notNull(this.messages, "A message source must be set");
        doAddExtraDefaultExceptionMappings(exceptionMappings);
    }

    private void checkIfValidList(List listToCheck) {
        if ((listToCheck == null) || (listToCheck.size() == 0)) {
            throw new IllegalArgumentException("A list of AuthenticationManagers is required");
        }
    }

    /**
     * Provided so subclasses can add extra exception mappings during startup if no exception mappings are injected by
     * the IoC container.
     *
     * @param exceptionMappings the properties object, which already has entries in it
     */
    protected void doAddExtraDefaultExceptionMappings(Properties exceptionMappings) {
    }

    /**
     * Attempts to authenticate the passed {@link Authentication} object.
     * <p>
     * The list of {@link AuthenticationProvider}s will be successively tried until an
     * <code>AuthenticationProvider</code> indicates it is capable of authenticating the type of
     * <code>Authentication</code> object passed. Authentication will then be attempted with that
     * <code>AuthenticationProvider</code>.
     * </p>
     * <p>
     * If more than one <code>AuthenticationProvider</code> supports the passed <code>Authentication</code> object, only
     * the first <code>AuthenticationProvider</code> tried will determine the result. No subsequent
     * <code>AuthenticationProvider</code>s will be tried.
     * </p>
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials.
     * @throws AuthenticationException if authentication fails.
     */
    public Authentication doAuthentication(Authentication authentication) throws AuthenticationException {
        Iterator iter = providers.iterator();
        Class toTest = authentication.getClass();
        AuthenticationException lastException = null;
        while (iter.hasNext()) {
            AuthenticationProvider provider = (AuthenticationProvider) iter.next();
            if (provider.supports(toTest)) {
                logger.debug("Authentication attempt using " + provider.getClass().getName());
                Authentication result = null;
                try {
                    result = provider.authenticate(authentication);
                    copyDetails(authentication, result);
                    sessionController.checkAuthenticationAllowed(result);
                }
                catch (LdapAuthenticationFailureException e) {
                    // LDAP驗證失敗，則不做其他驗證
                    lastException = e;
                    result = null;
                    break;
                }
                catch (TooSimpleLdapPasswordException e) {
                    // 使用簡單LDAP密碼
                    lastException = e;
                    result = null;
                    break;
                }
                catch (LdapAccountExpiredException e) {
                    // LDAP帳戶過期
                    lastException = e;
                    result = null;
                    break;
                }
                catch (AuthenticationException ae) {
                    // 有可能的異常是不需要ldap驗證，則使用jdbc驗證
                    lastException = ae;
                    result = null;
                }
                if (result != null) {
                    sessionController.registerSuccessfulAuthentication(result);
                    publishEvent(new AuthenticationSuccessEvent(result));
                    return result;
                }
            }
        }
        if (lastException == null) {
            lastException =
                    new ProviderNotFoundException(messages.getMessage("ProviderManager.providerNotFound",
                            new Object[]{toTest.getName()}, "No AuthenticationProvider found for {0}"));
        }
        // Publish the event
        String className = exceptionMappings.getProperty(lastException.getClass().getName());
        AbstractAuthenticationEvent event = null;
        if (className != null) {
            try {
                Class clazz = getClass().getClassLoader().loadClass(className);
                Constructor constructor =
                        clazz.getConstructor(new Class[]{Authentication.class, AuthenticationException.class});
                Object obj = constructor.newInstance(new Object[]{authentication, lastException});
                Assert.isInstanceOf(AbstractAuthenticationEvent.class, obj, "Must be an AbstractAuthenticationEvent");
                event = (AbstractAuthenticationEvent) obj;
            }
            catch (ClassNotFoundException ignored) {
            }
            catch (NoSuchMethodException ignored) {
            }
            catch (IllegalAccessException ignored) {
            }
            catch (InstantiationException ignored) {
            }
            catch (InvocationTargetException ignored) {
            }
        }
        if (event != null) {
            publishEvent(event);
        }
        else {
            if (logger.isDebugEnabled()) {
                logger.debug("No event was found for the exception " + lastException.getClass().getName());
            }
        }
        // Throw the exception
        throw lastException;
    }

    /**
     * Copies the authentication details from a source Authentication object to a destination one, provided the latter
     * does not already have one set.
     *
     * @param source source authentication
     * @param dest the destination authentication object
     */
    private void copyDetails(Authentication source, Authentication dest) {
        if ((dest instanceof AbstractAuthenticationToken) && (dest.getDetails() == null)) {
            AbstractAuthenticationToken token = (AbstractAuthenticationToken) dest;
            token.setDetails(source.getDetails());
        }
    }

    public List getProviders() {
        return this.providers;
    }

    /**
     * The configured {@link ConcurrentSessionController} is returned or the {@link NullConcurrentSessionController} if
     * a specific one has not been set.
     *
     * @return {@link ConcurrentSessionController} instance
     */
    public ConcurrentSessionController getSessionController() {
        return sessionController;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    /**
     * Sets the {@link AuthenticationProvider} objects to be used for authentication.
     *
     * @param newList
     * @throws IllegalArgumentException DOCUMENT ME!
     */
    public void setProviders(List newList) {
        checkIfValidList(newList);
        Iterator iter = newList.iterator();
        while (iter.hasNext()) {
            Object currentObject = iter.next();
            Assert.isInstanceOf(AuthenticationProvider.class, currentObject,
                    "Can only provide AuthenticationProvider instances");
        }
        this.providers = newList;
    }

    /**
     * Set the {@link ConcurrentSessionController} to be used for limiting user's sessions. The
     * {@link NullConcurrentSessionController} is used by default
     *
     * @param sessionController {@link ConcurrentSessionController}
     */
    public void setSessionController(ConcurrentSessionController sessionController) {
        this.sessionController = sessionController;
    }

    private void publishEvent(ApplicationEvent event) {
        if (applicationEventPublisher != null) {
            applicationEventPublisher.publishEvent(event);
        }
    }
}
