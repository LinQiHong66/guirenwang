package com.inesv.digiccy.event;

import java.util.Date;

public class UserBasicInfoEvent {
	private int userNo;
	private String nationality;
	private String job;
	private String sex;
	private Date birthday;
	private String userName;
	private String opration;

	public UserBasicInfoEvent() {

	}

	public UserBasicInfoEvent(int userNo, String nationality, String job, String sex, Date birthday, String userName,
			String opration) {
		this.userNo = userNo;
		this.nationality = nationality;
		this.job = job;
		this.sex = sex;
		this.birthday = birthday;
		this.opration = opration;
		this.userName = userName;
	}

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

	public String getOpration() {
		return opration;
	}

	public void setOpration(String opration) {
		this.opration = opration;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
