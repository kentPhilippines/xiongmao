package com.payProject.manage.contorller;

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
import com.payProject.config.common.Constant.Common;
import com.payProject.config.exception.ParamException;
import com.payProject.config.common.Constant;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.service.AccountService;
import com.payProject.system.entity.User;
import com.payProject.system.service.UserService;
import com.payProject.system.util.EncryptUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/manage/appAcount")
public class AppManageContorller {
	Logger log = LoggerFactory.getLogger(ChannelContorller.class);
	@Autowired
	AccountService accountServiceImpl;
	@Autowired
	UserService userService;
	@RequestMapping("/manage")
	public String accountlShow( ){
		return "/manage/account/userManage/appAccountManage";
	}
	/**
	 * <p>用户展示</p>
	 * @return 2019-07-31
	 */
	@ResponseBody
	@RequestMapping("appAccountManageList")
	public PageResult<User> userList(User user,String page,String limit){
		log.info("【外部商户管理】");
		user.setUserType(Common.USER_WAI);
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<User> list = userService.findPageUserByUser(user);
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		PageResult<User> pageR = new PageResult<User>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
		return pageR;
	}
	@RequestMapping("/appAccountEditShow")
	public String appAccountEditShow(User user ,Model m ){
		if( StrUtil.isBlank(user.getUserId())) {
			throw new ParamException("请求参数无效");
		}
		User findUserByUserId = userService.findUserByUserId(user.getUserId());
		m.addAttribute("user", findUserByUserId);
		return "/manage/account/userManage/appAccountEdit";
	}
	@ResponseBody
	@PostMapping("/appAccountManageAdd")
	@Transactional
	public JsonResult appAccountManageAdd(User user){
		log.info("增加用户请求参数"+user.toString());
		User users = userService.findUserByUserId(user.getUserId());
		if(ObjectUtil.isNull(users)){
		Map<String, String> map = EncryptUtil.encryptPassword(user.getUserId(), user.getUserPassword());
		//systemUser(user);//这里使用只为获取用户支付密码
		Map<String, String> encryptPassword = EncryptUtil.encryptPassword(user.getPayPassword());
		user.setPayPassword(encryptPassword.get(Constant.Common.PAYPASSWORD));
		user.setUserPassword(map.get(Constant.Common.PASSWORD));
		user.setUserSalt(map.get(Constant.Common.SALT));
		Boolean flag = userService.addUser(user);
		if(flag)
			return JsonResult.buildSuccessMessage("增加成功");
		return JsonResult.buildFailResult("增加失败");	
	}
	return  JsonResult.buildFailResult("当前用户名已存在");
	}
	@RequestMapping("/userManageAdd")
	public String userAdd( ){
		return "/manage/account/userManage/appAccountManageAdd";
	}
	@ResponseBody
	@RequestMapping("/appAccountEdit")
	public JsonResult appAccountEdit(User user ){
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
