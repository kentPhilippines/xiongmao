package com.payProject.system.contorller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
import com.payProject.config.common.Constant;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.exception.ParamException;
import com.payProject.system.entity.Resources;
import com.payProject.system.entity.Role;
import com.payProject.system.entity.RoleResources;
import com.payProject.system.service.ResourcesService;
import com.payProject.system.service.RoleResourcesService;
import com.payProject.system.service.RoleService;
import com.payProject.system.util.LayuiTreeBean;
import com.payProject.system.util.MapUtil;
import com.payProject.system.util.TreeUtil;

import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/system")
public class RoleContorller {
	Logger log = LoggerFactory.getLogger(RoleContorller.class);
	@Autowired
	RoleService roleService;
	@Autowired
	ResourcesService resourcesService;
	@Autowired
	RoleResourcesService roleResourcesService;
	/**
	 * 根据传入的信息给  user 表  和role表增加信息
	 * @param 	user	用户信息
	 * @param 	userRoleList	角色状态数组
	 * @return 	boolean数据    true为成功    false为失败
	 * 
	 */
	@ResponseBody
	@PostMapping("/role/addRole")
	public JsonResult addUser(Role role){
		log.info("增加角色参数"+role.toString());
		if( StrUtil.isBlank(role.getRoleName())) 
			throw new ParamException("请求参数无效");
		Boolean flag = roleService.addRole(role);
		if(flag)
			return JsonResult.buildSuccessMessage("增加成功");
		return JsonResult.buildFailResult("增加失败");	
	}

	
	@RequestMapping("/role/roleShow")
	public String roleShow( ){
		return "/system/role/roleShow";
	}
	
	/**
	 * <p>用户展示</p>
	 * @return 2019-07-31
	 */
	@ResponseBody
	@RequestMapping("/role/roleList")
	public PageResult<Role> userList(Role role,String page,String limit){
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<Role> list = roleService.findPageRoleByRser(role);
		 PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		 PageResult<Role> pageR = new PageResult<Role>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info(pageR.toString());
		return pageR;
	}
	@RequestMapping("/role/roleAdd")
	public String roleAdd( ){
		return "/system/role/roleAdd";
	}
	
	@ResponseBody
	@RequestMapping("/role/roleDel")
	public JsonResult roleDel(Role role ){
		boolean flag  = roleService.deleteRoleByRole(role);
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}
		return JsonResult.buildFailResult();
	}
	
	@RequestMapping("/role/roleEditShow")
	public String userEditShow(Role role ,Model m){
	if(null == role.getRoleId()) 
		throw new ParamException("请求参数无效");
	Role roleBean = roleService.findRoleByRoleId(role.getRoleId());
	log.info("修改角色星系的时候获取角色的详细信息"+roleBean.toString());
	m.addAttribute("role", roleBean);
		return "/system/role/roleEdit";
	}
	@ResponseBody
	@RequestMapping("/role/roleEdit")
	public JsonResult userEdit(Role role){
		if(null == role.getRoleId()) 
			throw new ParamException("请求参数无效");
		role.setCreateTime(null);
		boolean flag  = roleService.UpdateRoleByRole(role);
		if(flag) {
			return JsonResult.buildSuccessMessage("修改成功");
		}
		return JsonResult.buildFailResult();
	}
	/**
	 * <p>获取角色对应的资源信息</p>
	 * @param role
	 * @param userId
	 * @return
	 */
	@RequestMapping("/role/roleByResourcesShow")
	public String roleByResourcesShow(Role role,Model m) {
		if(StrUtil.isBlankIfStr(role.getRoleId()) ) 
			throw new ParamException("必传参数为空");
		m.addAttribute("roleId", role.getRoleId());
		return"/system/plugIn/tree";
	}
	@ResponseBody
	@RequestMapping("/role/getTree")
	public List<LayuiTreeBean> getTree(String roleId) {
		if(StrUtil.isBlankIfStr(roleId)) 
			throw new ParamException("必传参数为空");
		List<Integer> findRourcesByRoleId = resourcesService.findRourcesByRoleId(Integer.valueOf(roleId));
		List<Resources> list  = resourcesService.findRourcesByAll();
	//	List<Integer> rourcesIdlist  = resourcesService.findRourcesIdByUserId(user.get(Constant.User.USER_ID()).toString());
		List<LayuiTreeBean> layuiTreeBeanList = TreeUtil.getLayuiTreeBeanList(list, findRourcesByRoleId);
		return layuiTreeBeanList;
	} 
	
	@ResponseBody
	@RequestMapping("/role/roleAddResource")
	@Transactional
	public JsonResult roleAddResource(String roleId ,	String resource) {
		if(StrUtil.isBlank(roleId))
			throw new ParamException("必传参数为空");
		if(StrUtil.isBlank(resource))
			throw new ParamException("必传参数为空");
		List<String> split = StrUtil.splitTrim(resource, ',');
		List<RoleResources> list = new ArrayList<RoleResources>();
		for(String i : split) {
			RoleResources roleR = new RoleResources();
			roleR.setRoleId(Integer.valueOf(roleId));
			roleR.setResourcesId(Integer.valueOf(i));
			list.add(roleR);
			roleR = null ; //置为null会优先回收
		}
		Boolean flag  = roleResourcesService.addRoleResource(list,roleId);
		return flag?JsonResult.buildSuccessMessage("角色和资源绑定成功"):JsonResult.buildFailResult("角色和资源绑定失败");
	}
	
}
