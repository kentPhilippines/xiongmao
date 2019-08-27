package com.payProject.manage.mapper;

import com.payProject.manage.entity.ChannelFee;
import com.payProject.manage.entity.ChannelFeeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ChannelFeeMapper {
    int countByExample(ChannelFeeExample example);

    int deleteByExample(ChannelFeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChannelFee record);

    int insertSelective(ChannelFee record);

    List<ChannelFee> selectByExample(ChannelFeeExample example);

    ChannelFee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChannelFee record, @Param("example") ChannelFeeExample example);

    int updateByExample(@Param("record") ChannelFee record, @Param("example") ChannelFeeExample example);

    int updateByPrimaryKeySelective(ChannelFee record);

    int updateByPrimaryKey(ChannelFee record);
}