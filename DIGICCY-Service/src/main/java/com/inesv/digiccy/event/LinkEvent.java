package com.inesv.digiccy.event;

public class LinkEvent {
	private int id;
	private String linkName;
	private String linkUrl;
	private String operation;
	private String linkType;
	public LinkEvent(){
		
	}
	public LinkEvent(int id, String linkName, String linkUrl,String linkType, String operation){
		this.id = id;
		this.linkName = linkName;
		this.linkUrl = linkUrl;
		this.operation = operation;
		this.linkType = linkType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOperation() {
		return operation;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
}
