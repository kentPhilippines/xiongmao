package com.payProject.system.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

/**
 * <p>用户角色对应关系表</p>
 * @author K
 */
public class UserRole extends BaseEntity<UserRole>{
	private static final long serialVersionUID = -8389743172503968912L;
	/**
   	 * <p>用户id</p>
   	 */
    private String userId;
    /**
   	 * <p>角色id</p>
   	 */
    private Integer role;
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
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}