package com.inesv.digiccy.dto;
/**
 * 权限访问的参数
 * @author liukeling
 *
 */
public class ProwerParamDto {
	//id
	private int id;
	//参数说明
	private String paramInfo;
	//参数名
	private String paramName;
	//权限信息id
	private int powerId;
	//参数值
	private String paramValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParamInfo() {
		return paramInfo;
	}
	public void setParamInfo(String paramInfo) {
		this.paramInfo = paramInfo;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public int getPowerId() {
		return powerId;
	}
	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
}
