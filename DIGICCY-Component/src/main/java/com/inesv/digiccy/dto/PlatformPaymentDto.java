package com.inesv.digiccy.dto;

import java.util.Date;

 
public class PlatformPaymentDto {

	private Integer id;
	private String bank_name;
	private String bank_account;
	private String bank_people;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getBank_people() {
		return bank_people;
	}
	public void setBank_people(String bank_people) {
		this.bank_people = bank_people;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

 
}
