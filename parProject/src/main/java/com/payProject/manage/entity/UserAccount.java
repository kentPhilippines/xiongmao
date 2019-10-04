package com.payProject.manage.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class UserAccount extends BaseEntity<RunOrder>{
    private String userId;
    private List userIdList;
    private String UserName;
    private String accountId;
    private String accountName;
    
    
    public List getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List userIdList) {
		this.userIdList = userIdList;
	}

	public String getUserId() {
        return userId;
    }

    public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}