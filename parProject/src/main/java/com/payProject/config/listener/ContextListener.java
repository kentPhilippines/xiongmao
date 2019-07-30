package com.payProject.config.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("上下文启动");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("上下文销毁");
	}
}