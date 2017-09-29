package com.inesv.digiccy.api.command;

import java.util.Date;

public class UserVoucherCommand {
	// id
	private int id;
	// 证件号
	private String cardId;
	// 证件类型
	private int cardType;
	// 相关图片
	private String imgUrl1;
	private String imgUrl2;
	private String imgUrl3;
	// 审核状态
	private int state;
	// 用户编号
	private int userNo;
	// 真实姓名
	private String realName;

	// 自定义证件类型
	private String myvoucherType;
	// 有效时间段
	private Date startDate;
	private Date endDate;

	private String operating;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getImgUrl1() {
		return imgUrl1;
	}

	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}

	public String getImgUrl2() {
		return imgUrl2;
	}

	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}

	public String getImgUrl3() {
		return imgUrl3;
	}

	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getOperating() {
		return operating;
	}

	public void setOperating(String operating) {
		this.operating = operating;
	}

	public String getMyvoucherType() {
		return myvoucherType;
	}

	public void setMyvoucherType(String myvoucherType) {
		this.myvoucherType = myvoucherType;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
