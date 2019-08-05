package com.payProject.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class Resources extends BaseEntity<Resources>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer resourcesId;

    private String resourcesName;

    private Integer resourcesType;

    private String resourcesKey;

    private Integer parentId;

    private String resourcesUrl;

    private String description;


    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName == null ? null : resourcesName.trim();
    }

    public Integer getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(Integer resourcesType) {
        this.resourcesType = resourcesType;
    }

    public String getResourcesKey() {
        return resourcesKey;
    }

    public void setResourcesKey(String resourcesKey) {
        this.resourcesKey = resourcesKey == null ? null : resourcesKey.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        this.resourcesUrl = resourcesUrl == null ? null : resourcesUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}