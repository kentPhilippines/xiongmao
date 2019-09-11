package com.payProject.manage.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.base.BaseEntity;

public class AccountFee extends BaseEntity<AccountFee>{
	private static final long serialVersionUID = 7445738879673870168L;
	/**	
	 * <p>账户编号</p>
	 */
	private String accountId;
	/**	
	 * <p>账户渠道</p>
	 */
    private String accountChannel;
    /**	
	 * <p>账户渠道产品类型</p>
	 */
    private String channelProduct;
    /**	
	 * <p>账户费率</p>
	 */
    private String accountFee;
    /**	
	 * <p>账户成本费率</p>
	 */
    private String accountCost;
    /**	
	 * <p>账户取款手续费</p>
	 */
    private String withdrawal;
    /**	
	 * <p>账户取款手续费成本</p>
	 */
    private String withdrawalCost;
    /**	
	 * <p>费率状态</p>
	 */
    private Integer feeStautus;
    /**	
	 * <p>冻结百分比</p>
	 */
    private String accountSette;
    /**	
	 * <p>冻结模式T1/D1</p>
	 */
    private String settlementType;
    
    public String getAccountSette() {
		return accountSette;
	}
	public void setAccountSette(String accountSette) {
		this.accountSette = accountSette;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
    public String getAccountChannel() {
        return accountChannel;
    }
    public void setAccountChannel(String accountChannel) {
        this.accountChannel = accountChannel == null ? null : accountChannel.trim();
    }
    public String getChannelProduct() {
        return channelProduct;
    }
    public void setChannelProduct(String channelProduct) {
        this.channelProduct = channelProduct == null ? null : channelProduct.trim();
    }
    public String getAccountFee() {
        return accountFee;
    }
    public void setAccountFee(String accountFee) {
        this.accountFee = accountFee == null ? null : accountFee.trim();
    }
    public String getAccountCost() {
        return accountCost;
    }
    public void setAccountCost(String accountCost) {
        this.accountCost = accountCost == null ? null : accountCost.trim();
    }
    public String getWithdrawal() {
        return withdrawal;
    }
    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal == null ? null : withdrawal.trim();
    }
    public String getWithdrawalCost() {
        return withdrawalCost;
    }
    public void setWithdrawalCost(String withdrawalCost) {
        this.withdrawalCost = withdrawalCost == null ? null : withdrawalCost.trim();
    }
    public Integer getFeeStautus() {
        return feeStautus;
    }
    public void setFeeStautus(Integer feeStautus) {
        this.feeStautus = feeStautus;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}