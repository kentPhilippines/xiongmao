package com.payProject.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.system.entity.User;
import com.payProject.system.entity.UserExample;
import com.payProject.system.entity.UserExample.Criteria;
import com.payProject.system.mapper.UserMapper;
import com.payProject.system.service.UserService;

import cn.hutool.core.collection.CollUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userDao;
	@Override
	public User findUserByName(String userName) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(userName);
		List<User> userList = userDao.selectByExample(example);
		if(CollUtil.isEmpty(userList)) 
			return null;
		/**
		 * 根据表设计,用户的userName不可能重复,所以这里查询的结果集必然是只有一个元素
		 */
		return CollUtil.getFirst(userList);
	}
	@Override
	public Boolean addUser(User user) {
		int count = userDao.insertSelective(user);
		return count>0 && count <2 ;
	}

}
