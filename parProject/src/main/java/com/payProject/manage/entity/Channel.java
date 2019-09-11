package com.payProject.manage.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class Channel extends BaseEntity<Channel>{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2374889330911377466L;

	private String channelNo;

    private String channelAccount;

    private String channelName;

    private Integer channelStautus;
    private Integer channelType;
    


    public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getChannelAccount() {
        return channelAccount;
    }

    public void setChannelAccount(String channelAccount) {
        this.channelAccount = channelAccount == null ? null : channelAccount.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public Integer getChannelStautus() {
        return channelStautus;
    }

    public void setChannelStautus(Integer channelStautus) {
        this.channelStautus = channelStautus;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}