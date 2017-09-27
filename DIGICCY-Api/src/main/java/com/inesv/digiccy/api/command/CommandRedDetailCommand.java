package com.inesv.digiccy.api.command;

import java.util.Date;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class CommandRedDetailCommand {
	@TargetAggregateIdentifier
	private Long id;
    /** 用户ID */
    private Long user_id;
    /** 红包口令项目ID */
    private Long command_id;
    /** 红包口令随机数*/
    private String command_number;
    /** 奖品名称/货币类型 */
    private String command_name_price;
    /** 领取奖励状态*/
    private Integer state;
    
    private Date date;
    
    private String attr1;
    
    private String attr2;
	
    private String operation;

	public CommandRedDetailCommand(Long id, Long user_id, Long command_id,
			String command_number, String command_name_price, Integer state,
			Date date, String attr1, String attr2, String operation) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.command_id = command_id;
		this.command_number = command_number;
		this.command_name_price = command_name_price;
		this.state = state;
		this.date = date;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.operation = operation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCommand_id() {
		return command_id;
	}

	public void setCommand_id(Long command_id) {
		this.command_id = command_id;
	}

	public String getCommand_number() {
		return command_number;
	}

	public void setCommand_number(String command_number) {
		this.command_number = command_number;
	}

	public String getCommand_name_price() {
		return command_name_price;
	}

	public void setCommand_name_price(String command_name_price) {
		this.command_name_price = command_name_price;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	
	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
