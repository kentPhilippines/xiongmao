package com.payProject.system.mapper;

import com.payProject.system.entity.Resources;
import com.payProject.system.entity.ResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourcesMapper {
    int countByExample(ResourcesExample example);

    int deleteByExample(ResourcesExample example);

    int insert(Resources record);

    int insertSelective(Resources record);

    List<Resources> selectByExample(ResourcesExample example);

    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesExample example);

    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesExample example);

    /**
     * 根据用户id获取用户的所有权限
     * @param userid
     * @return
     */
	List<Resources> findResourceByUserId(String userid);
}