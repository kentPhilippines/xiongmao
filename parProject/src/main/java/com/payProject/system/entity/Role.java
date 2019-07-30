package com.payProject.system.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Role {
    private Integer id;

    private Integer roleid;

    private String rolename;

    private Date rolecreatetime;

    private Date rolesubmittime;

    private String submitsystem;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Date getRolecreatetime() {
        return rolecreatetime;
    }

    public void setRolecreatetime(Date rolecreatetime) {
        this.rolecreatetime = rolecreatetime;
    }

    public Date getRolesubmittime() {
        return rolesubmittime;
    }

    public void setRolesubmittime(Date rolesubmittime) {
        this.rolesubmittime = rolesubmittime;
    }

    public String getSubmitsystem() {
        return submitsystem;
    }

    public void setSubmitsystem(String submitsystem) {
        this.submitsystem = submitsystem == null ? null : submitsystem.trim();
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