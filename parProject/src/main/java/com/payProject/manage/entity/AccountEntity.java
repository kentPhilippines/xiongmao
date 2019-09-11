package com.payProject.manage.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class AccountEntity  extends BaseEntity<AccountEntity>{
	 	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * <p>账户id</p>
		 * <p>下游商户</p>
		 */
		private String accountId;
		/**
		 * <p>账户name</p>
		 * <p>下游商户</p>
		 */
	    private String accountName;
	    /**
		 * <p>账户类型</p>
		 * <p>下游商户</p>
		 */
	    private Integer accountType;
	    /**
		 * <p>账户可提现</p>
		 * <p>下游商户</p>
		 */
	    private BigDecimal cashBalance;
	    /**
		 * <p>商户冻结资金</p>
		 * <p>下游商户</p>
		 */	
	    private BigDecimal freezeBalance;
	    /**
		 * <p>商户账户余额=冻结金额+可提现余额</p>
		 * <p>下游商户</p>
		 */		
	    private BigDecimal accountBalance;
	    /**
		 * <p>商户日交易(充值)额度最大</p>
		 * <p>下游商户</p>
		 */
	    private BigDecimal dayDealAmountMax;
	    /**
		 * <p>商户日交易(充值)额度最小</p>
		 * <p>下游商户</p>
		 */
	    private BigDecimal dayDealAmountMin;
	    /**
		 * <p>累计交易</p>
		 * <p>下游商户</p>
		 */
	    private BigDecimal sumDealAmount;
	    /**
		 * <p>累计交易（当天）</p>
		 * <p>下游商户</p>
		 */
	    private BigDecimal sumDealToDayAmount;

	    /**
		 * <p>加款描述</p>
		 * <p>匹配流水表</p>
		 */
	    private String dealDescribe;
	    /**
	     * <p>加款金额</p>
	     */
	    private String amount;
	    /**
	     * <p>是否开通交易服务</p>
	     * <p>1开通</p>
	     * <p>0暂停</p>
	     */
	    private Integer isDeal;
	    /**
	     * <p>是否开通代付服务</p>
	     * <p>1开通</p>
	     * <p>0暂停</p>
	     */
	    private Integer isDpay;
	    
	    
	    public Integer getIsDpay() {
			return isDpay;
		}

		public void setIsDpay(Integer isDpay) {
			this.isDpay = isDpay;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public Integer getIsDeal() {
			return isDeal;
		}

		public void setIsDeal(Integer isDeal) {
			this.isDeal = isDeal;
		}

		public String getDealDescribe() {
			return dealDescribe;
		}

		public void setDealDescribe(String dealDescribe) {
			this.dealDescribe = dealDescribe;
		}

	public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }

    public BigDecimal getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(BigDecimal freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getDayDealAmountMax() {
        return dayDealAmountMax;
    }

    public void setDayDealAmountMax(BigDecimal dayDealAmountMax) {
        this.dayDealAmountMax = dayDealAmountMax;
    }

    public BigDecimal getDayDealAmountMin() {
        return dayDealAmountMin;
    }

    public void setDayDealAmountMin(BigDecimal dayDealAmountMin) {
        this.dayDealAmountMin = dayDealAmountMin;
    }

    public BigDecimal getSumDealAmount() {
        return sumDealAmount;
    }

    public void setSumDealAmount(BigDecimal sumDealAmount) {
        this.sumDealAmount = sumDealAmount;
    }

    public BigDecimal getSumDealToDayAmount() {
        return sumDealToDayAmount;
    }

    public void setSumDealToDayAmount(BigDecimal sumDealToDayAmount) {
        this.sumDealToDayAmount = sumDealToDayAmount;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}