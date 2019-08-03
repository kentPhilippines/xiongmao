package com.payProject.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p>可以用来获取配置文件,将其加载为全局常量</p>
 * <li>该类在项目启动的时候加载</li>
 * <li>已知该类可以加载数据,可以作为主动注入容器的类</li>
 * @author K
 *
 */
@Configuration
public class EnvironmentConfig implements EnvironmentAware{
	Logger log = LoggerFactory.getLogger(EnvironmentConfig.class);
	@Override
	public void setEnvironment(Environment environment) {
		// 获取操作系统信息
		log.info("操作系统的名称：" + environment.getProperty("os.name"));
		log.info("操作系统的构架：" + environment.getProperty("os.arch"));
		log.info("操作系统的版本：" + environment.getProperty("os.version"));
		// 获取配置信息
		String appUser = environment.getProperty("spring.datasource.url");
		log.info("配置信息获取：" + appUser);
	}
}
