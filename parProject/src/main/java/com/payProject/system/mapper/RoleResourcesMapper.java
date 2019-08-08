package com.payProject.system.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.system.entity.Role;
import com.payProject.system.entity.RoleExample;
import com.payProject.system.entity.RoleResources;
import com.payProject.system.entity.RoleResourcesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RoleResourcesMapper extends  MyMapper<RoleResources,RoleResourcesExample>{

    int deleteByExample(RoleResourcesExample example);

    int deleteByPrimaryKey(Integer id);

    RoleResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleResources record);

    int updateByPrimaryKey(RoleResources record);
    
    /**
     * <p>增加角色和资源之间的对应关系</p>
     * @param list
     * @return
     */
    int insertByRoleIdAndResourcesId(@Param("list")List<RoleResources> list);
}