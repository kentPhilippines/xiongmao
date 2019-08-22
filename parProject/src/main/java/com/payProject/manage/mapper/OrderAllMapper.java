package com.payProject.manage.mapper;
import com.payProject.config.base.mapperBase.MyMapper;
import com.payProject.manage.entity.OrderAllEntity;
import com.payProject.manage.entity.OrderAllEntityExample;
import java.util.Date;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface OrderAllMapper  extends  MyMapper<OrderAllEntity,OrderAllEntityExample>{
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    OrderAllEntity selectByPrimaryKey(@Param("id") Integer id, @Param("createTime") Date createTime);
    int updateByPrimaryKeySelective(OrderAllEntity record);
    int updateByPrimaryKey(OrderAllEntity record);
}