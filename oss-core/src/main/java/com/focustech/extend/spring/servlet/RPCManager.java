package com.focustech.extend.spring.servlet;

import com.focustech.common.utils.SpringContextUtil;
import com.focustech.oss2008.service.OrderService;



/**
 * RPCManager.java
 *
 * @author sunjingyu
 */
//@Service
public class RPCManager {


	private static OrderService orderService = (OrderService) SpringContextUtil.getBean("orderServiceImpl");
    public String sayHello(String s) {
        System.out.println("Server: Hello " + s);
        return "Hello " + s;
    }

    public Long synchroContract(Object[] objs)
    {
    	return orderService.synchroContract(objs);
    }

    public void payment(Object[] objs)
    {
    	orderService.payment(objs);
    }


}
