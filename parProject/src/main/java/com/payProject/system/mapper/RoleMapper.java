package com.payProject.system.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.system.entity.Role;
import com.payProject.system.entity.RoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends  MyMapper<Role,RoleExample>{
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);
    /**
     *<p>根据用户Id查询用户所对应得角色</p> 
     * @param string
     * @return
     */
	List<Role> findByUserId(String userId);
}