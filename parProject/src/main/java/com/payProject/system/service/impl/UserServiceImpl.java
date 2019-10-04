package com.payProject.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.payProject.manage.entity.UserAccount;
import com.payProject.manage.entity.UserAccountExample;
import com.payProject.manage.mapper.UserAccountMapper;
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
	@Autowired
	UserAccountMapper userAccountDao;
	@Override
	public User findUserByUserId(String userId) {
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
		UserExample example = new UserExample(); 
		Criteria criteria = example.createCriteria(); 
		example.setOrderByClause("createTime ASC");
		if(StrUtil.isNotBlank(user.getUserId())) {
			criteria.andUserIdLike(user.getUserId()); 
		}else if(StrUtil.isNotBlank(user.getUserName())) {
			criteria.andUserNameLike(user.getUserId()); 
		}else if(StrUtil.isNotBlank(user.getUserMail())) {
			criteria.andUserMailLike(user.getUserMail()); 
		}else if(StrUtil.isNotBlank(user.getUserPhone())) {
			criteria.andUserPhoneLike(user.getUserPhone()); 
		} else if (null != user.getUserType()) {
			criteria.andUserTypeEqualTo(user.getUserType());
			}
		if(StrUtil.isNotBlank(user.getRetain4()))//代理商账户
			criteria.andRetain4EqualTo(user.getRetain4());
		List<User> selectByExample = userDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public boolean deleteUserByUserId(String userId) {
		int i = userDao.deleteByUserId(userId);
		return i>0;
	}
	@Override
	public boolean UpdateUserByUserId(User user) {
		UserExample example = new UserExample(); 
		Criteria criteria = example.createCriteria(); 
		criteria.andIdEqualTo(user.getId());
		int updateByExampleSelective = userDao.updateByExampleSelective(user, example);
		return updateByExampleSelective >0;
	}
	/**
	 * <p>该方法查询所有外部商户号</p>
	 */
	@Override
	public List<User> findUserAll() {
		UserExample example = new UserExample(); 
		Criteria criteria = example.createCriteria(); 
		criteria.andUserTypeEqualTo(2);
		List<User> selectByExample = userDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public boolean addUserAccount(UserAccount userAccount) {
		int insertSelective = userAccountDao.insertSelective(userAccount);
		return insertSelective > 0 && insertSelective < 2;
	}
	@Override
	public List<UserAccount> findPageUserAccountByAccount(UserAccount account) {
		List<UserAccount> selectByExample = userAccountDao.selectByExampleAnd(account);
		return selectByExample;
	}
	@Override
	public boolean deleteUserAccount(Integer id) {
		int deleteByPrimaryKey = userAccountDao.deleteByPrimaryKey(id);
		return deleteByPrimaryKey > 0 && deleteByPrimaryKey < 2;
	}
	@Override
	public List<UserAccount> findUserAccountByUserId(String userId) {
		UserAccount account = new UserAccount();
		account.setUserId(userId);
		List<UserAccount> selectByExampleAnd = userAccountDao.selectByExampleAnd(account);
		return selectByExampleAnd;
	}
	@Override
	public List<UserAccount> findUserAccountByUserId(List<String> accountList) {//所有代理商子账户的账户
		UserAccount account = new UserAccount();
		account.setUserIdList(accountList);
		log.info("代理商商户集合："+accountList);
		List<UserAccount> selectByExampleAnd = userAccountDao.findUserAccountByUserId(account);
		return selectByExampleAnd;
	}
	@Override
	public List<User> findUserByAgent(String userId) {
		UserExample example = new UserExample(); 
		Criteria criteria = example.createCriteria(); 
		criteria.andRetain4EqualTo(userId);
		List<User> userList = userDao.selectByExample(example);
		return userList;
	}
}
