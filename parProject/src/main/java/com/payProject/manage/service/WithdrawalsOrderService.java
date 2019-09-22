package com.payProject.manage.service;

import java.math.BigDecimal;
import java.util.List;

import com.payProject.manage.entity.WithdrawalsOrderEntity;

public interface WithdrawalsOrderService {

	/**
	 * <p>分页查询代付订单数据</p>
	 * @param withdrawalsOrder			查询条件实体类
	 * @return
	 */
	List<WithdrawalsOrderEntity> findPageWithdrawalsByWithdrawals(WithdrawalsOrderEntity withdrawalsOrder);

	/**
	 * <p>根据数据id查询数据</p>
	 * @param id
	 * @return
	 */
	WithdrawalsOrderEntity findWithdrawalsByid(String id);

	/**
	 * <p>获取账户今日交易总额</p>
	 * @param string 
	 * @return
	 */
	BigDecimal findToDaySumAmount(String string);

	/**
	 * <p>根据代付订单号查询代付订单</p>
	 * @param orderId
	 * @return
	 */
	WithdrawalsOrderEntity findWithdrawalsByOrderId(String orderId);

	/**
	 * <p>修改代付订单状态</p>
	 * @param order
	 * @return
	 */
	boolean updataOrder(WithdrawalsOrderEntity order);

	/**
	 * <p>商户分页查询代付订单</p>
	 * @param withdrawalsOrder
	 * @return
	 */
	List<WithdrawalsOrderEntity> findPageWithdrawalsByWithdrawals1(WithdrawalsOrderEntity withdrawalsOrder);

}
