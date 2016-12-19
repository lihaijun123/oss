package com.focustech.extend.spring.xmlrpc.server;

import java.lang.reflect.Method;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.common.TypeConverterFactory;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping;

/**
 * <li></li>
 *
 * @author yangpeng 2008-8-1 上午11:47:37
 */
public class SpringReflectiveXmlRpcMetaDataHandler extends SpringXmlRpcHandler {
    private final String[][] signatures;
    private final String methodHelp;

    /**
     * @param mapping
     * @param typeConverterFactory
     * @param bean
     * @param methods
     */
    public SpringReflectiveXmlRpcMetaDataHandler(AbstractReflectiveHandlerMapping mapping,
            TypeConverterFactory typeConverterFactory, Object bean, Method[] methods, String[][] pSignatures,
            String pMethodHelp) {
        super(mapping, typeConverterFactory, bean, methods);
        signatures = pSignatures;
        methodHelp = pMethodHelp;
    }

    public String[][] getSignatures() throws XmlRpcException {
        return signatures;
    }

    public String getMethodHelp() throws XmlRpcException {
        return methodHelp;
    }
}
