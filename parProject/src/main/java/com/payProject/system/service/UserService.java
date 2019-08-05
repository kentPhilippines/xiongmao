package com.payProject.system.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.payProject.config.common.PageResult;
import com.payProject.system.entity.User;

public interface UserService {
	/**
	 * <p>根据用户的用户名，查询用户</p>
	 * @param userName	用户名
	 * @return	如果返回的结果为null则证明没有这个用户
	 */
	User findUserByUserId(String userId);
	/**
	 * 增加一个用户
	 * @param user   用户的具体数据
	 * @return		false:增加失败  true:增加成功
	 */
	Boolean addUser(User user);
	
	/**
	 * <p>分页查询呢用户数据</p>
	 * <p>可传参数</p>
	 * <li>@param user   userId</li>
	 * <li>@param user   userMail</li>
	 * <li>@param user   userPhone</li>
	 * @return
	 */
	List<User> findPageUserByUser (User user);
	/**
	 * <p>根据账户id删除</p>
	 * @param userId	账户id
	 * @return   true  删除成功 fasle 删除失败
	 */
	boolean deleteUserByUserId(String userId);
	/**
	 * <p>修改用户资料,根据用户id</p>
	 * @param user		用户资料
	 * @return
	 */
	boolean UpdateUserByUserId(User user);
	
	
	
	

}
