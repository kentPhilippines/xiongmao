package com.payProject.system.contorller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payProject.system.entity.User;

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
	 
	 @RequestMapping("/loginOnline")
	 public String login(Model m,HttpServletRequest request) {
		 
		 
		User user = null ;
		request.getSession().setAttribute("userSession", user);
		return "redirect:index";
	 }
	 
}
