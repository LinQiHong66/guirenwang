package com.inesv.digiccy.dto;

import java.util.Date;

public class UserBasicInfoDto {
	private int userNo;
	private String nationality;
	private String job;
	private String sex;
	private Date birthday;
	private String userName;
	private String province;
	private String districts;
	private String cities;
	private String addressInfo;

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public String getCities() {
		return cities;
	}

	public String getDistricts() {
		return districts;
	}

	public String getProvince() {
		return province;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	public void setDistricts(String districts) {
		this.districts = districts;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}
