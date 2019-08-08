package com.payProject.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.system.entity.RoleResources;
import com.payProject.system.mapper.RoleResourcesMapper;
import com.payProject.system.service.RoleResourcesService;
@Service
public class RoleResourcesServiceImpl  implements RoleResourcesService{
	@Autowired
	RoleResourcesMapper roleResourcesDao;
	@Override
	public Boolean addRoleResource(List<RoleResources> list,String roleId) {
		roleResourcesDao.deleteByPrimaryKey(Integer.valueOf(roleId));
		int i = roleResourcesDao.insertByRoleIdAndResourcesId(list);
		return i>0 && i == list.size();
	}

}
