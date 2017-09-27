package com.inesv.digiccy.event;

public class PaymentBankEvent {
	private int id;//id
	private String bankName;//银行名称
	private String bankCardId;//银行卡号
	private String bankUserName;//资金人
	private String remark;//备注
	private String operation;//命令
	
	public PaymentBankEvent(){
		
	}
	public PaymentBankEvent(int id, String bankName, String bankCardId, String bankUserName, String remark, String operation){
		this.id = id;
		this.bankName = bankName;
		this.bankCardId = bankCardId;
		this.bankUserName = bankUserName;
		this.remark = remark;
		this.operation = operation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
