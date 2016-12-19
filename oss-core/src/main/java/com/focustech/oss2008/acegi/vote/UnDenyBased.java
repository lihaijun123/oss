package com.focustech.oss2008.acegi.vote;

import java.util.Iterator;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.InsufficientAuthenticationException;
import org.acegisecurity.vote.AbstractAccessDecisionManager;
import org.acegisecurity.vote.AccessDecisionVoter;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


/**
 * <li>無人反對投票器</li>
 *
 * @author yangpeng 2008-7-11 上午09:07:58
 */
public class UnDenyBased extends AbstractAccessDecisionManager {
    /*
     * (non-Javadoc)
     * @see org.acegisecurity.AccessDecisionManager#decide(org.acegisecurity.Authentication, java.lang.Object,
     * org.acegisecurity.ConfigAttributeDefinition)
     */
    @SuppressWarnings("unchecked")
    public void decide(Authentication authentication, Object object, ConfigAttributeDefinition config)
            throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator iter = this.getDecisionVoters().iterator();
        int grant = 0;
        while (iter.hasNext()) {
            AccessDecisionVoter voter = (AccessDecisionVoter) iter.next();
            int result = voter.vote(authentication, object, config);
            switch (result) {
                case AccessDecisionVoter.ACCESS_GRANTED :
                    grant++;
                    break;
                case AccessDecisionVoter.ACCESS_DENIED :
                    throw new AccessDeniedException(MessageUtils
                            .getExceptionValue(ExceptionConstants.ACCESS_DENIED_EXCEPTION));
                default :
                    break;
            }
        }
        if (grant > 0) {
            return;
        }
        // To get this far, every AccessDecisionVoter abstained
        checkAllowIfAllAbstainDecisions();
    }
}
