package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.AccountEntityExample;
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.DealOrderEntityExample;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DealOrderMapper extends  MyMapper<DealOrderEntity,DealOrderEntityExample>{
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    List<DealOrderEntity> selectByExampleWithBLOBs(DealOrderEntityExample example);
    DealOrderEntity selectByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    int updateByExampleWithBLOBs(@Param("record") DealOrderEntity record, @Param("example") DealOrderEntityExample example);
    int updateByPrimaryKeySelective(DealOrderEntity record);
    int updateByPrimaryKeyWithBLOBs(DealOrderEntity record);
    int updateByPrimaryKey(DealOrderEntity record);
}