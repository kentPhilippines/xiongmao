package com.payProject.system.mapper;

import com.payProject.system.entity.Resources;
import com.payProject.system.entity.ResourcesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ResourcesMapper {
    int countByExample(ResourcesExample example);

    int insert(Resources record);

    int insertSelective(Resources record);

    List<Resources> selectByExample(ResourcesExample example);

    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesExample example);

    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesExample example);

    /**
     * <p>根据用户的账户名查询用户对应的所有权限</p>
     * @param userId		<strong>用户名</strong>
     * @return				<strong>该用户对应的所有权限信息</strong>
     */
	List<Resources> findResourceByUserId(String userId);
}