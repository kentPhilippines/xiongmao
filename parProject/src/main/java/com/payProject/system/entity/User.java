package com.payProject.system.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * <p>用户数据表</p>
 * @author K
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
    private String userId;
    /**
	 * <p>用户昵称</p>
	 */
    private String userName;
    /**
	 * <p>登录密码</p>
	 * 不可为null
	 */
    private String userPassword;
    /**
	 * <p>加密盐</p>
	 * 
	 */
    private String userSalt;
    /**
	 * <p>用户邮箱</p>
	 */
    private String userMail;
    /**
	 * <p>用户手机</p>
	 */
    private String userPhone;
    /**
	 * <p>用户QQ</p>
	 */
    private String userQQ;
    /**
	 * <p>用户微信</p>
	 */
    private String userWechar;
    /**
   	 * <p>用户类型</p>
   	 * 目前该值未定义，需要一个项目全局变量来定义
   	 */
    private Integer userType;
    /**
	 * <p>用户地址</p>
	 */
    private String userAddress;
    /**
	 * <p>用户所在城市</p>
	 */
    private String userCity;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt == null ? null : userSalt.trim();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ == null ? null : userQQ.trim();
    }

    public String getUserWechar() {
        return userWechar;
    }

    public void setUserWechar(String userWechar) {
        this.userWechar = userWechar == null ? null : userWechar.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity == null ? null : userCity.trim();
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