package com.payProject.system.contorller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.internal.util.Contracts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payProject.config.common.Constant;
import com.payProject.config.common.Constant.User;
import com.payProject.system.annotation.LoginRequired;
import com.payProject.system.entity.Resources;
import com.payProject.system.service.ResourcesService;
import com.payProject.system.service.RoleService;
import com.payProject.system.service.UserRoleService;
import com.payProject.system.service.UserService;
import com.payProject.system.util.MapUtil;
import com.payProject.system.util.MenuUtil;
@Controller
@RequestMapping("/")
public class IndexContorller {
	Logger log = LoggerFactory.getLogger(IndexContorller.class);
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	ResourcesService resourcesService;
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
		 /*1,首先第一步是由于shiro框架引入所以登录在框架内做了验证*/
		 Subject subject = SecurityUtils.getSubject();
		 Session session = subject.getSession();
		 Object attribute = session.getAttribute(Constant.User.USER_IN_SESSION());
		 Map<String, Object> objectToMap = MapUtil.objectToMap(attribute);
		 com.payProject.system.entity.User user = MapUtil.mapToBean(objectToMap,com.payProject.system.entity.User.class);
		 String userId = (String)objectToMap.get(Constant.User.USER_ID());
		 /*2,获取用户的所有菜单资源并转换信息*/
		 /*3,拿到当前用户的登录信息*/
		 /*4,获取其他登录跳转页面URL*/
		 List<Resources> resourcesList = resourcesService.findRourcesIdByUserId(userId);
		 List<Resources> menuList = MenuUtil.getMenuList(resourcesList);
		 m.addAttribute("menuList", menuList);
		 m.addAttribute("user", user);
		 /*5,日志记录,该功能由aop完成*/
	        return "index";
	    }
}
