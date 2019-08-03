package com.payProject.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.payProject.system.entity.User;
import com.payProject.system.entity.UserExample;
import com.payProject.system.entity.UserExample.Criteria;
import com.payProject.system.mapper.UserMapper;
import com.payProject.system.service.UserService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

@Service
public class UserServiceImpl implements UserService {
	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserMapper userDao;
	@Override
	public User findUserByName(String userId) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<User> userList = userDao.selectByExample(example);
		if(CollUtil.isEmpty(userList)) 
			return null;
		/**
		 * 根据表设计,用户的userId不可能重复,所以这里查询的结果集必然是只有一个元素
		 */
		return CollUtil.getFirst(userList);
	}
	@Override
	public Boolean addUser(User user) {
		int count = userDao.insertSelective(user);
		return count>0 && count <2 ;
	}
	@Override
	public List<User> findPageUserByUser(User user) {
		// Page<User> list  = userDao.selectUserAll();
		UserExample example = new UserExample(); 
		Criteria criteria = example.createCriteria(); example.setOrderByClause("createTime ASC");
		if(StrUtil.isNotBlank(user.getUserId())) {
			criteria.andUserIdLike(user.getUserId()); 
		}else if(StrUtil.isNotBlank(user.getUserName())) {
			criteria.andUserNameLike(user.getUserId()); 
		}else if(StrUtil.isNotBlank(user.getUserMail())) {
			criteria.andUserMailLike(user.getUserMail()); 
		}else if(StrUtil.isNotBlank(user.getUserPhone())) {
			criteria.andUserPhoneLike(user.getUserPhone()); 
			} 
		List<User> selectByExample = userDao.selectByExample(example);
		return selectByExample;
		/*
		 * UserExample example = new UserExample(); Criteria criteria =
		 * example.createCriteria(); example.setOrderByClause("createTime ASC");
		 * if(StrUtil.isNotBlank(user.getUserId())) {
		 * criteria.andUserIdLike(user.getUserId()); }else
		 * if(StrUtil.isNotBlank(user.getUserName())) {
		 * criteria.andUserNameLike(user.getUserId()); }else if
		 * (StrUtil.isNotBlank(user.getUserMail())) {
		 * criteria.andUserMailLike(user.getUserMail()); }else if
		 * (StrUtil.isNotBlank(user.getUserPhone())) {
		 * criteria.andUserPhoneLike(user.getUserPhone()); } List<User> userList =
		 * userDao.selectByExample(example); return userList;
		 */
	}
}
