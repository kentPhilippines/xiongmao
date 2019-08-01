package com.payProject.system.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>用户角色对应关系表</p>
 * @author ADMIN
 *
 */
public class UserRole {
	/**
	 * <p>数据id</p>
	 * 数据库自动生成
	 * <strong><u>主键,唯一索引</u></strong>
	 */
    private Integer id;
    /**
   	 * <p>用户id</p>
   	 */
    private String userId;
    /**
   	 * <p>角色id</p>
   	 */
    private Integer role;
    /**
  	 * <p>用户数据创建时间</p>
  	 */
    private Date createTime;
    /**
	 * <p>用户提交时间</p>
	 */
    private Date submitTime;
    /**
   	 * <p>提交系统</p>
   	 * <strong><u>全局索引</u></strong>
   	 * <div>该字段用户区分系统数据</div>
   	 */
    private String submitSystem;
    /**
	 * <p>用户状态</p>
	 * <strong><u>状态:1可使用；0不可使用</u></strong>		
	 * <div>该字段需要建立全局常量</div>		
	 */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitSystem() {
        return submitSystem;
    }

    public void setSubmitSystem(String submitSystem) {
        this.submitSystem = submitSystem == null ? null : submitSystem.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}