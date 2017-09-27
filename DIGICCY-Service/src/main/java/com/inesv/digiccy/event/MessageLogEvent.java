package com.inesv.digiccy.event;

import java.util.Date;

public class MessageLogEvent {
	private long id;
	private String phone_number;
	private String receive_name;
	private String sms_content;
	private Date update_time;
	private long user_id;
	private String operation;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getReceive_name() {
		return receive_name;
	}

	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}

	public String getSms_content() {
		return sms_content;
	}

	public void setSms_content(String sms_content) {
		this.sms_content = sms_content;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public MessageLogEvent(long id, String phone_number, String receive_name, String sms_content, Date update_time,
			long user_id, String operation) {
		super();
		this.id = id;
		this.phone_number = phone_number;
		this.receive_name = receive_name;
		this.sms_content = sms_content;
		this.update_time = update_time;
		this.user_id = user_id;
		this.operation = operation;
	}

}
