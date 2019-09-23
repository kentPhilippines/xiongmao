package com.payProject.manage.service.impl;

import java.sql.Struct;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.config.exception.OtherErrors;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.BackBankAmount;
import com.payProject.manage.entity.BackBankAmountExample;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.BankCardEntityExample;
import com.payProject.manage.entity.BankCardEntityExample.Criteria;
import com.payProject.manage.entity.BankCardRunEntity;
import com.payProject.manage.entity.BankCardRunEntityExample;
import com.payProject.manage.entity.BankIsDealEntity;
import com.payProject.manage.entity.BankIsDealEntityExample;
import com.payProject.manage.mapper.BackBankAmountMapper;
import com.payProject.manage.mapper.BankCardMapper;
import com.payProject.manage.mapper.BankCardRunMapper;
import com.payProject.manage.mapper.BankIsDealMapper;
import com.payProject.manage.service.BankCardService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class BankCardServiceImpl  implements BankCardService{
	@Autowired
	BankCardMapper bankCardDao;
	@Autowired
	BankIsDealMapper bankIsDealDao;
	@Autowired
	BackBankAmountMapper BackBankAmountDao;
	@Autowired
	BankCardRunMapper bankCardRunDao;
	
	
	
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
		if(StrUtil.isNotBlank(bankCard.getRetain2()))
			criteria.andRetain2EqualTo(bankCard.getRetain2());
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
	@Override
	public boolean updataBankCard(BankCardEntity bankCard) {
		BankCardEntityExample example = new BankCardEntityExample(); 
		Criteria criteria = example.createCriteria(); 
		criteria.andBankCardEqualTo(bankCard.getBankCard());
		int updateByExampleSelective = bankCardDao.updateByExampleSelective(bankCard, example);
		return updateByExampleSelective > 0 && updateByExampleSelective < 2;
	}
	@Override
	public List<BankCardRunEntity> findPageBankCardRunByBankCard(BankCardRunEntity bankCardRun) {
		BankCardRunEntityExample example = new BankCardRunEntityExample();
		com.payProject.manage.entity.BankCardRunEntityExample.Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(bankCardRun.getWithdrawBankCard()))  
			criteria.andWithdrawBankCardEqualTo(bankCardRun.getWithdrawBankCard());
		if(StrUtil.isNotBlank(bankCardRun.getWithdrawAccount()))  
			criteria.andWithdrawAccountEqualTo(bankCardRun.getWithdrawAccount());
		if(StrUtil.isNotBlank(bankCardRun.getDealBankCard()))  
			criteria.andDealBankCardEqualTo(bankCardRun.getDealBankCard());
		if(StrUtil.isNotBlank(bankCardRun.getDealAccount()))  
			criteria.andDealAccountEqualTo(bankCardRun.getDealAccount());
		if(null != bankCardRun.getRunType())  
			criteria.andRunTypeEqualTo(bankCardRun.getRunType());
		if(CollUtil.isNotEmpty( bankCardRun.getDealBankCardList())&& bankCardRun.getDealBankCardList().size() > 0) {
			criteria.andDealBankCardListEqualTo(bankCardRun.getDealBankCardList());
		}
		if(StrUtil.isNotBlank(bankCardRun.getTime())) {
			String data = StrUtil.subPre(bankCardRun.getTime(),10);
			String data1 = StrUtil.subSuf(bankCardRun.getTime(),12);
			criteria.andCreateTimeBetween(DateUtil.parse(data), DateUtil.parse(data1));
			} 
		List<BankCardRunEntity> selectByExample = bankCardRunDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public List<BankCardRunEntity> findBankCardRunByBankCard(BankCardRunEntity bankCardRun) {
		BankCardRunEntityExample example = new BankCardRunEntityExample();
		com.payProject.manage.entity.BankCardRunEntityExample.Criteria criteria = example.createCriteria();
		if(bankCardRun.getDealBankCardList().size() > 0) 
			criteria.andDealBankCardListEqualTo(bankCardRun.getDealBankCardList());
		if(bankCardRun.getRunTypeList().size() >0)
			criteria.andRunTypeListEqualTo(bankCardRun.getRunTypeList());
		List<BankCardRunEntity> selectByExample = bankCardRunDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public List<BankCardEntity> finBankCardByUserId(String userId) {
		BankCardEntityExample example = new BankCardEntityExample(); 
		Criteria criteria = example.createCriteria();
		criteria.andLiabilitiesEqualTo(userId);
		List<BankCardEntity> selectByExample = bankCardDao.selectByExample(example);		
		return selectByExample;
	}
	@Override
	public List<BackBankAmount> findPageBackBankAmountByBank(BackBankAmount bank) {
		BackBankAmountExample example = new BackBankAmountExample();
		com.payProject.manage.entity.BackBankAmountExample.Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank( bank.getAccountId()))
			criteria.andAccountIdEqualTo( bank.getAccountId());
		if(StrUtil.isNotBlank( bank.getOrderId()))
			criteria.andOrderIdEqualTo( bank.getOrderId());
		if(StrUtil.isNotBlank(bank.getBankR()))
			criteria.andBankREqualTo(bank.getBankR());
		if(StrUtil.isNotBlank(bank.getBankD()))
			criteria.andBankDEqualTo(bank.getBankD());
		if(StrUtil.isNotBlank(bank.getTime())) {
			String data = StrUtil.subPre(bank.getTime(),10);
			String data1 = StrUtil.subSuf(bank.getTime(),12);
			criteria.andCreateTimeBetween(DateUtil.parse(data), DateUtil.parse(data1));
			} 
		List<BackBankAmount> selectByExample = BackBankAmountDao.selectByExample(example);
		
		return selectByExample;
	}
}
