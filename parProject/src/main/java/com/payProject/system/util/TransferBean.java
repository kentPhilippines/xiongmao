package com.payProject.system.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>关于前端layui使用Transfer的实体数据类</p>
 * @author K
 * @data 2019-08-07
 */
public class TransferBean {
	/**
	 * <p>显示的值 （必填）</p>
	 */
	private String title;
	/**
	 * <p>隐藏的值 （必填）</p>
	 * <p>实际数据库的数据</p>
	 */
	private String value;
	/**
	 * <p>是否可用，true为不可用，false与不填都为可用 （选填）</p>
	 */
	private Boolean disabled;
	/**
	 * <p>是否选择，true为选择，false与不填都为不选择 （选填）</p>
	 */
	private Boolean checked = false;
	
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	 
	 @Override 
	 public String toString() { 
		 return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
		 }
	 
}
