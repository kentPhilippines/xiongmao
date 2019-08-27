package com.payProject.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.manage.entity.RunOrder;
import com.payProject.manage.entity.RunOrderExample;
import com.payProject.manage.entity.RunOrderExample.Criteria;
import com.payProject.manage.mapper.RunOrderMapper;
import com.payProject.manage.service.OrderRunService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class OrderRunServiceImpl implements OrderRunService {
	@Autowired
	RunOrderMapper  runOrderDao;
	@Override
	public List<RunOrder> findPageRunOrderByRunOrder(RunOrder runOrder) {
		RunOrderExample example  = new RunOrderExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(runOrder.getOrderRunId()))
			criteria.andOrderRunIdEqualTo(runOrder.getOrderRunId());
		if(StrUtil.isNotBlank(runOrder.getOrderId()))
			criteria.andOrderIdEqualTo(runOrder.getOrderId());
		if(StrUtil.isNotBlank(runOrder.getOrderAccount()))
			criteria.andOrderAccountEqualTo(runOrder.getOrderAccount());
		if(StrUtil.isNotBlank(runOrder.getCardNameRunD()))
			criteria.andCardNameRunDEqualTo(runOrder.getCardNameRunD());
		if(StrUtil.isNotBlank(runOrder.getCardNameRunW()))
			criteria.andCardNameRunWEqualTo(runOrder.getCardNameRunW());
		if(StrUtil.isNotBlank(runOrder.getCardRunW()))
			criteria.andCardRunWEqualTo(runOrder.getCardRunW());
		if(StrUtil.isNotBlank(runOrder.getCardRunD()))
			criteria.andCardRunDEqualTo(runOrder.getCardRunD());
		if( null != runOrder.getRunStatus())
			criteria.andRunStatusEqualTo(runOrder.getRunStatus());
		if( null != runOrder.getRunType())
			criteria.andRunTypeEqualTo(runOrder.getRunType());
		if(StrUtil.isNotBlank(runOrder.getTime())) {
			String data = StrUtil.subPre(runOrder.getTime(),10);
			String data1 = StrUtil.subSuf(runOrder.getTime(),12);
			criteria.andCreateTimeBetween(DateUtil.parse(data), DateUtil.parse(data1));
			} 
		List<RunOrder> selectByExample = runOrderDao.selectByExample(example);
		return selectByExample;
	}

}
