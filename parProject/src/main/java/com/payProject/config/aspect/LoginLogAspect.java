package com.payProject.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.payProject.config.mapper.LogMapper;
/**
 * <p>登录日志记录</p>
 * @author K
 * 2019-08-09
 */
@Aspect
@Component
public class LoginLogAspect {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private LogMapper logDao;
	 
	 /**
	  * 
	  */
	 @Pointcut("execution(public * com.payProject.system.contorller..IndexContorller(..))")
	 public void loginLog() {}
	 /**
	  * <p>日志记录业务发生</p>
	  * @param joinPoint
	  * @throws Throwable
	  */
	 @Around(value = "loginLog()")
	 public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		 joinPoint.proceed();//执行方法
	 }
}
