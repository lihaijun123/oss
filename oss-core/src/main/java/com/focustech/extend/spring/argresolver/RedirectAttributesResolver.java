package com.focustech.extend.spring.argresolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
/***
 *
 *
 * @author lihaijun
 *
 */
public class RedirectAttributesResolver implements WebArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		if(methodParameter.getParameterType() != null && methodParameter.getParameterType().equals(RedirectAttributes.class)){
			HttpServletRequest req = null;
			Object nativeRequest = webRequest.getNativeRequest();
			if(nativeRequest instanceof HttpServletRequest){
				req = (HttpServletRequest)nativeRequest;
			}
			RedirectAttributes redirectAttributes = new RedirectAttributes();
            if(req != null){
            	redirectAttributes.setRequest(req);
            }
            return redirectAttributes;
        }
        return UNRESOLVED;
	}

}
