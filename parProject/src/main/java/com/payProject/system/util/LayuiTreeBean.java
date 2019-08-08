package com.payProject.system.util;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>关于前端layui使用tree的实体数据类</p>
 * @author K
 * @data 2019-08-07
 */
public class LayuiTreeBean {
	/**
	 * <p>显示的值 （必填）</p>
	 */
	private String title;
	/**
	 * <p>隐藏的值 （必填）</p>
	 */
	private String value;
	/**
	 * <p>默认是否选中，true为选中，false与不填都为不选中 （选填）</p>
	 */
	private Boolean  checked;
	/**
	 * <p>是否可用，true为不可用，false与不填都为可用 （选填）</p>
	 */
	private Boolean disabled;
	/**
	 * <p>json数组 节点的子节点数组，结构与此节点一致，（必填）如果无子节点则必须为 data:[]</p>
	 */
	private List<LayuiTreeBean> data;
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
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	
	 public List<LayuiTreeBean> getData() {
		return data;
	}
	public void setData(List<LayuiTreeBean> data) {
		this.data = data;
	}
	@Override
	    public String toString() {
	        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	    }
}
