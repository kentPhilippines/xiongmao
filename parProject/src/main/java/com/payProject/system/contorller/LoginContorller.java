package com.payProject.system.contorller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payProject.config.common.JsonResult;
import com.payProject.system.entity.User;

import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/")
public class LoginContorller {
	/**
	 * 主页面跳转，在这之前需要验证权限
	 * @param m
	 * @return
	 */
	 @RequestMapping("/login")
	 public String login() {
		return "login";
	 }
	 @ResponseBody
	 @RequestMapping("/loginOnline")
	 public JsonResult login(User user,HttpSession session) {
		 if(StrUtil.isBlankIfStr(user.getUserName()))
			throw new UnknownAccountException("请填写用户名");
		 if(StrUtil.isBlankIfStr(user.getUserPassword()))
			 throw new IncorrectCredentialsException("请填写密码");	
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getUserPassword());
			//有实例的话安全管理器获取subject
			Subject subject = SecurityUtils.getSubject();
			token.setRememberMe(true);
			try {
				//当输入的密码存在错误的时候出出现如下的异常，，根据自定义异常处理类，会直接返还给前端一个异常
				subject.login(token);
				//登录成功之后将用户信息存入session
				User userDB = (User) subject.getPrincipal();
				session.setAttribute("user", userDB);
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
			return JsonResult.buildSuccessResult();
	 }
	 @GetMapping("logout")
		public String logout() {
			SecurityUtils.getSubject().logout();
			return "login";
		}
}
