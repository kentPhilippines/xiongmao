package com.payProject.manage.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.RunOrder;

public interface OrderRunService {

	/**
	 * <p>系统流水交易分页展示</p>
	 * @param runOrder			分页查询条件
	 * @return
	 */
	List<RunOrder> findPageRunOrderByRunOrder(RunOrder runOrder);
	
	
	
	/**
	 *	<p>该商户账户加钱</p>
	 * @param accountId
	 * @param amount
	 * @return
	 */
	boolean addAmount(HttpServletRequest request,AccountEntity accountId,BigDecimal amount);

}
