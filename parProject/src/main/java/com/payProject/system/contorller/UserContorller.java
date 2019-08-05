package com.payProject.system.contorller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.exception.ParamException;
import com.payProject.system.entity.User;
import com.payProject.system.service.UserService;
import com.payProject.system.util.EncryptUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

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
		User users = userService.findUserByUserId(user.getUserId());
		if(ObjectUtil.isNull(users)){
		Map<String, String> map = EncryptUtil.encryptPassword(user.getUserId(), user.getUserPassword());
		systemUser(user);//这里使用只为获取用户支付密码
		user.setUserPassword(map.get("password"));
		user.setUserSalt(map.get("salt"));
		Boolean flag = userService.addUser(user);
		if(flag)
			return JsonResult.buildSuccessMessage("增加成功");
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
		Map<String, String> map = EncryptUtil.encryptPassword(randomNumbers);
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
	public PageResult<User> userList(User user,String page,String limit){
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<User> list = userService.findPageUserByUser(user);
		 PageInfo<User> pageInfo = new PageInfo<User>(list);
		 PageResult<User> pageR = new PageResult<User>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
		return pageR;
	}
	@RequestMapping("/user/userAdd")
	public String userAdd( ){
		return "/system/user/userAdd";
	}
	@ResponseBody
	@RequestMapping("/user/caeateAccount")
	public JsonResult caeateAccount( ){
		String account = createAccount();
		return JsonResult.buildSuccessResult(account);
	}
	/**
	 * 生成一个随机数账号
	 * @return
	 */
	private String createAccount() {
		String UserId =  RandomUtil.randomNumbers(10);//登录账号
		User users = userService.findUserByUserId(UserId);
		if(ObjectUtil.isNull(users))
			return UserId ;
		return createAccount();
	}
	
	
	@ResponseBody
	@RequestMapping("/user/userDel")
	public JsonResult userDel(User user ){
	if( StrUtil.isBlank(user.getUserId())) {
		throw new ParamException("请求参数无效");
	}
		boolean flag  = userService.deleteUserByUserId(user.getUserId());
		
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}
		return JsonResult.buildFailResult();
	}
	
	@RequestMapping("/user/userEditShow")
	public String userEditShow(User user ,Model m){
	if( StrUtil.isBlank(user.getUserId())) {
		throw new ParamException("请求参数无效");
	}
	User findUserByUserId = userService.findUserByUserId(user.getUserId());
	m.addAttribute("user", findUserByUserId);
		return "/system/user/userEdit";
	}
	@ResponseBody
	@RequestMapping("/user/userEdit")
	public JsonResult userEdit(User user ){
		if( StrUtil.isBlank(user.getUserId())) {
			throw new ParamException("请求参数无效");
		}
		user.setCreateTime(null);
		boolean flag  = userService.UpdateUserByUserId(user);
		if(flag) {
			return JsonResult.buildSuccessMessage("修改成功");
		}
		return JsonResult.buildFailResult();
	}
}
