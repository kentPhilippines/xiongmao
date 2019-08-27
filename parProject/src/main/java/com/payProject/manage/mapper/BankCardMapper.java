package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.BankCardEntityExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BankCardMapper extends  MyMapper<BankCardEntity,BankCardEntityExample>{
    int deleteByExample(BankCardEntityExample example);
    int deleteByPrimaryKey(Integer id);
    List<BankCardEntity> selectByExampleWithBLOBs(BankCardEntityExample example);
    BankCardEntity selectByPrimaryKey(Integer id);
    int updateByExampleWithBLOBs(@Param("record") BankCardEntity record, @Param("example") BankCardEntityExample example);
    int updateByPrimaryKeySelective(BankCardEntity record);
    int updateByPrimaryKeyWithBLOBs(BankCardEntity record);
    int updateByPrimaryKey(BankCardEntity record);
    
    
    /**
     * <p>查询当前最大的银行卡本地编号</p>
     * @return
     */
	BankCardEntity findBankCardByBankCardId();
    
    
    
    
    
}