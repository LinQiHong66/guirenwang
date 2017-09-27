package com.inesv.digiccy.api.command;

public class LinkCommand {
	private int id; //id
	private String linkName; //链接名
	private String linkUrl;  //链接地址
	private String operation; //命令
	private String linkType; //链接类型  友情链接   关于我们   帮助中心   下载中心
	public int getId() {
		return id;
	}
	public String getLinkName() {
		return linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public String getOperation() {
		return operation;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}  
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
}
