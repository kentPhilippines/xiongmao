package com.payProject.system.contorller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payProject.config.common.JsonResult;
import com.payProject.system.entity.User;
import com.payProject.system.service.UserRoleService;
import com.payProject.system.service.UserService;
import com.payProject.system.util.EncryptUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;

@Controller
@RequestMapping("/system")
public class UserContorller {
	@Autowired
	UserService userService;
	/**
	 * 根据传入的信息给  user 表  和role表增加信息
	 * @param 	user	用户信息
	 * @param 	userRoleList	角色状态数组
	 * @return 	boolean数据    true为成功    false为失败
	 * 
	 */
	@ResponseBody
	@PostMapping("/user/addUser")
	public JsonResult addUser(User user){
		User users = userService.findUserByName(user.getUserName());
		if(ObjectUtil.isNull(users)){
		Map<String, String> map = EncryptUtil.encryptPassword(user.getUserName(), user.getUserPassword());
		systemUser(user);//这里使用只为获取用户支付密码
		user.setUserPassword(map.get("password"));
		user.setUserSalt(map.get("salt"));
		Boolean flag = userService.addUser(user);
		if(flag)
			return JsonResult.buildSuccessResult("增加成功");
		return JsonResult.buildFailResult("增加失败");	
	}
	return  JsonResult.buildFailResult("当前用户名已存在");
	}

	
	/**
	 * 系统生成一个用户
	 * @param user
	 * @return
	 */
	public User systemUser(User user) {
		String randomNumbers = RandomUtil.randomNumbers(10);//登录密码
		String randomNumbers2 = RandomUtil.randomNumbers(10);//支付密码
		Map<String, String> map = EncryptUtil.encryptPassword(user.getUserName(),randomNumbers);
		user.setUserPassword(map.get("password"));
		user.setUserSalt(map.get("salt"));
		Map<String, String> encryptPassword = EncryptUtil.encryptPassword(randomNumbers2);
		user.setPayPassword(encryptPassword.get("payPassword"));
		return user;
	}
	
	@RequestMapping("/user/userShow")
	public String userShow( ){
		return "/system/user/userShow";
	}
	
	/**
	 * <p>用户展示</p>
	 * @return 2019-07-31
	 */
	@ResponseBody
	@RequestMapping("/user/userList")
	public JsonResult userList( ){
		return null;
	}
	
	@RequestMapping("/user/userAdd")
	public String userAdd( ){
		return "/system/user/userAdd";
	}
}
