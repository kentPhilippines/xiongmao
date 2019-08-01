package com.payProject.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.payProject.system.annotation.LoginRequired;
import com.payProject.system.entity.User;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
@Component
public class MyInterceptor implements HandlerInterceptor {
	Logger log = LoggerFactory.getLogger(MyInterceptor.class);

	@Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("----------------------------------------这里可以做登录拦截,和注解拦截,权限拦截-------------------------------------拦截器--------------------------------------------------------------------------------------------------------");
		 //1.判断是否存在注解
        if(!(handler instanceof HandlerMethod)){
        	log.info("不是HandlerMethod类型，则无需检查");
            return true;
        }
        HandlerMethod method = (HandlerMethod)handler;
        boolean hasLoginAnnotation=method.getMethod().isAnnotationPresent(LoginRequired.class);
        if(!hasLoginAnnotation){
            //不存在LoginRequired注解，则直接通过
        	log.info("不存在LoginRequired注解，则直接通过");
            return true;
        }
        LoginRequired loginRequired=method.getMethod().getAnnotation(LoginRequired.class);
        //2.required=false,则无需检查是否登录
        if(!loginRequired.required()){
        	log.info("required=false,则无需检查是否登录");
            return true;
        }
        //3.登录状态检查,使用response返回指定信息
        log.info("required=true,需检查是否登录");
        User user = (User)request.getSession().getAttribute("user");
        if(ObjectUtil.isNull(user)) {
        	response.sendRedirect("/login");
        	return false;
        };
    	return false;
	}
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2, ModelAndView arg3)
			throws Exception {
		req.setAttribute("ctx", req.getContextPath());//前端全局变量
		resp.setContentType("text/html;charset=utf-8"); 
		resp.setCharacterEncoding("utf-8");
		System.out.println("MyInterceptor01--->postHandle()执行控制器之后且在渲染视图前调用此方法....");
	}
}
