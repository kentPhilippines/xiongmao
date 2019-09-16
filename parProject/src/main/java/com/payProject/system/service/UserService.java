package com.payProject.system.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.payProject.config.common.PageResult;
import com.payProject.manage.entity.UserAccount;
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
	/**
	 * <p>查询所有的用户外部账户号</p>
	 * @return
	 */
	List<User> findUserAll();
	/**
	 * <p>绑定账户号和商户号</p>
	 * @param userAccount
	 * @return
	 */
	boolean addUserAccount(UserAccount userAccount);
	/**
	 * <p>分页查询账户关联情况</p>
	 * @param account
	 * @return
	 */
	List<UserAccount> findPageUserAccountByAccount(UserAccount account);
	/**
	 * <p>删除账户号商户号对应关系</p>
	 * @param id
	 * @return
	 */
	boolean deleteUserAccount(Integer id);
	/**
	 * <p>根据账户号查询对应的商户号</p>
	 * @param userId
	 * @return
	 */
	List<UserAccount> findUserAccountByUserId(String userId);
	
	
	
	

}
