package com.inesv.digiccy.dto;

import java.util.Date;

public class MessageSetDto {
	private int id;
	private int limit_date;
	private int limit_number;
	private String limit_ip;
	private String limit_name;
	private Date update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLimit_date() {
		return limit_date;
	}

	public void setLimit_date(int limit_date) {
		this.limit_date = limit_date;
	}

	public int getLimit_number() {
		return limit_number;
	}

	public void setLimit_number(int limit_number) {
		this.limit_number = limit_number;
	}

	public String getLimit_ip() {
		return limit_ip;
	}

	public void setLimit_ip(String limit_ip) {
		this.limit_ip = limit_ip;
	}

	public String getLimit_name() {
		return limit_name;
	}

	public void setLimit_name(String limit_name) {
		this.limit_name = limit_name;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "MessageSetDto [id=" + id + ", limit_date=" + limit_date + ", limit_number=" + limit_number
				+ ", limit_ip=" + limit_ip + ", limit_name=" + limit_name + ", update_time=" + update_time + "]";
	}

}
