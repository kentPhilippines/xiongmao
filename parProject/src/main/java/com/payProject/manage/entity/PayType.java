package com.payProject.manage.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class PayType extends BaseEntity<PayType>{
	private static final long serialVersionUID = -1330942231132368782L;

	private String payTypeNo;

    private String payTypeName;


    public String getPayTypeNo() {
        return payTypeNo;
    }

    public void setPayTypeNo(String payTypeNo) {
        this.payTypeNo = payTypeNo == null ? null : payTypeNo.trim();
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}