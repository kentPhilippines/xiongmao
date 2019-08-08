package com.payProject.system.contorller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.exception.ParamException;
import com.payProject.system.entity.Role;
import com.payProject.system.entity.RoleResources;
import com.payProject.system.entity.User;
import com.payProject.system.entity.UserRole;
import com.payProject.system.service.RoleService;
import com.payProject.system.service.UserRoleService;
import com.payProject.system.service.UserService;
import com.payProject.system.util.EncryptUtil;
import com.payProject.system.util.TransferBean;
import com.payProject.system.util.TransferUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

@Controller
@RequestMapping("/system")
public class UserContorller {
	Logger log = LoggerFactory.getLogger(UserContorller.class);
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserRoleService userRoleService;
	/**
	 * 根据传入的信息给  user 表  和role表增加信息
	 * @param 	user	用户信息
	 * @param 	userRoleList	角色状态数组
	 * @return 	boolean数据    true为成功    false为失败
	 * 
	 */
	@ResponseBody
	@PostMapping("/user/addUser")
	@Transactional
	public JsonResult addUser(User user){
		log.info("增加用户请求参数"+user.toString());
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
		log.info("查询用户请求参数"+user.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<User> list = userService.findPageUserByUser(user);
		 PageInfo<User> pageInfo = new PageInfo<User>(list);
		 PageResult<User> pageR = new PageResult<User>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("增加用户相应参数"+pageR.toString());
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
	@Transactional
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
	
	
	
	/**
	 * <p>这里因为在纠结于用数据id还是用userId作为唯一的关系标识,所以逻辑有些乱但是目前不影响功能的完善</p>
	 * @param userId
	 * @param m
	 * @return
	 */
	@RequestMapping("/user/roleAndRShow")
	public String  roleAndRShow(String userId,Model m) {
		if(StrUtil.isBlank(userId))
			throw new ParamException("请求参数无效");
		User findUserByUserId = userService.findUserByUserId(userId);//保存的话我们统一保存用户的数据id号,因为后期userId这个字段可能会做数据延申
		String id = String.valueOf(findUserByUserId.getId());
		m.addAttribute("userId", id);
		List<Integer>checkedList  = userRoleService.findUserLationshipByUserId(id);
		List<Role> roleList = roleService.findRoleAll();
		List<TransferBean> transferBeanList = TransferUtil.getTransferBeanList(roleList);
		m.addAttribute("roleList", JSONUtil.toJsonPrettyStr(transferBeanList));
		m.addAttribute("roleNotList", JSONUtil.toJsonPrettyStr(checkedList));
		return "/system/plugIn/transfer";
	}
	
	@ResponseBody
	@RequestMapping("/user/bindingRole")
	@Transactional
	public JsonResult  bindingRole(String roles ,String  userId) {
		if(StrUtil.isBlank(userId))
			throw new ParamException("必传参数为空");
		if(StrUtil.isBlank(roles))
			throw new ParamException("必传参数为空");
		List<String> split = StrUtil.splitTrim(roles, ',');
		List<UserRole> list = new ArrayList<UserRole>();
		for(String i : split) {
			UserRole roleR = new UserRole();
			roleR.setUserId(userId);
			roleR.setRole(Integer.valueOf(i));
			list.add(roleR);
			roleR = null ; //置为null会优先回收
		}
		Boolean flag = userRoleService.addUserRole(list , userId);
		if(flag)
			return JsonResult.buildSuccessMessage("用户角色绑定成功");
		return JsonResult.buildFailResult("用户角色绑定失败");
	}
}
