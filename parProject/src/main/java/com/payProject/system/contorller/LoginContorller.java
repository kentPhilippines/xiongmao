package com.payProject.system.contorller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payProject.config.annotation.LogMonitor;
import com.payProject.config.common.Constant;
import com.payProject.config.common.JsonResult;
import com.payProject.system.entity.User;
import com.payProject.system.shiro.ShiroConfig;

import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/")
public class LoginContorller {
	Logger log = LoggerFactory.getLogger(LoginContorller.class);
	/**
	 *<p> 主页面跳转，在这之前需要验证权限</p>
	 * @param m
	 * @return
	 */
	 @RequestMapping("/login")
	 public String login() {
		return "login";
	 }
	 
	 @RequestMapping("/loginOnline")
///	 @LogMonitor
	 public void login(User user, HttpServletResponse response) {
		 if(StrUtil.isBlankIfStr(user.getUserId()))
			throw new UnknownAccountException("请填写用户名");
		 if(StrUtil.isBlankIfStr(user.getUserPassword()))
			 throw new IncorrectCredentialsException("请填写密码");	
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserId(),user.getUserPassword());
			//有实例的话安全管理器获取subject
			Subject subject = SecurityUtils.getSubject();
			try {
				//当输入的密码存在错误的时候出出现如下的异常，，根据自定义异常处理类，会直接返还给前端一个异常
				subject.login(token);
				//登录成功之后将用户信息存入session
				User userDB = (User) subject.getPrincipal();
				Session session = subject.getSession();
				session.setAttribute(Constant.User.USER_IN_SESSION(), userDB);
				log.info(userDB.getUserId()+"--------------->登录成功");
				//session.setAttribute(Constant.User.USER_IN_SESSION(), userDB);
			} catch (UnknownAccountException e) {
				throw new UnknownAccountException("用户名错误");		
			} catch (IncorrectCredentialsException e) {
				throw new IncorrectCredentialsException("密码错误");	
			} catch (ExcessiveAttemptsException e){
				throw new ExcessiveAttemptsException("密码错误次数过多，请1天后重试");	
			}catch (AuthenticationException e) {
				throw new AuthenticationException("认证时的其他错误");
			} catch (Exception e) {
				throw new RuntimeException("认证时的其他错误");				
			}
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				mapper.writeValue(writer, JsonResult.buildSuccessResult());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
	 }
	 @GetMapping("logout")
		public String logout() {
		 	log.info(SecurityUtils.getSubject().getSession().getAttribute(Constant.User.USER_IN_SESSION())+"--------------->退出登录");
			SecurityUtils.getSubject().logout();
			return "login";
		}
}
