package com.payProject.system.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.system.entity.Resources;
import com.payProject.system.entity.ResourcesExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ResourcesMapper extends  MyMapper<Resources,ResourcesExample> {
    int deleteByExample(ResourcesExample example);
    /**
     * <p>根据用户id查询用户所对应的资源信息</p>
     * @param userId		用户id
     * @return
     */
	List<Resources> findResourceByUserId(String userId);
    
    
    
    

}