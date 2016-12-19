package com.focustech.oss2008.acegi.ldap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.providers.ldap.LdapAuthenticator;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.NameClassPairCallbackHandler;
import org.springframework.ldap.core.SearchExecutor;
import org.springframework.util.Assert;

import com.focustech.common.utils.DateUtils;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.exception.acegi.LdapAccountExpiredException;
import com.focustech.oss2008.exception.acegi.LdapAuthenticationFailureException;
import com.focustech.oss2008.exception.acegi.NotLdapAuthenticateException;
import com.focustech.oss2008.exception.service.LdapCheckedException;
import com.focustech.oss2008.service.UserService;


/**
 * <li></li>
 *
 * @author yangpeng 2008-12-11 上午09:27:29
 */
public class OssBindAuthenticator implements LdapAuthenticator, LdapService, InitializingBean {
    /** LdapTemplate to execute ldap queries. */
    // @NotNull
    private LdapTemplate ldapTemplate;
    /** Instance of ContextSource */
    // @NotNull
    private AuthenticatedLdap contextSource;
    /** The filter path to the uid of the user. */
    // @NotNull
    private String filter;
    /** Whether the LdapTemplate should ignore partial results. */
    private boolean ignorePartialResultException = false;
    /** The default maximum number of results to return. */
    private static final int DEFAULT_MAX_NUMBER_OF_RESULTS = 1000;
    /** The default timeout. */
    private static final int DEFAULT_TIMEOUT = 1000;
    /** The search base to find the user under. */
    private String searchBase;
    /** The scope. */
    // @IsIn(
    // {SearchControls.OBJECT_SCOPE, SearchControls.ONELEVEL_SCOPE, SearchControls.SUBTREE_SCOPE})
    private int scope = SearchControls.SUBTREE_SCOPE;
    /** The maximum number of results to return. */
    private int maxNumberResults = DEFAULT_MAX_NUMBER_OF_RESULTS;
    /** The amount of time to wait. */
    private int timeout = DEFAULT_TIMEOUT;
    /** Boolean of whether multiple accounts are allowed. */
    private boolean allowMultipleAccounts;
    @Autowired
    private UserService userService;
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_OSS);
    public static final long LDAP_BASE_TIME = 11644473600000L;
    /** ldap 密碼距離過期的天數 */
    private int ldapRemainDay;
    /** ldap 密碼的有效期 */
    private int ldapEffectiveDay;

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(contextSource);
        Assert.notNull(filter);
        Assert.notNull(ldapTemplate);
        Assert.isTrue(this.filter.contains("%u"), "filter must contain %u");
        this.ldapTemplate.setIgnorePartialResultException(this.ignorePartialResultException);
    }

    public LdapUserDetails authenticate(String username, String password) {
        if (userService.isLoginWithLdap(username)) {
            boolean result = authenticateUsernamePasswordInternal(username, password);
            if (result) {
                return null;
            }
            else {
                throw new LdapAuthenticationFailureException("user not pass ldap auth.");
            }
        }
        else {
            String errorMessage = "user :" + username + " not use ldap authentication!";
            log.error(errorMessage);
            throw new NotLdapAuthenticateException(errorMessage);
        }
    }

    public long getLdapValidDay(final String username) throws LdapCheckedException {
        final List<Long> day = new ArrayList<Long>();
        final SearchControls searchControls = getSearchControls();
        final String base = this.searchBase;
        this.getLdapTemplate().search(new SearchExecutor() {
            public NamingEnumeration executeSearch(final DirContext context) throws NamingException {
                return context.search(base, LdapUtils.getFilterWithValues(getFilter(), username), searchControls);
            }
        }, new NameClassPairCallbackHandler() {
            public void handleNameClassPair(final NameClassPair nameClassPair) {
                Object attribute = null;
                String strAttribute = null;
                try {
                    attribute = ((SearchResult) nameClassPair).getAttributes().get("pwdlastset").get();
                    strAttribute = attribute == null ? "" : attribute.toString();
                    if (StringUtils.isNotEmpty(strAttribute)) {
                        long time = Long.parseLong(strAttribute);
                        day.add(countLdapValidDay(time));
                    }
                    else
                        throw new LdapCheckedException("pwdlastset is null!");
                }
                catch (NamingException e) {
                    throw new LdapCheckedException(e.getMessage(), e);
                }
                catch (NumberFormatException e) {
                    throw new LdapCheckedException("pwdlastset could not be formated,the string is: " + strAttribute, e);
                }
            }

            public long countLdapValidDay(long lastModifyTime) {
                Date lastModifyDate = new Date(lastModifyTime / 10000 - LDAP_BASE_TIME);
                return DateUtils.getIntervalDayWithoutTime(DateUtils.getCurDate(), DateUtils.getDateOfDay(
                        lastModifyDate, ldapEffectiveDay));
            }
        });
        return day.get(0);
    }

    protected boolean authenticateUsernamePasswordInternal(final String username, final String password)
            throws AuthenticationException {
        final List<String> cns = new ArrayList<String>();
        final SearchControls searchControls = getSearchControls();
        final String base = this.searchBase;
        this.getLdapTemplate().search(new SearchExecutor() {
            public NamingEnumeration executeSearch(final DirContext context) throws NamingException {
                return context.search(base, LdapUtils.getFilterWithValues(getFilter(), username), searchControls);
            }
        }, new NameClassPairCallbackHandler() {
            public void handleNameClassPair(final NameClassPair nameClassPair) {
                Object attribute = null;
                String strAttribute = null;
                try {
                    attribute = ((SearchResult) nameClassPair).getAttributes().get("pwdlastset").get();
                    strAttribute = attribute == null ? "" : attribute.toString();
                    if (StringUtils.isNotEmpty(strAttribute)) {
                        long time = Long.parseLong(strAttribute);
                        long day = countLdapValidDay(time);
                        if (day <= ldapRemainDay) {
                            throw new LdapAccountExpiredException(day, "ldap account is expired!");
                        }
                    }
                    else
                        throw new LdapAuthenticationFailureException("pwdlastset is null!");
                }
                catch (NamingException e) {
                    throw new LdapAuthenticationFailureException(e.getMessage(), e);
                }
                catch (NumberFormatException e) {
                    throw new LdapAuthenticationFailureException("pwdlastset could not be formated,the string is: "
                            + strAttribute, e);
                }
                cns.add(nameClassPair.getNameInNamespace());
            }

            public long countLdapValidDay(long lastModifyTime) {
                Date lastModifyDate = new Date(lastModifyTime / 10000 - LDAP_BASE_TIME);
                return DateUtils.getIntervalDayWithoutTime(DateUtils.getCurDate(), DateUtils.getDateOfDay(
                        lastModifyDate, ldapEffectiveDay));
            }
        });
        if (cns.isEmpty() || (cns.size() > 1 && !this.allowMultipleAccounts)) {
            return false;
        }
        for (final String dn : cns) {
            DirContext test = null;
            try {
                test = this.getContextSource().getDirContext(dn, password);
                if (test != null) {
                    return true;
                }
            }
            catch (final Exception e) {
                log.error(e);
            }
            finally {
                LdapUtils.closeContext(test);
            }
        }
        return false;
    }

    private final SearchControls getSearchControls() {
        final SearchControls constraints = new SearchControls();
        constraints.setSearchScope(this.scope);
        constraints.setReturningAttributes(null);
        constraints.setTimeLimit(this.timeout);
        constraints.setCountLimit(this.maxNumberResults);
        return constraints;
    }

    public LdapTemplate getLdapTemplate() {
        return ldapTemplate;
    }

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public AuthenticatedLdap getContextSource() {
        return contextSource;
    }

    public void setContextSource(AuthenticatedLdap contextSource) {
        this.contextSource = contextSource;
        this.ldapTemplate = new LdapTemplate(contextSource);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean isIgnorePartialResultException() {
        return ignorePartialResultException;
    }

    public void setIgnorePartialResultException(boolean ignorePartialResultException) {
        this.ignorePartialResultException = ignorePartialResultException;
    }

    public String getSearchBase() {
        return searchBase;
    }

    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public int getMaxNumberResults() {
        return maxNumberResults;
    }

    public void setMaxNumberResults(int maxNumberResults) {
        this.maxNumberResults = maxNumberResults;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isAllowMultipleAccounts() {
        return allowMultipleAccounts;
    }

    public void setAllowMultipleAccounts(boolean allowMultipleAccounts) {
        this.allowMultipleAccounts = allowMultipleAccounts;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public int getLdapRemainDay() {
        return ldapRemainDay;
    }

    public void setLdapRemainDay(int ldapRemainDay) {
        this.ldapRemainDay = ldapRemainDay;
    }

    public int getLdapEffectiveDay() {
        return ldapEffectiveDay;
    }

    public void setLdapEffectiveDay(int ldapEffectiveDay) {
        this.ldapEffectiveDay = ldapEffectiveDay;
    }
}
