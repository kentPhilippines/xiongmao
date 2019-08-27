package com.payProject.manage.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;
/**
 * <p>取款记录订单表</p>
 * @author K
 *
 */
public class WithdrawalsOrderEntity extends BaseEntity<WithdrawalsOrderEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5062944861961737925L;
	/**
	 * <p>订单单号</p>
	 */
    private String orderId;
    /**
     * <p>关联订单单号</p>
     */
    private String associatedId;
    /**
     * <p>订单状态:1处理中,2成功,3失败</p>
     */
    private Integer orderStatus;
    /**
     * <p>订单代付金额</p>
     */
    private BigDecimal withdrawalsAmount;
    /**
     * <p>代付订单手续费</p>
     */
    private BigDecimal withdrawalsFee;
    /**
     * <p>代付实际到账金额（计算过后）</p>
     */
    private BigDecimal actualAmount;
    /**
     * <p>订单类型:1下游代付，2补充代付'</p>
     */
    private Integer orderType;
    /**
     * <p>订单关联商户账号</p>
     */
    private String orderAccount;
    /**
     * <p>外部订单号(下游商户请求参数,用户数据回调)</p>
     */
    private String externalOrderId;
    /**
     * <p>订单生成IP(客户端ip或者是下游商户id)</p>
     */
    private String orderGenerationIp;
    /**
     * <p>代付银行卡</p>
     */
    private String bankCard;
    /**
     * <p>代付银行卡持卡人</p>
     */
    private String cardholder;
    /**
     * <p>商户交易渠道</p>
     */
    private String dealChannel;

    /**
     * <p>时间范围</p>
     */
    private String Time;
    
    private String retain1;
    private String retain2;
    private String retain3;
    private String retain4;
    private String retain5;
    private String retain6;
    private String retain7;
    private String retain8;
    private String retain9;
    
    public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(String associatedId) {
        this.associatedId = associatedId == null ? null : associatedId.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getWithdrawalsAmount() {
        return withdrawalsAmount;
    }

    public void setWithdrawalsAmount(BigDecimal withdrawalsAmount) {
        this.withdrawalsAmount = withdrawalsAmount;
    }

    public BigDecimal getWithdrawalsFee() {
        return withdrawalsFee;
    }

    public void setWithdrawalsFee(BigDecimal withdrawalsFee) {
        this.withdrawalsFee = withdrawalsFee;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount == null ? null : orderAccount.trim();
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId == null ? null : externalOrderId.trim();
    }

    public String getOrderGenerationIp() {
        return orderGenerationIp;
    }

    public void setOrderGenerationIp(String orderGenerationIp) {
        this.orderGenerationIp = orderGenerationIp == null ? null : orderGenerationIp.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder == null ? null : cardholder.trim();
    }

    public String getDealChannel() {
        return dealChannel;
    }

    public void setDealChannel(String dealChannel) {
        this.dealChannel = dealChannel == null ? null : dealChannel.trim();
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

    public String getRetain8() {
        return retain8;
    }

    public void setRetain8(String retain8) {
        this.retain8 = retain8 == null ? null : retain8.trim();
    }

    public String getRetain9() {
        return retain9;
    }

    public void setRetain9(String retain9) {
        this.retain9 = retain9 == null ? null : retain9.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}