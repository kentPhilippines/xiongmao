package com.payProject.manage.service;

import java.util.List;

import com.payProject.manage.entity.DealOrderEntity;

public interface DealOrderService {

	/**
	 * <p>分页查询交易数据</p>
	 * @param dealOrder			查询条件
	 * @return
	 */
	List<DealOrderEntity> findPageDealOrderByDealOrder(DealOrderEntity dealOrder);

	/**
	 * <p>根据订单号查询订单具体信息</p>
	 * @param orderId
	 * @return
	 */
	DealOrderEntity findDealOrderByOrderId(String orderId);

}
