package com.payProject.config.base.mapperBase;


import java.util.List;

import org.apache.ibatis.annotations.Param;

@org.apache.ibatis.annotations.Mapper
public interface MyMapper<T, E>   {
	int insert(T record);
	int insertSelective(T record);
    int countByExample(E example);
    List<T> selectByExample(E example);
    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);
    int updateByExample(@Param("record") T record, @Param("example") E example);
}
