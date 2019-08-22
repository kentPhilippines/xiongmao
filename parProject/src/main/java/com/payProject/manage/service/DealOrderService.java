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

}
