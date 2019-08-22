package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.AccountEntityExample;
import com.payProject.system.entity.Role;
import com.payProject.system.entity.RoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountMapper  extends  MyMapper<AccountEntity,AccountEntityExample>{
    int deleteByExample(AccountEntityExample example);
    int deleteByPrimaryKey(Integer id);
    List<AccountEntity> selectByExample(AccountEntityExample example);
    AccountEntity selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(AccountEntity record);
    int updateByPrimaryKey(AccountEntity record);
}