package com.payProject.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.format.DataFormatDetector;
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.DealOrderEntityExample;
import com.payProject.manage.entity.DealOrderEntityExample.Criteria;
import com.payProject.manage.mapper.DealOrderMapper;
import com.payProject.manage.service.DealOrderService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class DealOrderServiceImpl implements DealOrderService {
	@Autowired
	DealOrderMapper dealOrderDao;
	@Override
	public List<DealOrderEntity> findPageDealOrderByDealOrder(DealOrderEntity dealOrder) {
		DealOrderEntityExample example = new DealOrderEntityExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(dealOrder.getOrderId()))
			criteria.andOrderIdEqualTo(dealOrder.getOrderId());
		if(StrUtil.isNotBlank(dealOrder.getAssociatedId()))
			criteria.andAssociatedIdEqualTo(dealOrder.getAssociatedId());
		if(StrUtil.isNotBlank(dealOrder.getDealCardId()))
			criteria.andDealCardIdEqualTo(dealOrder.getDealCardId());
		if(StrUtil.isNotBlank(dealOrder.getDealChannel()))
			criteria.andDealChannelEqualTo(dealOrder.getDealChannel());
		if(null != dealOrder.getOrderStatus())
			criteria.andOrderStatusEqualTo(dealOrder.getOrderStatus());
		if(StrUtil.isNotBlank(dealOrder.getTime())) {
			String data = StrUtil.subPre(dealOrder.getTime(),10);
			String data1 = StrUtil.subSuf(dealOrder.getTime(),13);
			criteria.andCreateTimeBetween(DateUtil.parse(data), DateUtil.parse(data1));
			} 
		if(CollUtil.isNotEmpty(dealOrder.getAccountList())) 
			criteria.andOrderAccountListEqualTo(dealOrder.getAccountList());
		if(CollUtil.isNotEmpty(dealOrder.getPayTypeList())) 
			criteria.andRetain4ListEqualTo(dealOrder.getPayTypeList());
		List<DealOrderEntity> selectByExample = dealOrderDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public DealOrderEntity findDealOrderByOrderId(String orderId) {
		DealOrderEntityExample example = new DealOrderEntityExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(orderId))
			criteria.andOrderIdEqualTo(orderId);
		List<DealOrderEntity> selectByExample = dealOrderDao.selectByExample(example);
		if(CollUtil.isNotEmpty(selectByExample))
			return CollUtil.getFirst(selectByExample);
		return null;
	}

}
