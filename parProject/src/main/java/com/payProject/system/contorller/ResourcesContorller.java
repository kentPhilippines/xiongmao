package com.payProject.system.contorller;

import java.util.List;

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
import com.payProject.system.entity.Resources;
import com.payProject.system.entity.Role;
import com.payProject.system.service.ResourcesService;
import com.payProject.system.service.RoleService;

import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/system")
public class ResourcesContorller {
	Logger log = LoggerFactory.getLogger(ResourcesContorller.class);
	@Autowired
	ResourcesService resourcesService;
	@ResponseBody
	@PostMapping("/resources/addResources")
	public JsonResult addResources(Resources resources){
		log.info("增加资源参数"+resources.toString());
		if( StrUtil.isBlank(resources.getResourcesName()) || StrUtil.isBlank(resources.getResourcesKey()) ||null == resources.getLevel() ) 
			throw new ParamException("必填参数未填");
		Boolean flag = resourcesService.addResources(resources);
		if(flag)
			return JsonResult.buildSuccessMessage("增加成功");
		return JsonResult.buildFailResult("增加失败");	
	}

	
	@RequestMapping("/resources/resourcesShow")
	public String roleShow( ){
		return "/system/resources/resourcesShow";
	}
	@ResponseBody
	@RequestMapping("/resources/resourcesList")
	public PageResult<Resources> userList(Resources resources,String page,String limit){
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<Resources> list = resourcesService.findPageResourcesByResources(resources);
		 PageInfo<Resources> pageInfo = new PageInfo<Resources>(list);
		 PageResult<Resources> pageR = new PageResult<Resources>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info(pageR.toString());
		return pageR;
	}
	@RequestMapping("/resources/resourcesAdd")
	public String roleAdd( ){
		return "/system/resources/resourcesAdd";
	}
	
	@ResponseBody
	@RequestMapping("/resources/resourcesDel")
	public JsonResult roleDel(Resources resources ){
		boolean flag  = resourcesService.deleteResourcesByResources(resources);
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}
		return JsonResult.buildFailResult();
	}
	
	@RequestMapping("/resources/resourcesEditShow")
	public String userEditShow(Resources resources ,Model m){
	if(null == resources.getResourcesId()) 
		throw new ParamException("请求参数无效");
	Resources resourcesBean = resourcesService.findResourcesByResourcesId(resources.getResourcesId());
	Integer level = resourcesBean.getLevel();//本级菜单
	List<Resources> menu = resourcesService.findParentMenuByLevel(level);
	m.addAttribute("menu", menu);
	log.info("菜单列表"+menu.toString());
	log.info("修改资源的时候获取资源的详细信息"+resourcesBean.toString());
	m.addAttribute("resources", resourcesBean);
		return "/system/resources/resourcesEdit";
	}
	@ResponseBody
	@RequestMapping("/resources/resourcesEdit")
	@Transactional
	public JsonResult userEdit(Resources resources){
		log.info("菜单修改请求参数"+resources.toString());
		if(null == resources.getResourcesId()) 
			throw new ParamException("请求参数无效");
		resources.setCreateTime(null);
		boolean flag  = resourcesService.UpdateResourcesByResources(resources);
		if(flag) {
			return JsonResult.buildSuccessMessage("修改成功");
		}
		return JsonResult.buildFailResult();
	}


}
