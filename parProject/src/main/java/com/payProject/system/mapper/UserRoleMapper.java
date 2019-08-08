package com.payProject.system.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.system.entity.User;
import com.payProject.system.entity.UserExample;
import com.payProject.system.entity.UserRole;
import com.payProject.system.entity.UserRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserRoleMapper  extends  MyMapper<UserRole,UserRoleExample>{
    int deleteByPrimaryKey(Integer id);

    UserRole selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    /***
     * <p>添加一个用户和角色的对应关系</p>
     * @param list		用户和角色的对应关系集合
     * @return			
     */
	int insertUserRole(@Param("list")List<UserRole> list);
	
	/**
	 *	<p>删除一个数据根据userId</p>
	 *	<Strong>实际上这个userId是用户的数据id这里一定要注意</Strong>
	 * @param id
	 * @return
	 */
	int deleteByUserId(Integer id);
}