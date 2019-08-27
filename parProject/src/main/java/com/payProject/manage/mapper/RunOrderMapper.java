package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.OrderAllEntity;
import com.payProject.manage.entity.OrderAllEntityExample;
import com.payProject.manage.entity.RunOrder;
import com.payProject.manage.entity.RunOrderExample;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RunOrderMapper  extends  MyMapper<RunOrder,RunOrderExample>{
    int deleteByExample(RunOrderExample example);
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    List<RunOrder> selectByExampleWithBLOBs(RunOrderExample example);
    RunOrder selectByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    int updateByExampleWithBLOBs(@Param("record") RunOrder record, @Param("example") RunOrderExample example);
    int updateByPrimaryKeySelective(RunOrder record);
    int updateByPrimaryKeyWithBLOBs(RunOrder record);
    int updateByPrimaryKey(RunOrder record);
}