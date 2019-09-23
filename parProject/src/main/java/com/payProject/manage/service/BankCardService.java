package com.payProject.manage.service;

import java.util.List;

import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.BankCardRunEntity;

/**
 * <p>银行卡数据管理</p>
 * @author K
 */
public interface BankCardService {
	/**
	 * <p>根据银行卡号查询银行卡详细信息</p>
	 * @param bankCard	银行卡号
	 * @return
	 */
	BankCardEntity findBankCardByBankCard(String bankCard);
	/**
	 * <p>增加一个银行卡</p>
	 * @param bankCard			银行卡具体数据
	 * @return
	 */
	Boolean addBankCard(BankCardEntity bankCard);
	/**
	 * <p>分页查询银行卡信息</p>
	 * @param bankCard		模糊查询条件
	 * @return
	 */
	List<BankCardEntity> findPageBankCardByBankCard(BankCardEntity bankCard);
	/**
	 * <p>查询最大的银行卡号(本地编号)</p>
	 * @return				
	 */
	BankCardEntity findBankCardByBankCardId();
	
	/**
	 * <p>根据银行卡号删除一个银行卡信息</p>
	 * @param bankCard			银行卡号
	 * @return
	 */
	boolean deleteBankCardByBankCardNo(String bankCard);
	/**
	 * <p>跟新一个银行卡详细信息,根据银行卡号</p>
	 * @param bankCard	银行卡更改的详细信息
	 * @return
	 */
	boolean UpdateBankCardByBankCardNo(BankCardEntity bankCard);
	/**
	 * <p>修改银行卡的状态未无效</p>
	 * @param bankCard
	 * @return
	 */
	boolean updataBankCard(BankCardEntity bankCard);
	/**
	 * <p>银行卡流水</p>
	 * @param bankCardRun
	 * @return
	 */
	List<BankCardRunEntity> findPageBankCardRunByBankCard(BankCardRunEntity bankCardRun);
	
	/**
	 * <p>根据条件查询银行卡流水</p>
	 * @param bankCardRun
	 * @return
	 */
	List<BankCardRunEntity> findBankCardRunByBankCard(BankCardRunEntity bankCardRun);
	/**
	 * <p>根据银行卡负责人查询该复制人所有的银行卡</p>
	 * @param userId
	 * @return
	 */
	List<BankCardEntity> finBankCardByUserId(String userId);
}
