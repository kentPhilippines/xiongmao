package com.payProject.manage.service;

import java.util.List;

import com.payProject.manage.entity.RunOrder;

public interface OrderRunService {

	/**
	 * <p>系统流水交易分页展示</p>
	 * @param runOrder			分页查询条件
	 * @return
	 */
	List<RunOrder> findPageRunOrderByRunOrder(RunOrder runOrder);

}
