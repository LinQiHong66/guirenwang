package com.inesv.digiccy.event;

import java.util.Date;

public class UserVoucherEvent {
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

	private String myvoucherType;

	private Date startDate;
	private Date enDateDate;
	private String operating;

	public UserVoucherEvent() {
		// TODO Auto-generated constructor stub
	}

	public UserVoucherEvent(int id, String cardId, int cardType, String imgUrl1, String imgUrl2, String imgUrl3,
			int state, int userNo, String realName, String operating, String myvoucherType, Date startDate, Date endDate) {
		this.id = id;
		this.cardId = cardId;
		this.cardType = cardType;
		this.imgUrl1 = imgUrl1;
		this.imgUrl2 = imgUrl2;
		this.imgUrl3 = imgUrl3;
		this.state = state;
		this.startDate = startDate;
		this.enDateDate = endDate;
		this.userNo = userNo;
		this.realName = realName;
		this.operating = operating;
		this.myvoucherType = myvoucherType;
	}

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

	public Date getEnDateDate() {
		return enDateDate;
	}

	public void setEnDateDate(Date enDateDate) {
		this.enDateDate = enDateDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
