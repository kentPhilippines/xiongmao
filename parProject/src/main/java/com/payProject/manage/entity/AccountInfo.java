package com.payProject.manage.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class AccountInfo extends BaseEntity<AccountInfo>{
	private static final long serialVersionUID = -2671314851580183408L;

	private String accountId;

    private String agentAccount;

    private Integer isAgant;

    private String password;

    private String appKey;

    private String appDesKey;

    private String accountIp;

    private String havaInterface;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getAgentAccount() {
        return agentAccount;
    }

    public void setAgentAccount(String agentAccount) {
        this.agentAccount = agentAccount == null ? null : agentAccount.trim();
    }

    public Integer getIsAgant() {
        return isAgant;
    }

    public void setIsAgant(Integer isAgant) {
        this.isAgant = isAgant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getAppDesKey() {
        return appDesKey;
    }

    public void setAppDesKey(String appDesKey) {
        this.appDesKey = appDesKey == null ? null : appDesKey.trim();
    }

    public String getAccountIp() {
        return accountIp;
    }

    public void setAccountIp(String accountIp) {
        this.accountIp = accountIp == null ? null : accountIp.trim();
    }

    public String getHavaInterface() {
        return havaInterface;
    }

    public void setHavaInterface(String havaInterface) {
        this.havaInterface = havaInterface == null ? null : havaInterface.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}