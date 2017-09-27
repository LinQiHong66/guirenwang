package com.inesv.digiccy.dto;

public class LinkDto {
	private int id;
	private String linkName;
	private String linkUrl;
	private String linkType;
	public int getId() {
		return id;
	}
	public String getLinkName() {
		return linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
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
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	  
}
