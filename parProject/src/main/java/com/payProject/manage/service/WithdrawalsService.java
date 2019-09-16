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

}
