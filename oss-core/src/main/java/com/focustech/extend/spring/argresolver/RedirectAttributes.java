package com.focustech.extend.spring.argresolver;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * *
 * @author lihaijun
 *
 */
public class RedirectAttributes {

	private HttpServletRequest request;


	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void addFlashAttribute(String name, Object value){
		request.getSession().setAttribute(name, value);
	}
	public void addFlashAttribute(Object value){
		request.getSession().setAttribute("message", value);
	}
	/**
	 *
	 * *
	 * @param name
	 * @return
	 */
	public Object getFlashAttribute(String name){
		return request.getSession().getAttribute(name);
	}
}
