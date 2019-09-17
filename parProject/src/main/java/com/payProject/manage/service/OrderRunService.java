package com.payProject.manage.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.RunOrder;
import com.payProject.manage.entity.WithdrawalsOrderEntity;

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



	/**
	 * <p>商户账户冻结资金</p>
	 * @param request
	 * @param findAccountByAccountId
	 * @param amountB
	 * @return
	 */
	boolean delAmount(HttpServletRequest request, AccountEntity findAccountByAccountId, BigDecimal amountB);



	/**
	 * <p>商户资金冻结</p>
	 * @param request
	 * @param findAccountByAccountId
	 * @param amountB
	 * @return
	 */
	boolean freAmount(HttpServletRequest request, AccountEntity findAccountByAccountId, BigDecimal amountB);



	/**
	 * <p>根据代付订单号拆线流水</p>
	 * @param orderId
	 * @return
	 */
	List<RunOrder> findOrderRunByWitOrder(String orderId);



	/**
	 * <p>根据流水集合修改流水状态</p>
	 * @param runList
	 * @return
	 */
	boolean updataRunType(List<RunOrder> runList);
	/**
	 * <p>根据订单新建流水</p>
	 * <p>该订单为代付订单</p>
	 * @param order
	 * @return
	 */
	boolean createAmount(WithdrawalsOrderEntity order);

}
