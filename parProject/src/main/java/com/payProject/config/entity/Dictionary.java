package com.payProject.config.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

/**
 * <p>数据字典</p>
 * @author ADMIN
 *
 */
public class Dictionary extends BaseEntity<Dictionary>{
	private static final long serialVersionUID = 9138984842059684553L;
	private String dataKey;
    private String dataValue;
    private String dataType;
    private String dateSource;
    private String use;
    public String getDataKey() {
        return dataKey;
    }
    public void setDataKey(String dataKey) {
        this.dataKey = dataKey == null ? null : dataKey.trim();
    }
    public String getDataValue() {
        return dataValue;
    }
    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }
    public String getDateSource() {
        return dateSource;
    }
    public void setDateSource(String dateSource) {
        this.dateSource = dateSource == null ? null : dateSource.trim();
    }
    public String getUse() {
        return use;
    }
    public void setUse(String use) {
        this.use = use == null ? null : use.trim();
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}