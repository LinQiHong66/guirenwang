package com.inesv.digiccy.event;

import java.util.ArrayList;
import java.util.Date;

public class PowerInfoEvent {
	// id
	private int id;
	// 权限地址
	private String url;
	// 权限说明
	private String info;
	// 权限访问时间
	private Date time;
	// 访问权限人
	private String userName;
	// 参数集合
	private ArrayList<ProwerParamEvent> params;
	private String opration;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<ProwerParamEvent> getParams() {
		return params;
	}

	public void setParams(ArrayList<ProwerParamEvent> params) {
		this.params = params;
	}

	public String getOpration() {
		return opration;
	}

	public void setOpration(String opration) {
		this.opration = opration;
	}
}
