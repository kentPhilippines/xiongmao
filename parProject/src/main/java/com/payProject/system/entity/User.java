package com.payProject.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;
/**
 * <p>用户数据表</p>
 * @author K
 *
 */
public class User extends BaseEntity<User>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4415258459504769269L;
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
	 * <p>支付密码</p>
	 */
    private String payPassword;
    /**
	 * <p>用户状态</p>
	 * <strong><u>状态:1可使用；0不可使用</u></strong>		
	 * <div>该字段需要建立全局常量</div>		
	 */
    private Integer status;
	/**
	 * <p>码商利率(该字段只有账户是卡商或者码商才有数据)</p>
	 */
    private String retain1;
    /**
	 * <p>账号类型:1码商2商户3运营4财务5客服</p>
	 */
    private String retain2;
    /**
	 * 保留字段目前没有业务拓展的需求用不到
	 */
    private String retain3;
    /**
	 * 保留字段目前没有业务拓展的需求用不到
	 */
    private String retain4;
    /**
	 * 保留字段目前没有业务拓展的需求用不到
	 */
    private String retain5;
    /**
	 * 保留字段目前没有业务拓展的需求用不到
	 */
    private String retain6;
    /**
	 * 保留字段目前没有业务拓展的需求用不到
	 */
    private String retain7;
    /**
	 * 保留字段目前没有业务拓展的需求用不到
	 */
    private String retain8;
    /**
	 * 保留字段目前没有业务拓展的需求用不到
	 */
    private String retain9;
    /**
	 * 保留字段目前没有业务拓展的需求用不到
	 */
    private String retain10;

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


    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getRetain7() {
        return retain7;
    }

    public void setRetain7(String retain7) {
        this.retain7 = retain7 == null ? null : retain7.trim();
    }

    public String getRetain8() {
        return retain8;
    }

    public void setRetain8(String retain8) {
        this.retain8 = retain8 == null ? null : retain8.trim();
    }

    public String getRetain9() {
        return retain9;
    }

    public void setRetain9(String retain9) {
        this.retain9 = retain9 == null ? null : retain9.trim();
    }

    public String getRetain10() {
        return retain10;
    }

    public void setRetain10(String retain10) {
        this.retain10 = retain10 == null ? null : retain10.trim();
    }
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}