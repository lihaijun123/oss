package com.focustech.oss2008.acegi.ldap;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utilities related to LDAP functions.
 *
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2008-12-23 01:08:17 $
 * @since 3.0
 */
public final class LdapUtils {
    private static final Log logger = LogFactory.getLog(LdapUtils.class);

    private LdapUtils() {
        // private constructor so that no one can instantiate.
    }

    /**
     * Utility method to replace the placeholders in the filter with the proper values from the userName.
     *
     * @param filter
     * @param userName
     * @return the filtered string populated with the username
     */
    public static String getFilterWithValues(final String filter, final String userName) {
        final Map<String, String> properties = new HashMap<String, String>();
        final String[] userDomain;
        String newFilter = filter;
        properties.put("%u", userName.replace("\\", "\\\\"));
        userDomain = userName.split("@");
        properties.put("%U", userDomain[0]);
        if (userDomain.length > 1) {
            properties.put("%d", userDomain[1]);
            final String[] dcArray = userDomain[1].split("\\.");
            for (int i = 0; i < dcArray.length; i++) {
                properties.put("%" + (i + 1), dcArray[dcArray.length - 1 - i]);
            }
        }
        for (final String key : properties.keySet()) {
            final String value = properties.get(key);
            newFilter = newFilter.replaceAll(key, value);
        }
        return newFilter;
    }

    /**
     * Close the given context and ignore any thrown exception. This is useful for typical finally blocks in manual Ldap
     * statements.
     *
     * @param context the Ldap context to close
     */
    public static void closeContext(final DirContext context) {
        if (context != null) {
            try {
                context.close();
            }
            catch (NamingException ex) {
                logger.warn("Could not close context", ex);
            }
        }
    }
}
