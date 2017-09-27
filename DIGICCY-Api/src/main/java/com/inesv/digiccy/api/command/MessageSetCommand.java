package com.inesv.digiccy.api.command;

import java.util.Date;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class MessageSetCommand {
	@TargetAggregateIdentifier
	private int id;
	private int limit_date;
	private int limit_number;
	private String limit_ip;
	private String limit_name;
	private Date update_time;
	private String operation;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

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

}
