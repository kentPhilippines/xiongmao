package com.payProject.system.entity;

import java.util.Date;

/**
 * 用户数据表
 * @author ADMIN
 *
 */
public class User {
	/**
	 * <p>数据id</p>
	 * 数据库自动生成
	 * <strong><u>主键,唯一索引</u></strong>
	 */
    private Integer id;
    /**
	 * <p>用户id</p>
	 * 不可重复，建议自动生成
	 * <strong><u>唯一索引</u></strong>
	 */
    private String userid;
    /**
	 * <p>用户昵称</p>
	 */
    private String username;
    /**
	 * <p>登录密码</p>
	 * 不可为null
	 */
    private String userpassword;
    /**
	 * <p>用户邮箱</p>
	 */
    private String usermail;
    /**
	 * <p>用户手机</p>
	 */
    private String userphone;
    /**
	 * <p>用户QQ</p>
	 */
    private String userqq;
    /**
	 * <p>用户微信</p>
	 */
    private String userwechar;
    /**
	 * <p>用户类型</p>
	 * 目前该值未定义，需要一个项目全局变量来定义
	 */
    private Integer usertype;
    /**
	 * <p>用户地址</p>
	 */
    private String useraddress;
    /**
	 * <p>用户所在城市</p>
	 */
    private String usercity;
    /**
	 * <p>用户数据创建时间</p>
	 */
    private Date createtime;
    /**
	 * <p>用户提交时间</p>
	 */
    private Date submittime;
    /**
	 * <p>提交系统</p>
	 * <strong><u>全局索引</u></strong>
	 * <div>该字段用户区分系统数据</div>
	 */
    private String submitsystem;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail == null ? null : usermail.trim();
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getUserqq() {
        return userqq;
    }

    public void setUserqq(String userqq) {
        this.userqq = userqq == null ? null : userqq.trim();
    }

    public String getUserwechar() {
        return userwechar;
    }

    public void setUserwechar(String userwechar) {
        this.userwechar = userwechar == null ? null : userwechar.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }

    public String getUsercity() {
        return usercity;
    }

    public void setUsercity(String usercity) {
        this.usercity = usercity == null ? null : usercity.trim();
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
		return "User [id=" + id + ", userid=" + userid + ", username=" + username + ", userpassword=" + userpassword
				+ ", usermail=" + usermail + ", userphone=" + userphone + ", userqq=" + userqq + ", userwechar="
				+ userwechar + ", usertype=" + usertype + ", useraddress=" + useraddress + ", usercity=" + usercity
				+ ", createtime=" + createtime + ", submittime=" + submittime + ", submitsystem=" + submitsystem
				+ ", status=" + status + "]";
	}
    
    
}