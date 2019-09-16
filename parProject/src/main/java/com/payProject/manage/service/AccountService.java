package com.payProject.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.AccountFee;
import com.payProject.manage.entity.AccountInfo;

public interface AccountService {

	/**
	 * <p>分页查询商户资料</p>
	 * @param account
	 * @return
	 */
	List<AccountEntity> findPageAccountByAccount(AccountEntity account);

	/**
	 * <p>增加一个用户的基本信息</p>
	 * @param entity
	 * @return
	 */
	boolean addAccount(AccountEntity entity);

	/**
	 * <p>增加一个用户的详细信息</p>
	 * @param entityInfo
	 * @return
	 */
	boolean addAccountInfo(AccountInfo entityInfo);

	/**
	 * <p>根据时间查询最新的一个新建的商户号账号</p>
	 * @return
	 */
	AccountEntity findAccountByNo();

	/**
	 * <p>根据商户账号查找商户</p>
	 * @param accountId
	 * @return
	 */
	AccountEntity findAccountByAccountId(String accountId);

	/**
	 * <p>根据商户号查询详细的商户信息</p>
	 * @param accountId
	 * @return
	 */
	AccountInfo findAccountInfoByNo(String accountId);

	/**
	 * <p>根据商户号修改是否该商户有代理商功能</p>
	 * @param accountId
	 * @return
	 */
	boolean upDataAccountIsAgant(String accountId, String isAgant);

	/**
	 * <p>根据id删除一个商户</p>
	 * @param id
	 * @return
	 */
	boolean deleteAccount(String id);

	/**
	 * <p>分页查询商户费率列表</p>
	 * @param accountFee
	 * @return
	 */
	List<AccountFee> findPageAccountFeeByAccountFee(AccountFee accountFee);

	/**
	 * <p>增加一个商户费率</p>
	 * @param account
	 * @return
	 */
	Boolean addAccountFee(AccountFee account);

	/**
	 * <p>根据输入的值模糊查询</p>
	 * @param accountFee
	 * @return
	 */
	List<AccountEntity> findAccountIdLike(AccountEntity account);

	/**
	 * <p>查询所有用户</p>
	 * @return
	 */
	List<AccountEntity> findAccountAll();

	/**
	 * <p>查询是否存在唯一的费率</p>
	 * @param accountChannel	商户费率渠道
	 * @param accountId			商户id
	 * @param channelProduct	商户费率产品
	 * @param integer 			费率状态
	 * @return		
	 */
	AccountFee findAccountFeeByAll(String accountChannel, String accountId, String channelProduct, Integer integer);

	/**
	 * <p>删除一个费率根据数据id</p>
	 * @param account
	 * @return
	 */
	Boolean deleteAccountFee(AccountFee account);

	/**
	 * <p>根据数据的id来数据的详细信息</p>
	 * @param id
	 * @return
	 */
	AccountFee findAccountFeeById(Integer id);

	/**
	 * <p>根据数据id修改一条数据</p>
	 * @param account
	 * @return
	 */
	Boolean updataAccountFee(AccountFee account);

	/**
	 * <p>根据商户号修改商户信息</p>
	 * @param account
	 * @return
	 */
	Boolean updataAccount(AccountEntity account);

	/**
	 * <p>商户加钱</p>
	 * @param account
	 * @return
	 */
	Boolean addAmount(HttpServletRequest request,AccountEntity account);

	/**
	 * <p>商户冻结资金</p>
	 * @param request
	 * @param account
	 * @return
	 */
	Boolean delAmount(HttpServletRequest request, AccountEntity account);

	/**
	 * <p>商户资金冻结</p>
	 * @param request
	 * @param account
	 * @return
	 */
	Boolean freAmount(HttpServletRequest request, AccountEntity account);

	/**
	 * <p>根据费率状态和Appid查询唯一费率</p>
	 * <p>当前账户只存在唯一一条费率状态为可使用,其他全部为 不可使用</p>
	 * @param accountId	 账户
	 * @param feeStatus1  费率状态
	 * @return
	 */
	AccountFee findAccountFeeBy(String accountId, Integer feeStatus1);

	/**
	 * <p>根据商户号集合查询所对应的取款银行卡</p>
	 * @param accountList
	 * @return
	 */
	List<AccountEntity> findBankCardByAccountId(List<String> accountList);

}
