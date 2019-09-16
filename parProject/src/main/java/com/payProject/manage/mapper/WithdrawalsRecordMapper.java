package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.WithdrawalsOrderEntity;
import com.payProject.manage.entity.WithdrawalsOrderEntityExample;
import com.payProject.manage.entity.WithdrawalsRecord;
import com.payProject.manage.entity.WithdrawalsRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface WithdrawalsRecordMapper  extends  MyMapper<WithdrawalsRecord,WithdrawalsRecordExample>{
    int deleteByExample(WithdrawalsRecordExample example);
    int deleteByPrimaryKey(Integer id);
    List<WithdrawalsRecord> selectByExampleWithBLOBs(WithdrawalsRecordExample example);
    WithdrawalsRecord selectByPrimaryKey(Integer id);
    int updateByExampleWithBLOBs(@Param("record") WithdrawalsRecord record, @Param("example") WithdrawalsRecordExample example);
    int updateByPrimaryKeySelective(WithdrawalsRecord record);
    int updateByPrimaryKeyWithBLOBs(WithdrawalsRecord record);
    int updateByPrimaryKey(WithdrawalsRecord record);
    /**
     * <p>根据账户号分页查询交易记录表数据</p>
     * @param example
     * @param accountList
     * @return
     */
	List<WithdrawalsRecord> selectByExampleAndAccountList(WithdrawalsRecordExample example, List<String> accountList);
}