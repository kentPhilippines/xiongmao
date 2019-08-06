package com.payProject.system.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.system.entity.Resources;
import com.payProject.system.entity.ResourcesExample;
import com.payProject.system.entity.User;
import com.payProject.system.entity.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResourcesMapper extends  MyMapper<Resources,ResourcesExample>{
    int countByExample(ResourcesExample example);

    int deleteByExample(ResourcesExample example);

    /**
     * <p>根据用户id查询用户所对应资源列表</p>
     * @param string
     * @return
     */
	List<Resources> findResourceByUserId(String string);
}