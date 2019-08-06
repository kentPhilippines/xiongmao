package com.payProject.config.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.pagehelper.Page;

public class PageResult<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Code ;
	private String mas;
	private String count;
	private Object data;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getMas() {
		return mas;
	}
	public void setMas(String mas) {
		this.mas = mas;
	}
	public String getCount() {
		return count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
