package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */

public class InesvBankInfo {
	/** ID */
	private long id;
	/** 用户编号 */
	private int user_no;
	/** 备注名称 */
	private String remark_name;
	/** 开户银行 */
	private String bank;
	/** 开户省份 */
	private String province;
	/** 开户城市 */
	private String city;
	/** 开户支行 */
	private String branch;
	/** 开户姓名 */
	private String name;
	/** 开户卡号 */
	private String bank_num;
	/** 添加时间 */
	private Date date;
	/** 操作类型 0：不存在 、 1：存在 */
	private int state;
	/** 备用字段 */
	private String atte1;
	private String atte2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getRemark_name() {
		return remark_name;
	}

	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBank_num() {
		return bank_num;
	}

	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAtte2() {
		return atte2;
	}

	public void setAtte2(String atte2) {
		this.atte2 = atte2;
	}

	public String getAtte1() {
		return atte1;
	}

	public void setAtte1(String atte1) {
		this.atte1 = atte1;
	}

	@Override
	public String toString() {
		return "InesvBankInfo{" + "id=" + id + ", user_no=" + user_no + ", remark_name='" + remark_name + '\''
				+ ", bank='" + bank + '\'' + ", province='" + province + '\'' + ", city='" + city + '\'' + ", branch='"
				+ branch + '\'' + ", name='" + name + '\'' + ", bank_num='" + bank_num + '\'' + ", date=" + date
				+ ", state=" + state + ", atte2='" + atte2 + '\'' + '}';
	}
}
