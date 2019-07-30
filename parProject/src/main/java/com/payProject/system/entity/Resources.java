package com.payProject.system.entity;

import java.util.Date;

public class Resources {
    private Integer id;

    private Integer resourcesid;

    private String resourcesname;

    private Integer resourcestype;

    private String resourceskey;

    private String resourcesurl;

    private String description;

    private Date createtime;

    private Date submittime;

    private String submitsystem;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourcesid() {
        return resourcesid;
    }

    public void setResourcesid(Integer resourcesid) {
        this.resourcesid = resourcesid;
    }

    public String getResourcesname() {
        return resourcesname;
    }

    public void setResourcesname(String resourcesname) {
        this.resourcesname = resourcesname == null ? null : resourcesname.trim();
    }

    public Integer getResourcestype() {
        return resourcestype;
    }

    public void setResourcestype(Integer resourcestype) {
        this.resourcestype = resourcestype;
    }

    public String getResourceskey() {
        return resourceskey;
    }

    public void setResourceskey(String resourceskey) {
        this.resourceskey = resourceskey == null ? null : resourceskey.trim();
    }

    public String getResourcesurl() {
        return resourcesurl;
    }

    public void setResourcesurl(String resourcesurl) {
        this.resourcesurl = resourcesurl == null ? null : resourcesurl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
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