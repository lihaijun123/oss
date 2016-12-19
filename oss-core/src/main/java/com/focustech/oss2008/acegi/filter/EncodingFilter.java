package com.focustech.oss2008.acegi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class EncodingFilter implements Filter {

	private static String formEncode;
	private static String ajaxEncode;
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestType = StringUtils.defaultString(httpRequest.getHeader("X-Requested-With"));
        if (requestType.equals("XMLHttpRequest")) {
            httpRequest.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("text/html; charset=UTF-8");
        }
        else {
            httpRequest.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("text/html; charset=UTF-8");
        }
        request.getParameter("PRODUCT_TYPE_NAME");
        chain.doFilter(request, response);
    }

    public void doFilter_bak(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
    ServletException {
    	final HttpServletRequest httpRequest = (HttpServletRequest) request;
    	final HttpServletResponse httpResponse = (HttpServletResponse) response;
    	String requestType = StringUtils.defaultString(httpRequest.getHeader("X-Requested-With"));
    	if(formEncode != null)
        {
    		if (requestType.equals("XMLHttpRequest")) {
    			httpRequest.setCharacterEncoding(ajaxEncode);
    			httpResponse.setContentType("text/html; charset=" + ajaxEncode);
    		}
    		else {
    			System.out.println(formEncode);
    			httpRequest.setCharacterEncoding(formEncode);
    			httpResponse.setContentType("text/html; charset=" + formEncode);
    		}
        }
    	chain.doFilter(request, response);
    }




	public void destroy() {
        // TODO Auto-generated method stub
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
}
