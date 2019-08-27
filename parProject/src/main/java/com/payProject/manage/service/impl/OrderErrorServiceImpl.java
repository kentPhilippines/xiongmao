package com.payProject.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.manage.entity.ExceptionOrderEntity;
import com.payProject.manage.entity.ExceptionOrderEntityExample;
import com.payProject.manage.entity.ExceptionOrderEntityExample.Criteria;
import com.payProject.manage.mapper.ExceptionOrderMapper;
import com.payProject.manage.service.OrderErrorService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

@Service
public class OrderErrorServiceImpl implements OrderErrorService{
	@Autowired
	ExceptionOrderMapper exceptionOrderDao;

	@Override
	public List<ExceptionOrderEntity> findPageOrderErrorByOrderError(ExceptionOrderEntity orderError) {
		ExceptionOrderEntityExample example  = new ExceptionOrderEntityExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(orderError.getOrderId()))
			criteria.andOrderIdEqualTo(orderError.getOrderId());
		if(StrUtil.isNotBlank(orderError.getOrderExceptId()))
			criteria.andOrderExceptIdEqualTo(orderError.getOrderExceptId());
		if(StrUtil.isNotBlank(orderError.getOrderAccount()))
			criteria.andOrderAccountEqualTo(orderError.getOrderAccount());
		if( null != orderError.getExceptType())
			criteria.andExceptTypeEqualTo(orderError.getExceptType());
		if( null != orderError.getExceptStatus())
			criteria.andExceptStatusEqualTo(orderError.getExceptStatus());
		if(StrUtil.isNotBlank(orderError.getTime())) {
			String data = StrUtil.subPre(orderError.getTime(),10);
			String data1 = StrUtil.subSuf(orderError.getTime(),12);
			criteria.andCreateTimeBetween(DateUtil.parse(data), DateUtil.parse(data1));
			} 
		List<ExceptionOrderEntity> selectByExample = exceptionOrderDao.selectByExample(example);
		return selectByExample;
	}
		
		
		
	
	
	

}
