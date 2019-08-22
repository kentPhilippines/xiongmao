package com.payProject.manage.service;

import java.util.List;

import com.payProject.manage.entity.WithdrawalsOrderEntity;

public interface WithdrawalsOrderService {

	/**
	 * <p>分页查询代付订单数据</p>
	 * @param withdrawalsOrder			查询条件实体类
	 * @return
	 */
	List<WithdrawalsOrderEntity> findPageWithdrawalsByWithdrawals(WithdrawalsOrderEntity withdrawalsOrder);

}
