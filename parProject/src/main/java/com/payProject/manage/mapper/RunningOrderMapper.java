package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.OrderAllEntity;
import com.payProject.manage.entity.OrderAllEntityExample;
import com.payProject.manage.entity.RunningOrderEntity;
import com.payProject.manage.entity.RunningOrderEntityExample;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RunningOrderMapper  extends  MyMapper<RunningOrderEntity,RunningOrderEntityExample>{
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    List<RunningOrderEntity> selectByExampleWithBLOBs(RunningOrderEntityExample example);
    RunningOrderEntity selectByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    int updateByExampleWithBLOBs(@Param("record") RunningOrderEntity record, @Param("example") RunningOrderEntityExample example);
    int updateByPrimaryKeySelective(RunningOrderEntity record);
    int updateByPrimaryKeyWithBLOBs(RunningOrderEntity record);
    int updateByPrimaryKey(RunningOrderEntity record);
}