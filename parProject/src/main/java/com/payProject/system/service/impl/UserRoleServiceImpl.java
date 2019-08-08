package com.payProject.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.system.entity.Resources;
import com.payProject.system.entity.UserRole;
import com.payProject.system.entity.UserRoleExample;
import com.payProject.system.entity.UserRoleExample.Criteria;
import com.payProject.system.mapper.UserMapper;
import com.payProject.system.mapper.UserRoleMapper;
import com.payProject.system.service.UserRoleService;

@Service
public class UserRoleServiceImpl  implements UserRoleService{
	@Autowired
	UserRoleMapper userRoleDao;
	@Override
	public List<Integer> findUserLationshipByUserId(String userId) {
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<UserRole> selectByExample = userRoleDao.selectByExample(example);
		List<Integer> list = new ArrayList<Integer>();
		for(UserRole bean : selectByExample) {
			list.add(bean.getRole());
		}
		return list;
	}
	@Override
	public Boolean addUserRole(List<UserRole> list, String id) {
		userRoleDao.deleteByUserId(Integer.valueOf(id));
		int i  = userRoleDao.insertUserRole(list);
		return i>0 && list.size() == i;
	}
}
