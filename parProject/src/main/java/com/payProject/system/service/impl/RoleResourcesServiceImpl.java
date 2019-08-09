package com.payProject.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.system.entity.RoleResources;
import com.payProject.system.entity.RoleResourcesExample;
import com.payProject.system.entity.RoleResourcesExample.Criteria;
import com.payProject.system.mapper.RoleResourcesMapper;
import com.payProject.system.service.RoleResourcesService;
@Service
public class RoleResourcesServiceImpl  implements RoleResourcesService{
	@Autowired
	RoleResourcesMapper roleResourcesDao;
	@Override
	public Boolean addRoleResource(List<RoleResources> list,String roleId) {
		RoleResourcesExample example = new RoleResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(Integer.valueOf(roleId));
		roleResourcesDao.deleteByExample(example);
		int i = roleResourcesDao.insertByRoleIdAndResourcesId(list);
		return i>0 && i == list.size();
	}

}
