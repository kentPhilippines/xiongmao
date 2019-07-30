package com.payProject.system.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Role {
    private Integer id;

    private Integer roleId;

    private String roleName;

    private Date roleCreateTime;

    private Date roleSubmitTime;

    private String submitSystem;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(Date roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public Date getRoleSubmitTime() {
        return roleSubmitTime;
    }

    public void setRoleSubmitTime(Date roleSubmitTime) {
        this.roleSubmitTime = roleSubmitTime;
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