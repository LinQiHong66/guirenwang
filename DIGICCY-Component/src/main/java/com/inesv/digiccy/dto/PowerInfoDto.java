package com.inesv.digiccy.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * 权限记录信息
 * 
 * @author liukeling
 *
 */
public class PowerInfoDto {
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
	private ArrayList<ProwerParamDto> params;

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

	public ArrayList<ProwerParamDto> getParams() {
		return params;
	}

	public void setParams(ArrayList<ProwerParamDto> params) {
		this.params = params;
	}
}
