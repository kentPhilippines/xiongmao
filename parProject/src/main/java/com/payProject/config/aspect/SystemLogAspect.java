package com.payProject.config.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.payProject.config.annotation.LogMonitor;
import com.payProject.config.common.Constant;
import com.payProject.config.entity.Log;
import com.payProject.config.mapper.LogMapper;
import com.payProject.config.util.IPUtil;
import com.payProject.system.annotation.LoginRequired;
import com.payProject.system.entity.User;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
/**
 * <p>系统情况日志记录</p>
 * @author K
 * 2019-08-01
 *
 */
@Aspect
@Component
public class SystemLogAspect {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private LogMapper logDao;
    
    @Pointcut("execution(public * com.payProject.config.contorller..*(..))")
    public void systemLog() {}
    @Around(value = "systemLog()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	//2、有无日志监控注解，有则输出
    	String methodName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    	Method method = signature.getMethod();
    	if(method.isAnnotationPresent(LogMonitor.class)) {
    		 Log log = new Log();
    	        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    	        HttpServletRequest request = attributes.getRequest();
    	        HttpServletResponse response = attributes.getResponse();
    	        User user = (User)request.getSession().getAttribute(Constant.User.USER_IN_SESSION());
    	        long startTime = System.currentTimeMillis();//记录执行时间
    	        Object proceed = joinPoint.proceed(joinPoint.getArgs());//执行方法
    	        long endTime = System.currentTimeMillis();//记录时间
    	        long totalTime = endTime - startTime;
    	            logger.info("**********Method: {}, Start: {}, End: {}, Total: {}ms**********", methodName, DateUtil.formatDateTime(new Date(startTime)),   DateUtil.formatDateTime(new Date(endTime)), totalTime);
    	        //3、入系统日志表
    	        if(joinPoint.getArgs().length > 0){
    	        	log.setParam(JSONUtil.parse(joinPoint.getArgs()[0]).toString());
    	        }
    	        log.setClientIp(IPUtil.getClientIp(request));
    	        log.setMetHod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
    	        log.setOperator(ObjectUtil.isNull(user)?"系统未登录刷新":user.getUserId());
    	        log.setRequertUrl(request.getRequestURI());
    	        log.setReturnData(proceed.toString());
    	        log.setStartTime(DateUtil.formatDateTime(new Date(startTime)));
    	        log.setEndTime(DateUtil.formatDateTime(new Date(endTime)));
    	        log.setTotalTime(String.valueOf(totalTime));
    	        logDao.insertSelective(log);
    	}else {
    		joinPoint.proceed();//执行方法
    	}
    }
}
