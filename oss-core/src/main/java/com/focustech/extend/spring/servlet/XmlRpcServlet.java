package com.focustech.extend.spring.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <li>xml rpc servlet with spring beans</li>
 *
 * @author yangpeng 2008-8-11 上午11:55:30
 */
public class XmlRpcServlet extends HttpServlet {
    private static final long serialVersionUID = -7321115403819288870L;
    public static final String DEFAULT_XML_RPC_SERVIER_NAME = "xmlRpcServer";
    private String serverName = DEFAULT_XML_RPC_SERVIER_NAME;
    private XmlRpcServletServer server;

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        server.execute(req, resp);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig pConfig) throws ServletException {
        for (Enumeration en = pConfig.getInitParameterNames(); en.hasMoreElements();) {
            String name = (String) en.nextElement();
            String value = pConfig.getInitParameter(name);
            if (name.equals("serverName") && StringUtils.isNotEmpty(value)) {
                serverName = value;
            }
        }
        WebApplicationContext wac =
                WebApplicationContextUtils.getRequiredWebApplicationContext(pConfig.getServletContext());
        server = (XmlRpcServletServer) wac.getBean(serverName);
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
