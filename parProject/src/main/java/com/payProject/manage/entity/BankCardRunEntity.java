package com.payProject.manage.entity;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;
/**
 * <p>银行卡出入金登记表</p>
 * @author K
 */
public class BankCardRunEntity extends BaseEntity<BankCardRunEntity>{
	private static final long serialVersionUID = -4523538365602853694L;
	/**
	 * <p>出金银行卡</p>
	 */
	private String withdrawBankCard;
	/**
	 * <p>出金本地账号</p>
	 */
    private String withdrawAccount;
    /**
	 * <p>出金金额</p>
	 */
    private BigDecimal withdrawAmount;
    /**
	 * <p>交易银行卡(入金)</p>
	 */
    private String dealBankCard;
    /**
	 * <p>交易本地账户(入金)</p>
	 */
    private String dealAccount;
    /**
	 * <p>交易金额(入金)</p>
	 */
    private BigDecimal dealAmount;
    /**
	 * <p>流水类型</p>
	 */
    private Integer runType;
    
    private String Time;
    
    
    public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	private String retain1;

    private String retain2;

    private String retain3;

    private String retain4;

    private String retain5;

    private String retain6;

    private String retain7;



    public String getWithdrawBankCard() {
        return withdrawBankCard;
    }

    public void setWithdrawBankCard(String withdrawBankCard) {
        this.withdrawBankCard = withdrawBankCard == null ? null : withdrawBankCard.trim();
    }

    public String getWithdrawAccount() {
        return withdrawAccount;
    }

    public void setWithdrawAccount(String withdrawAccount) {
        this.withdrawAccount = withdrawAccount == null ? null : withdrawAccount.trim();
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getDealBankCard() {
        return dealBankCard;
    }

    public void setDealBankCard(String dealBankCard) {
        this.dealBankCard = dealBankCard == null ? null : dealBankCard.trim();
    }

    public String getDealAccount() {
        return dealAccount;
    }

    public void setDealAccount(String dealAccount) {
        this.dealAccount = dealAccount == null ? null : dealAccount.trim();
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    public Integer getRunType() {
        return runType;
    }

    public void setRunType(Integer runType) {
        this.runType = runType;
    }


    public String getRetain1() {
        return retain1;
    }

    public void setRetain1(String retain1) {
        this.retain1 = retain1 == null ? null : retain1.trim();
    }

    public String getRetain2() {
        return retain2;
    }

    public void setRetain2(String retain2) {
        this.retain2 = retain2 == null ? null : retain2.trim();
    }

    public String getRetain3() {
        return retain3;
    }

    public void setRetain3(String retain3) {
        this.retain3 = retain3 == null ? null : retain3.trim();
    }

    public String getRetain4() {
        return retain4;
    }

    public void setRetain4(String retain4) {
        this.retain4 = retain4 == null ? null : retain4.trim();
    }

    public String getRetain5() {
        return retain5;
    }

    public void setRetain5(String retain5) {
        this.retain5 = retain5 == null ? null : retain5.trim();
    }

    public String getRetain6() {
        return retain6;
    }

    public void setRetain6(String retain6) {
        this.retain6 = retain6 == null ? null : retain6.trim();
    }

    public String getRetain7() {
        return retain7;
    }

    public void setRetain7(String retain7) {
        this.retain7 = retain7 == null ? null : retain7.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}