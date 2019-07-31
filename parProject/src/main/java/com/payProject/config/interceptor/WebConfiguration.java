package com.payProject.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*public class SessionConfiguration extends WebMvcConfigurerAdapter{      该方法以过时
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
	    }
}
*/
@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	 	@Autowired
	    private MyInterceptor  inteceptor;
	    //配置拦截的资源以及放行的资源
	 	/**
	 	 * addPathPatterns     拦截的资源
	 	 * excludePathPatterns 不拦截的资源
	 	 */
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	    	   System.out.println("----------------------------------------拦截器配置-------------------------------------拦截器--------------------------------------------------------------------------------------------------------");
	       registry.addInterceptor(inteceptor)
	               .addPathPatterns("/**").excludePathPatterns("/**/*.htm ")     
	               //放行静态资源
	               .excludePathPatterns("/img/**","/css/**","/fonts/**","/js/**");
	    }
	 
	    //配置静态资源的位置
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 
	    }
}