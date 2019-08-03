package com.payProject.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.payProject.system.entity.Role;
import com.payProject.system.entity.RoleExample;
@Mapper
public interface RoleMapper {
	/**
	 * <p>统计数据条数</p>
	 * @param example		<strong>动态查询类【统计条件】</strong>
	 * @return				<strong>数据条数</strong>
	 */
    int countByExample(RoleExample example);
    /**
	 * <p>增加数据</p>
	 * @param example		<strong>动态查询类【统计条件】</strong>
	 * @return				<strong>数据条数</strong>
	 */
    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * <p>根据用户id查询用户对应的角色信息</p>
     * @param string		<Strong>用户id</Strong>
     * @return			<Strong>用户对应角色信息</Strong>
     */
	List<Role> findByUserId(String userId);
}