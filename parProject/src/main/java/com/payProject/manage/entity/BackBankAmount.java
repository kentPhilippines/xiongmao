package com.payProject.manage.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class BackBankAmount extends BaseEntity<BackBankAmount> {
	/**
	 *<p>账户id</p>
	 */
    private String accountId;
    /**
	 *<p>订单id</p>
	 */
    private String orderId;
    /**
	 *<p>金额</p>
	 */
    private BigDecimal amount;
    /**
	 *<p>ip</p>
	 */
    private String ip;
    /**
	 *<p>审核人</p>
	 */
    private String approver;
    /**
	 *<p>入款卡</p>
	 */
    private String bankR;
    /**
	 *<p>出款卡</p>
	 */
    private String bankD;
    /**
	 *<p>状态</p>
	 */
    private Integer bankStatus;
    /**
     * <p>搜索时间</p>
     */
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
    private String note;//备注


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public String getBankR() {
        return bankR;
    }

    public void setBankR(String bankR) {
        this.bankR = bankR == null ? null : bankR.trim();
    }

    public String getBankD() {
        return bankD;
    }

    public void setBankD(String bankD) {
        this.bankD = bankD == null ? null : bankD.trim();
    }

    public Integer getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(Integer bankStatus) {
        this.bankStatus = bankStatus;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}