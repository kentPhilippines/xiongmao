package com.payProject.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.config.exception.OtherErrors;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.BankCardEntityExample;
import com.payProject.manage.entity.BankCardEntityExample.Criteria;
import com.payProject.manage.entity.BankIsDealEntity;
import com.payProject.manage.entity.BankIsDealEntityExample;
import com.payProject.manage.mapper.BankCardMapper;
import com.payProject.manage.mapper.BankIsDealMapper;
import com.payProject.manage.service.BankCardService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class BankCardServiceImpl  implements BankCardService{
	@Autowired
	BankCardMapper bankCardDao;
	
	@Autowired
	BankIsDealMapper bankIsDealDao;
	
	public BankCardEntity findBankCardByBankCard(String bankCard) {
		BankCardEntityExample  example = new BankCardEntityExample();
		Criteria criteria = example.createCriteria();
		criteria.andBankCardEqualTo(bankCard);
		List<BankCardEntity> selectByExample = bankCardDao.selectByExample(example);
		if(CollUtil.isEmpty(selectByExample)) 
			return null;
		/**
		 * 根据表设计,银行卡的bankCard不可能重复,所以这里查询的结果集必然是只有一个元素
		 */
		return CollUtil.getFirst(selectByExample);
	}
	@Override
	public Boolean addBankCard(BankCardEntity bankCard) {
		int insertSelective = bankCardDao.insertSelective(bankCard);
		BankIsDealEntity entity = new BankIsDealEntity();
		entity.setBankCardNo(bankCard.getBankCard());
		int insertSelective2 = bankIsDealDao.insertSelective(entity);
		if(!(insertSelective2 > 0 && insertSelective2 < 2 && insertSelective>0 && insertSelective <2) )
			throw new OtherErrors("增加银行卡异常");
		return insertSelective>0 && insertSelective <2 && insertSelective2 > 0 && insertSelective2 < 2;
	}

	public List<BankCardEntity> findPageBankCardByBankCard(BankCardEntity bankCard) {
		BankCardEntityExample example = new BankCardEntityExample(); 
		Criteria criteria = example.createCriteria(); 
		example.setOrderByClause("createTime ASC");
		if(StrUtil.isNotBlank(bankCard.getBankCard()))
			criteria.andBankCardLike(bankCard.getBankCard());
		if(StrUtil.isNotBlank(bankCard.getLiabilities()))
			criteria.andLiabilitiesLike(bankCard.getLiabilities());
		if(null != bankCard.getBankId())
			criteria.andBankIdEqualTo(bankCard.getBankId());
		List<BankCardEntity> selectByExample = bankCardDao.selectByExample(example);
		return selectByExample;
	}

	public BankCardEntity findBankCardByBankCardId() {
		BankCardEntity entity = bankCardDao.findBankCardByBankCardId();
		return entity;
	}
	public boolean deleteBankCardByBankCardNo(String bankCard) {
		BankCardEntityExample example = new BankCardEntityExample(); 
		Criteria criteria = example.createCriteria(); 
		criteria.andBankCardEqualTo(bankCard);
		 int deleteByExample = bankCardDao.deleteByExample(example);
		 BankIsDealEntityExample example1 = new BankIsDealEntityExample();
		 com.payProject.manage.entity.BankIsDealEntityExample.Criteria criteria1 = example1.createCriteria();
		 criteria1.andBankCardNoEqualTo(bankCard);
		 int deleteByExample2 = bankIsDealDao.deleteByExample(example1);
		 if(!(deleteByExample>0 && deleteByExample <2 && deleteByExample2 > 0 && deleteByExample2 <2))
			 throw new OtherErrors("删除银行卡异常");
		return deleteByExample>0 && deleteByExample <2 ;
	}
	public boolean UpdateBankCardByBankCardNo(BankCardEntity bankCard) {
		BankCardEntityExample example = new BankCardEntityExample(); 
		Criteria criteria = example.createCriteria(); 
		criteria.andBankCardEqualTo(bankCard.getBankCard());
		int updateByExampleSelective = bankCardDao.updateByExampleSelective(bankCard, example);
		return updateByExampleSelective >0;
	}
}
