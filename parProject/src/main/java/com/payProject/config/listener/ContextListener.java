package com.payProject.config.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payProject.system.shiro.ShiroConfig;
/**
 * <p>初始化数据字典和相应的配置文件</p>
 * 2019-07-30
 * @author K
 *
 */
@WebListener
public class ContextListener implements ServletContextListener{
	Logger log = LoggerFactory.getLogger(ContextListener.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("------SpringBootListener------:监听器发挥作用,上下文已启动,这里可以用来加载缓存数据,数据字典,初始化配置文件");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("------SpringBootListener------:监听器发挥作用,上下文已销毁,清除缓存,清空数据");
	}
}