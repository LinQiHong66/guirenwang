package com.inesv.digiccy.event;

public class HomeImgEvent {
	private int id;
	private String imgInfo;
	private String imgUrl;
	private String operation;
	public HomeImgEvent(int id,String imgInfo,String imgUrl,String operation){
		this.id = id;
		this.imgInfo = imgInfo;
		this.imgUrl = imgUrl;
		this.operation = operation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImgInfo() {
		return imgInfo;
	}
	public void setImgInfo(String imgInfo) {
		this.imgInfo = imgInfo;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
