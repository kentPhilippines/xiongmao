package com.payProject.manage.mapper;

import com.payProject.manage.entity.BackBankAmount;
import com.payProject.manage.entity.BackBankAmountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BackBankAmountMapper {
    int countByExample(BackBankAmountExample example);

    int deleteByExample(BackBankAmountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BackBankAmount record);

    int insertSelective(BackBankAmount record);

    List<BackBankAmount> selectByExampleWithBLOBs(BackBankAmountExample example);

    List<BackBankAmount> selectByExample(BackBankAmountExample example);

    BackBankAmount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BackBankAmount record, @Param("example") BackBankAmountExample example);

    int updateByExampleWithBLOBs(@Param("record") BackBankAmount record, @Param("example") BackBankAmountExample example);

    int updateByExample(@Param("record") BackBankAmount record, @Param("example") BackBankAmountExample example);

    int updateByPrimaryKeySelective(BackBankAmount record);

    int updateByPrimaryKeyWithBLOBs(BackBankAmount record);

    int updateByPrimaryKey(BackBankAmount record);
}