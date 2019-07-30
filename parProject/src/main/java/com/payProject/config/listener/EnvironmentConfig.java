package com.payProject.config.listener;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
@Configuration
public class EnvironmentConfig implements EnvironmentAware{
	@Override
	public void setEnvironment(Environment environment) {
		// 获取操作系统信息
		System.out.println("操作系统的名称：" + environment.getProperty("os.name"));
		System.out.println("操作系统的构架：" + environment.getProperty("os.arch"));
		System.out.println("操作系统的版本：" + environment.getProperty("os.version"));
		// 获取配置信息
		String appUser = environment.getProperty("app.user");
		System.out.println(appUser);
	}
}
