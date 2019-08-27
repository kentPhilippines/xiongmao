package com.payProject.manage.entity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.payProject.system.entity.base.BaseEntity;
public class RunOrder extends BaseEntity<RunOrder>{
	private static final long serialVersionUID = 4424739100621947136L;
	/**
	 * <p>流水订单号</p>
	 */
    private String orderRunId;
    /**
	 * <p>关联订单号</p>
	 */
    private String orderId;
    /**
	 * <p>流水状态</p>
	 */
    private Integer runStatus;
    /**
	 * <p>流水类型:1交易,2系统加款,3交易手续费,4系统扣款,5代付,6代付手续费</p>
	 */
    private Integer runType;
    /**
	 * <p>流水关联商户号</p>
	 */
    private String orderAccount;
    /**
	 * <p>流水金额</p>
	 */
    private String runOrderAmount;
    /**
	 * <p>流水下游IP</p>
	 */
    private String orderGenerationIp;
    /**
	 * <p>流水关联出账 账户  (如果是银行卡  这里就是卡号，如果是上游这里就是上游商户号) </p>
	 */
    private String cardRunD;
    /**
	 * <p>流水关联出账 账户名  (如果是银行卡  这里就是卡号，如果是上游这里就是上游商户号) </p>
	 */
    private String cardNameRunD;
    /**
	 * <p>流水关联入账 账户  (如果是银行卡  这里就是卡号，如果是支付宝这里就是支付宝账户) </p>
	 */
    private String cardRunW;
    /**
	 * <p>流水关联入账 账户名  (如果是银行卡  这里就是卡号，如果是支付宝这里就是支付宝账户) </p>
	 */
    private String cardNameRunW;
    /**
	 * <p>流水描述</p>
	 */
    private String dealDescribe;
    /**
     * <p>时间范围</p>
     */
    private String Time;
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * <p>备用字段</p>
     */
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
	public String getOrderRunId() {
        return orderRunId;
    }
    public void setOrderRunId(String orderRunId) {
        this.orderRunId = orderRunId == null ? null : orderRunId.trim();
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }
    public Integer getRunStatus() {
        return runStatus;
    }
    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }
    public Integer getRunType() {
        return runType;
    }
    public void setRunType(Integer runType) {
        this.runType = runType;
    }
    public String getOrderAccount() {
        return orderAccount;
    }
    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount == null ? null : orderAccount.trim();
    }
    public String getRunOrderAmount() {
        return runOrderAmount;
    }
    public void setRunOrderAmount(String runOrderAmount) {
        this.runOrderAmount = runOrderAmount == null ? null : runOrderAmount.trim();
    }
    public String getOrderGenerationIp() {
        return orderGenerationIp;
    }
    public void setOrderGenerationIp(String orderGenerationIp) {
        this.orderGenerationIp = orderGenerationIp == null ? null : orderGenerationIp.trim();
    }
    public String getCardRunD() {
        return cardRunD;
    }
    public void setCardRunD(String cardRunD) {
        this.cardRunD = cardRunD == null ? null : cardRunD.trim();
    }
    public String getCardNameRunD() {
        return cardNameRunD;
    }
    public void setCardNameRunD(String cardNameRunD) {
        this.cardNameRunD = cardNameRunD == null ? null : cardNameRunD.trim();
    }
    public String getCardRunW() {
        return cardRunW;
    }
    public void setCardRunW(String cardRunW) {
        this.cardRunW = cardRunW == null ? null : cardRunW.trim();
    }
    public String getCardNameRunW() {
        return cardNameRunW;
    }
    public void setCardNameRunW(String cardNameRunW) {
        this.cardNameRunW = cardNameRunW == null ? null : cardNameRunW.trim();
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
    public String getDealDescribe() {
        return dealDescribe;
    }
    public void setDealDescribe(String dealDescribe) {
        this.dealDescribe = dealDescribe == null ? null : dealDescribe.trim();
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}