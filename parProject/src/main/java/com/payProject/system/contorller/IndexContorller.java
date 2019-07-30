package com.payProject.system.contorller;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payProject.system.annotation.LoginRequired;
@Controller
@RequestMapping("/")
public class IndexContorller {
	/**
	 * 主页面跳转，在这之前需要验证权限
	 * @param m
	 * @return
	 */
	 @LoginRequired(required = true)
	 @RequestMapping("/index")
	 public String index(Model m,HttpServletRequest request) {
		 /**
		  * 1,验证登录权限
		  * 2,获取用户所有的权限信息
		  * 3,拿到当前用户的登录信息
		  * 4,获取其他登录跳转页面URL
		  * 5,日志记录
		  */
	        return "index";
	    }
}
