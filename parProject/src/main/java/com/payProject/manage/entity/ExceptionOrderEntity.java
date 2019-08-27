package com.payProject.manage.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.payProject.system.entity.base.BaseEntity;

/**
 * <p>异常订单登记表</p>
 * @author K
 */
public class ExceptionOrderEntity extends BaseEntity<ExceptionOrderEntity>{


    /**
	 * 
	 */
	private static final long serialVersionUID = -1895340678402174384L;

	/**
	 * <p>异常单号<p>
	 */
	private String orderExceptId;

	/**
	 * <p>关联订单单号<p>
	 */
    private String orderId;

    /**
     * <p>异常订单状态:1程序异常2人工异常<p>
     */
    private Integer exceptStatus;

    /**
     * <p>异常类型:1交易,2系统加款,3交易手续费,4系统扣款,5代付,6代付手续费<p>
     */
    private Integer exceptType;

    /**
     * <p>订单关联商户账号<p>
     */
    private String orderAccount;

    /**
     * <p>异常订单金额<p>
     */
    private String exceptOrderAmount;

    /**
     * <p>异常订单生成IP(源头ip)<p>
     */
    private String orderGenerationIp;

    /**
     * <p>操作人<p>
     */
    private String operation;
    /**
     * <p>异常说明</p>
     */
    private String explain;
    
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

	public String getOrderExceptId() {
        return orderExceptId;
    }

    public void setOrderExceptId(String orderExceptId) {
        this.orderExceptId = orderExceptId == null ? null : orderExceptId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getExceptStatus() {
        return exceptStatus;
    }

    public void setExceptStatus(Integer exceptStatus) {
        this.exceptStatus = exceptStatus;
    }

    public Integer getExceptType() {
        return exceptType;
    }

    public void setExceptType(Integer exceptType) {
        this.exceptType = exceptType;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount == null ? null : orderAccount.trim();
    }

    public String getExceptOrderAmount() {
        return exceptOrderAmount;
    }

    public void setExceptOrderAmount(String exceptOrderAmount) {
        this.exceptOrderAmount = exceptOrderAmount == null ? null : exceptOrderAmount.trim();
    }

    public String getOrderGenerationIp() {
        return orderGenerationIp;
    }

    public void setOrderGenerationIp(String orderGenerationIp) {
        this.orderGenerationIp = orderGenerationIp == null ? null : orderGenerationIp.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}