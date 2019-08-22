package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.BankCardRunEntity;
import com.payProject.manage.entity.BankCardRunEntityExample;
import com.payProject.manage.entity.BankIsDealEntity;
import com.payProject.manage.entity.BankIsDealEntityExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BankCardRunMapper extends  MyMapper<BankCardRunEntity,BankCardRunEntityExample>{
    int deleteByExample(BankCardRunEntityExample example);
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    BankCardRunEntity selectByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    int updateByPrimaryKeySelective(BankCardRunEntity record);
    int updateByPrimaryKey(BankCardRunEntity record);
}