package com.payProject.manage.mapper;

import com.payProject.manage.entity.AccountInfo;
import com.payProject.manage.entity.AccountInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>商户详细信息</p>
 * @author K
 */
@Mapper
public interface AccountInfoMapper {
    int countByExample(AccountInfoExample example);
    int deleteByExample(AccountInfoExample example);
    int deleteByPrimaryKey(Integer id);
    int insert(AccountInfo record);
    int insertSelective(AccountInfo record);
    List<AccountInfo> selectByExampleWithBLOBs(AccountInfoExample example);
    List<AccountInfo> selectByExample(AccountInfoExample example);
    AccountInfo selectByPrimaryKey(Integer id);
    int updateByExampleSelective(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);
    int updateByExampleWithBLOBs(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);
    int updateByExample(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);
    int updateByPrimaryKeySelective(AccountInfo record);
    int updateByPrimaryKeyWithBLOBs(AccountInfo record);
    int updateByPrimaryKey(AccountInfo record);
}