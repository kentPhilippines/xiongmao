package com.payProject.manage.mapper;

import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.WithdrawalsOrderEntity;
import com.payProject.manage.entity.WithdrawalsOrderEntityExample;
import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface WithdrawalsOrderMapper  extends  MyMapper<WithdrawalsOrderEntity,WithdrawalsOrderEntityExample>{
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    WithdrawalsOrderEntity selectByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    int updateByPrimaryKeySelective(WithdrawalsOrderEntity record);
    int updateByPrimaryKey(WithdrawalsOrderEntity record);
    /**
     * <p>查询今日交易总额</p>
     * @param example
     */
	String selectToDaySumAmountByExample(WithdrawalsOrderEntityExample example);
}