package com.inesv.digiccy.api.command;

public class NoticeTypeCommand {
	int id;
	String name;
	String opreation;
	public NoticeTypeCommand(int id, String name, String opreation){
		this.id = id;
		this.name = name;
		this.opreation = opreation;
	}
	public String getOpreation() {
		return opreation;
	}
	public void setOpreation(String opreation) {
		this.opreation = opreation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
