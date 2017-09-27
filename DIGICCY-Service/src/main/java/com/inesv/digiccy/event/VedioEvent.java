package com.inesv.digiccy.event;

public class VedioEvent {
	private int id;
	private String name;
	private String url;
	private String info;
	private String operation;
	
	public VedioEvent(int id, String name, String url, String info, String operation){
		this.id = id;
		this.name = name;
		this.url = url;
		this.info = info;
		this.operation = operation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
