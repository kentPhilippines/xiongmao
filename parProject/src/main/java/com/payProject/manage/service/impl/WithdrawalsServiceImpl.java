package com.payProject.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.manage.entity.WithdrawalsOrderEntity;
import com.payProject.manage.entity.WithdrawalsRecord;
import com.payProject.manage.entity.WithdrawalsRecordExample;
import com.payProject.manage.entity.WithdrawalsRecordExample.Criteria;
import com.payProject.manage.mapper.WithdrawalsRecordMapper;
import com.payProject.manage.service.WithdrawalsService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

@Service
public class WithdrawalsServiceImpl implements WithdrawalsService{
	@Autowired
	WithdrawalsRecordMapper withdrawalsRecordDao;
	@Override
	public List<WithdrawalsRecord> findPageWithdrawalsByWithdrawals(WithdrawalsRecord withdrawals) {
		WithdrawalsRecordExample example = new WithdrawalsRecordExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(withdrawals.getAccountId()))
			criteria.andAccountIdEqualTo(withdrawals.getAccountId());
		if(StrUtil.isNotBlank(withdrawals.getOrderId()))
			criteria.andOrderIdEqualTo(withdrawals.getOrderId());
		if(StrUtil.isNotBlank(withdrawals.getRetain1()))
			criteria.andRetain1EqualTo(withdrawals.getRetain1());
		if(CollUtil.isNotEmpty(withdrawals.getAccountList())) {
			List<String> accountList = withdrawals.getAccountList();
			criteria.andAccountListEqualTo(accountList);
		}
		List<WithdrawalsRecord> selectByExample = withdrawalsRecordDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public WithdrawalsRecord findWithdrawalsRecordByOrderAll(String associatedId) {
		WithdrawalsRecordExample example = new WithdrawalsRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andAssociatedIdEqualTo(associatedId);
		List<WithdrawalsRecord> selectByExample = withdrawalsRecordDao.selectByExample(example);
		if(CollUtil.isNotEmpty(selectByExample)) {
			return CollUtil.getFirst(selectByExample);
		}
		return null;
	}
	@Override
	public boolean updataOrder(WithdrawalsRecord record) {
		WithdrawalsRecordExample example = new WithdrawalsRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(record.getId());
		int updateByExample = withdrawalsRecordDao.updateByExample(record, example);
		return updateByExample > 0 && updateByExample < 2;
	}
}
