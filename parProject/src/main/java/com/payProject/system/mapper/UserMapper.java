package com.payProject.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.system.entity.User;
import com.payProject.system.entity.UserExample;

@Mapper
public interface UserMapper  extends  MyMapper<User,UserExample>{
    /**
     * <p>查询所有的</p>
     * @return
     */
	Page<User> selectUserAll();
	
	
	
	/**
	 * <p>分页查询用户</p>
	 * @param example	查询条件
	 * @return  分页数据
	 */
	Page<User>  selectPageByExample(UserExample example);



	/**
	 * <p>根据用户id删除用户</p>
	 * @param userId 
	 * @return
	 */
	int deleteByUserId(String userId);
}