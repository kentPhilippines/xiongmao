package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.AccountEntityExample;
import com.payProject.manage.entity.AccountInfoExample;
import com.payProject.manage.entity.BankIsDealEntity;
import com.payProject.manage.entity.BankIsDealEntityExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountMapper extends  MyMapper<AccountEntity,AccountEntityExample>{
	int deleteByExample(AccountEntityExample example);
    int deleteByPrimaryKey(Integer id);
    AccountEntity selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(AccountEntity record);
    int updateByPrimaryKey(AccountEntity record);
    /**
     * <p>根据商户号集合查询对应的商户号取款银行卡</p>
     * @param accountList
     * @return
     */
	List<AccountEntity> findBankCardByAccountId(List<String> accountList);
}