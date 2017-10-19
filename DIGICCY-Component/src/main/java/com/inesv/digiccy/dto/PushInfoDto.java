package com.inesv.digiccy.dto;
/**
 * 推送消息信息dto
 * @author Liukeling
 *
 */
public class PushInfoDto {
	//主键
	private int id;
	//用户编号
	private int userNo;
	//区间最大值
	private float maxPrice;
	//区间最小值
	private float minPrice;
	//是否开启推送
	private boolean isPush;
	//设备token
	private String driverToken;
	//用户账号
	private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}
	public float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}
	public boolean isPush() {
		return isPush;
	}
	public void setPush(boolean isPush) {
		this.isPush = isPush;
	}
	public String getDriverToken() {
		return driverToken;
	}
	public void setDriverToken(String driverToken) {
		this.driverToken = driverToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
