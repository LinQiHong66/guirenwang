package com.inesv.digiccy.dto;

/**
 * 
 * @author Liukeling
 *
 */
public class ContactDto {
	private int id;
	private String email;
	private String weixin;
	private String wxqrcord;//微信二维码
	private String qqqrcord;//qq二维码
	private String telphone;
	private String qq;
	private String address;
	private String authority_account;//官方账号
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	
	public String getWxqrcord() {
		return wxqrcord;
	}
	public void setWxqrcord(String wxqrcord) {
		this.wxqrcord = wxqrcord;
	}
	public String getQqqrcord() {
		return qqqrcord;
	}
	public void setQqqrcord(String qqqrcord) {
		this.qqqrcord = qqqrcord;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getAuthority_account() {
		return authority_account;
	}
	public void setAuthority_account(String authority_account) {
		this.authority_account = authority_account;
	}
	
}
