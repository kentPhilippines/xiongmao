package com.payProject.manage.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class ChannelFee extends BaseEntity<ChannelFee>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2909189485618714032L;
	/**
	 * <p>渠道本地编号</p>
	 */
	private String channelNo;

	/**
	 * <p>上游实际代号</p>
	 */
    private String channelAccount;
    /**
	 * <p>渠道名</p>
	 */
    private String channelName;
    /**
	 * <p>支付方式(产品)</p>
	 */
    private String payType;
    /**
	 * <p>费率</p>
	 */
    private String fee;
    /**
	 * <p>日结百分比</p>
	 * <p>如果不进行设置得话该值默认为100%</p>
	 */
    private String settle;



    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getChannelAccount() {
        return channelAccount;
    }

    public void setChannelAccount(String channelAccount) {
        this.channelAccount = channelAccount == null ? null : channelAccount.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee == null ? null : fee.trim();
    }

    public String getSettle() {
        return settle;
    }

    public void setSettle(String settle) {
        this.settle = settle == null ? null : settle.trim();
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}