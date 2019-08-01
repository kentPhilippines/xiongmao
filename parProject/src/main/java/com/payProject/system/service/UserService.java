package com.payProject.system.service;

import com.payProject.system.entity.User;

public interface UserService {
	/**
	 * <p>根据用户的用户名，查询用户</p>
	 * @param userName	用户名
	 * @return	如果返回的结果为null则证明没有这个用户
	 */
	User findUserByName(String userName);
	/**
	 * 增加一个用户
	 * @param user   用户的具体数据
	 * @return		false:增加失败  true:增加成功
	 */
	Boolean addUser(User user);
	
	

}
