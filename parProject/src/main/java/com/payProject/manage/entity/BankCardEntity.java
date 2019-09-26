package com.payProject.manage.entity;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.config.exception.ParamException;
import com.payProject.system.entity.base.BaseEntity;
/**
 * <p>银行卡详细登记表</p>
 * @author K
 */
public class  BankCardEntity extends BaseEntity<BankCardEntity>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6213878085272841440L;
	/**
	 * <p>银行编号(本地编号)</p>
	 */
    private Integer bankId;
    /**
     * <p>银行类别</p>
     */
    private Integer bankType;
    /**
     * <p>银行卡号</p>
     */
    private String bankCard;
    /**
     * <p>银行名称</p>
     */
    private String bankName;
    /**
     * <p>持卡人</p>
     */
    private String cardholder;
    /**
     * <p>财务主管</p>
     */
    private String treasurer;
    /**
     * <p>银行卡负责人</p>
     */
    private String liabilities;
    /**
     * <p>卡上余额</p>
     */
    private BigDecimal bankAmount;
    /**
     * <p>绑定手机</p>
     */
    private String bankPhone;
    /**
     * </>持卡人身份证</p>
     */
    private String cardholderId;
    /**
     * <p>备注</p>
     */
    private String bankNote;
   
    private String retain1;//银行卡允许交易额度
    private String retain2;//是否逻辑删除：1删除2可用
    private String retain3;//银行卡简写
    private String retain4;
    private String retain5;
    private String retain6;
    private String retain7;
   
    public String getCardholderId() {
		return cardholderId;
	}

	public void setCardholderId(String cardholderId) {
		this.cardholderId = cardholderId;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getBankType() {
        return bankType;
    }

    public void setBankType(Integer bankType) {
        this.bankType = bankType;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder == null ? null : cardholder.trim();
    }

    public String getTreasurer() {
        return treasurer;
    }

    public void setTreasurer(String treasurer) {
        this.treasurer = treasurer == null ? null : treasurer.trim();
    }

    public String getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(String liabilities) {
        this.liabilities = liabilities == null ? null : liabilities.trim();
    }

    public BigDecimal getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(BigDecimal bankAmount) {
        this.bankAmount = bankAmount;
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

    public String getBankNote() {
        return bankNote;
    }

    public void setBankNote(String bankNote) {
        this.bankNote = bankNote == null ? null : bankNote.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}