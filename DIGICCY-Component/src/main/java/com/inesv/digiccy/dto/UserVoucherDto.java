package com.inesv.digiccy.dto;

import java.util.Date;

public class UserVoucherDto {
	private int id;
	private String imgUrl1;
	private String imgUrl2;
	private String imgUrl3;
	private String cardId;
	private int cardType;
	private int userNo;
	private String trueName;
	private String myvoucherType;
	private Date startDate;
	private Date enDateDate;
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getMyvoucherType() {
		return myvoucherType;
	}

	public void setMyvoucherType(String myvoucherType) {
		this.myvoucherType = myvoucherType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEnDateDate() {
		return enDateDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEnDateDate(Date enDateDate) {
		this.enDateDate = enDateDate;
	}
}
