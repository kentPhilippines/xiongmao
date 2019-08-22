package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.BankCardEntityExample;
import com.payProject.manage.entity.BankIsDealEntity;
import com.payProject.manage.entity.BankIsDealEntityExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BankIsDealMapper extends  MyMapper<BankIsDealEntity,BankIsDealEntityExample>{
    int deleteByExample(BankIsDealEntityExample example);
    int deleteByPrimaryKey(Integer id);
    BankIsDealEntity selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(BankIsDealEntity record);
    int updateByPrimaryKey(BankIsDealEntity record);
}