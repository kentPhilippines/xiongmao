package com.payProject.manage.service.impl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.payProject.manage.entity.WithdrawalsOrderEntity;
import com.payProject.manage.entity.WithdrawalsOrderEntityExample;
import com.payProject.manage.entity.WithdrawalsOrderEntityExample.Criteria;
import com.payProject.manage.mapper.WithdrawalsOrderMapper;
import com.payProject.manage.service.WithdrawalsOrderService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class WithdrawalsOrderServiceImpl implements WithdrawalsOrderService {

	@Autowired
	WithdrawalsOrderMapper withdrawalsOrderDao;
	@Override
	public List<WithdrawalsOrderEntity> findPageWithdrawalsByWithdrawals(WithdrawalsOrderEntity withdrawalsOrder) {
		WithdrawalsOrderEntityExample example  = new WithdrawalsOrderEntityExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(withdrawalsOrder.getOrderId()))
			criteria.andOrderIdEqualTo(withdrawalsOrder.getOrderId());
		if(StrUtil.isNotBlank(withdrawalsOrder.getAssociatedId()))
			criteria.andAssociatedIdEqualTo(withdrawalsOrder.getAssociatedId());
		if(StrUtil.isNotBlank(withdrawalsOrder.getBankCard()))
			criteria.andBankCardEqualTo(withdrawalsOrder.getBankCard());
		if(StrUtil.isNotBlank(withdrawalsOrder.getOrderAccount()))
			criteria.andOrderAccountEqualTo(withdrawalsOrder.getOrderAccount());
		if( null != withdrawalsOrder.getOrderStatus())
			criteria.andOrderStatusEqualTo(withdrawalsOrder.getOrderStatus());
		if(StrUtil.isNotBlank(withdrawalsOrder.getTime())) {
			String data = StrUtil.subPre(withdrawalsOrder.getTime(),10);
			String data1 = StrUtil.subSuf(withdrawalsOrder.getTime(),12);
			criteria.andCreateTimeBetween(DateUtil.parse(data), DateUtil.parse(data1));
			} 
		List<WithdrawalsOrderEntity> selectByExample = withdrawalsOrderDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public WithdrawalsOrderEntity findWithdrawalsByid(String id) {
		WithdrawalsOrderEntityExample example  = new WithdrawalsOrderEntityExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(Integer.valueOf(id));
		 List<WithdrawalsOrderEntity> selectByExample = withdrawalsOrderDao.selectByExample(example);
		 if(CollUtil.isNotEmpty(selectByExample)) {
			 return CollUtil.getFirst(selectByExample);
		 }
		return null;
	}
	@Override
	public BigDecimal findToDaySumAmount(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		Date date = DateUtil.parse(format);
		Date beginOfDay = DateUtil.beginOfDay(date);
		Date endOfDay = DateUtil.endOfDay(date);
		WithdrawalsOrderEntityExample example  = new WithdrawalsOrderEntityExample();
		Criteria criteria = example.createCriteria();
		criteria.andCreateTimeBetween(beginOfDay, endOfDay);
		criteria.andOrderAccountEqualTo(string);
		String todayAmount = withdrawalsOrderDao.selectToDaySumAmountByExample(example);
		return new BigDecimal(StrUtil.isBlank(todayAmount)?"0":todayAmount);
	}
	@Override
	public WithdrawalsOrderEntity findWithdrawalsByOrderId(String orderId) {
		WithdrawalsOrderEntityExample example  = new WithdrawalsOrderEntityExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<WithdrawalsOrderEntity> selectByExample = withdrawalsOrderDao.selectByExample(example);
		if(CollUtil.isNotEmpty(selectByExample)) {
			return CollUtil.getFirst(selectByExample);
		}
		return null;
	}
	@Override
	public boolean updataOrder(WithdrawalsOrderEntity order) {
		WithdrawalsOrderEntityExample example  = new WithdrawalsOrderEntityExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(order.getId());
		int updateByExample = withdrawalsOrderDao.updateByExample(order, example);
		return updateByExample > 0 && updateByExample < 2;
	}
	@Override
	public List<WithdrawalsOrderEntity> findPageWithdrawalsByWithdrawals1(WithdrawalsOrderEntity withdrawalsOrder) {
		WithdrawalsOrderEntityExample example  = new WithdrawalsOrderEntityExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(withdrawalsOrder.getOrderId()))
			criteria.andOrderIdEqualTo(withdrawalsOrder.getOrderId());
		if(StrUtil.isNotBlank(withdrawalsOrder.getAssociatedId()))
			criteria.andAssociatedIdEqualTo(withdrawalsOrder.getAssociatedId());
		if(StrUtil.isNotBlank(withdrawalsOrder.getBankCard()))
			criteria.andBankCardEqualTo(withdrawalsOrder.getBankCard());
		if(StrUtil.isNotBlank(withdrawalsOrder.getOrderAccount()))
			criteria.andOrderAccountEqualTo(withdrawalsOrder.getOrderAccount());
		if( null != withdrawalsOrder.getOrderStatus())
			criteria.andOrderStatusEqualTo(withdrawalsOrder.getOrderStatus());
		if(StrUtil.isNotBlank(withdrawalsOrder.getTime())) {
			String data = StrUtil.subPre(withdrawalsOrder.getTime(),10);
			String data1 = StrUtil.subSuf(withdrawalsOrder.getTime(),12);
			criteria.andCreateTimeBetween(DateUtil.parse(data), DateUtil.parse(data1));
			} 
		if(CollUtil.isNotEmpty(withdrawalsOrder.getAccountList())) {
			List<String> accountList = withdrawalsOrder.getAccountList();
			criteria.andAccountListEqualTo(accountList);
		}else {
			return new ArrayList<WithdrawalsOrderEntity>();
		} 
		List<WithdrawalsOrderEntity> selectByExample = withdrawalsOrderDao.selectByExample(example);
		return selectByExample;
	}

}
