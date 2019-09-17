package com.payProject.manage.service;

import java.util.List;

import com.payProject.manage.entity.WithdrawalsRecord;

public interface WithdrawalsService {

	/**
	 * <p>申请提提现记录表</p>
	 * @param withdrawals
	 * @return
	 */
	List<WithdrawalsRecord> findPageWithdrawalsByWithdrawals(WithdrawalsRecord withdrawals);

	/**
	 * <p>根据全局订单查询代付订单登记表</p>
	 * @param associatedId
	 * @return
	 */
	WithdrawalsRecord findWithdrawalsRecordByOrderAll(String associatedId);

	/**
	 * <p>修改代付登记表状态</p>
	 * @param record
	 * @return
	 */
	boolean updataOrder(WithdrawalsRecord record);

}
