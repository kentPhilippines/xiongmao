package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.BankIsDealEntity;
import com.payProject.manage.entity.BankIsDealEntityExample;
import com.payProject.manage.entity.ExceptionOrderEntity;
import com.payProject.manage.entity.ExceptionOrderEntityExample;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ExceptionOrderMapper extends  MyMapper<ExceptionOrderEntity,ExceptionOrderEntityExample>{
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    List<ExceptionOrderEntity> selectByExampleWithBLOBs(ExceptionOrderEntityExample example);
    ExceptionOrderEntity selectByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    int updateByExampleWithBLOBs(@Param("record") ExceptionOrderEntity record, @Param("example") ExceptionOrderEntityExample example);
    int updateByPrimaryKeySelective(ExceptionOrderEntity record);
    int updateByPrimaryKeyWithBLOBs(ExceptionOrderEntity record);
    int updateByPrimaryKey(ExceptionOrderEntity record);
}