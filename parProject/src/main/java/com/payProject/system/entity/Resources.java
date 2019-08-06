package com.payProject.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class Resources extends BaseEntity<Resources>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6472156226186377649L;
	/**
   	 * <p>用资源id</p>
   	 * 不可重复，建议自动生成
   	 * <strong><u>唯一索引</u></strong>
   	 */
	private Integer resourcesId;
	 /**
		 * <p>资源昵称</p>
		 */
    private String resourcesName;
    /**
	 * <p>资源类型</p>
	 */
    private Integer resourcesType;
    /**
   	 * <p>资源类型</p>
   	 * <p>目前暂时未定义这个数据类型,可留下备用</p>
   	 */
    private String resourcesKey;
    /**
	 * <p>父级资源id</p>
	 */
    private Integer parentId;
    /**
	 * <p>资源路径</p>
	 * <p>该路径为可点击路径,一般一级菜单无路径,但是要求就是只要存在路径就可以点击有效</p>
	 */
    private String resourcesUrl;
    /**
	 * <p>资源描述</p>
	 * <p>这个是干什么用的</p>
	 */
    private String description;
    /**
   	 * <p>资源排名</p>
   	 * <p>目前这个字段可能在现有的逻辑上用不到，但是在业务拓展的过程钟可能会用到</p>
   	 */
    private Integer rank;
    /**
   	 * <p>资源等级</p>
   	 * <p>用于标识这个路径是1,2,3哪一级菜单</p>
   	 */
    private Integer level;
    /**
   	 * <p>备用字段</p>
   	 * <p>用于业务拓展</p>
   	 */
    private String retain1;
    /**
   	 * <p>备用字段</p>
   	 * <p>用于业务拓展</p>
   	 */
    private String retain2;
    /**
   	 * <p>备用字段</p>
   	 * <p>用于业务拓展</p>
   	 */
    private String retain3;
    /**
   	 * <p>备用字段</p>
   	 * <p>用于业务拓展</p>
   	 */
    private String retain4;
    /**
   	 * <p>备用字段</p>
   	 * <p>用于业务拓展</p>
   	 */
    private String retain5;
    /**
   	 * <p>备用字段</p>
   	 * <p>用于业务拓展</p>
   	 */
    private String retain6;

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


    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}