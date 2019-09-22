package com.payProject.config.common;

public class DataResult {
	private String payType;//产品
	private double payTypeNumber;//产品在成功订单中的分布
	private double payTypeNumberP;//产品在总订单中的分布
	private double number;//产品交易次数
	
	
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public double getPayTypeNumberP() {
		return payTypeNumberP;
	}
	public void setPayTypeNumberP(double payTypeNumberP) {
		this.payTypeNumberP = payTypeNumberP;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public double getPayTypeNumber() {
		return payTypeNumber;
	}
	public void setPayTypeNumber(double payTypeNumber) {
		this.payTypeNumber = payTypeNumber;
	}
}
