package com.inesv.digiccy.event;

public class ContactEvent {
	private int id;//id
	private String email;//email
	private String weixin;//微信
	private String wxqrcord;//微信二维码
	private String qqqrcord;//qq二维码
	private String qq;//qq
	private String address;//地址
	private String remark;//详情
	private String operation;//命令 
	private String telphone;//电话
	
	public ContactEvent(){
		
	}
	
	
	public ContactEvent(int id, String email, String weixin,String wxqrcord, String qqqrcord, String qq, String address, String remark,
			String telphone, String operation) {
		super();
		this.id = id;
		this.email = email;
		this.weixin = weixin;
		this.qq = qq;
		this.wxqrcord = wxqrcord;
		this.qqqrcord = qqqrcord;
		this.address = address;
		this.remark = remark;
		this.operation = operation;
		this.telphone = telphone;
	}


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


	public String getTelphone() {
		return telphone;
	}


	public void setTelphone(String telphone) {
		this.telphone = telphone;
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
}
