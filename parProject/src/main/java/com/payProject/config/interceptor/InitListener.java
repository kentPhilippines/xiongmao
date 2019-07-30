package com.payProject.config.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * <p>初始化数据字典和相应的配置文件</p>
 * 2019-07-30
 * @author K
 *
 */
@WebListener
public class InitListener implements ServletContextListener{
	 	@Override
	    public void contextInitialized(ServletContextEvent servletContextEvent) {
	        //do something while contextInitialized
	 		 System.out.println("----------------------------------------初始化数据------------------------------ ----------------------------------------------------------------------------------------------------");
	 	       
	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent servletContextEvent) {
	        //do something while contextDestroyed
	    	 System.out.println("---------------------------------------初始化数据------------------------------------------------------------------------------------------------------");
	         
	    }
}