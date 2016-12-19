package com.focustech.oss2008.acegi.vote;

import java.util.Iterator;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;

import com.focustech.oss2008.model.OssAdminUser;

/**
 * <li>將個人ID也作為角色去判斷</li>
 *
 * @author yangpeng 2008-9-27 下午06:37:39
 */
public class RoleVoter extends org.acegisecurity.vote.RoleVoter {
    /*
     * (non-Javadoc)
     * @see org.acegisecurity.vote.RoleVoter#vote(org.acegisecurity.Authentication, java.lang.Object,
     * org.acegisecurity.ConfigAttributeDefinition)
     */
    @SuppressWarnings("unchecked")
    @Override
    public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
        int result = ACCESS_ABSTAIN;
        Iterator iter = config.getConfigAttributes();
        while (iter.hasNext()) {
            ConfigAttribute attribute = (ConfigAttribute) iter.next();
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;
                // Attempt to find a matching granted authority
                for (int i = 0; i < authentication.getAuthorities().length; i++) {
                    if (attribute.getAttribute().equals(authentication.getAuthorities()[i].getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
                // 判斷用戶
                String userId = ((OssAdminUser) authentication.getPrincipal()).getUserId();
                if (attribute.getAttribute().equals(userId)) {
                    return ACCESS_GRANTED;
                }
            }
        }
        return result;
    }
}
