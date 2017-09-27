package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * 众筹项目Dto
 * Created by JimJim on 2017/06/05 0017.
 */
public class CommandRedDto {

	private Long id;
	/** 红包口令项目ID */
    private String command_no;
    /** 红包口令名称 */
    private String command_name;
    /** 红包口令奖品类型 */
    private Integer command_prize_type;
    /** 奖品名称/货币类型 */
    private String command_name_price;
    /** 红包口令随机数*/
    private String command_number;
    /** 红包口令项目简介 */
    private String command_remark;
    /** 红包口令状态*/
    private Integer state;
    
    private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCommand_no() {
		return command_no;
	}

	public void setCommand_no(String command_no) {
		this.command_no = command_no;
	}

	public String getCommand_name() {
		return command_name;
	}

	public void setCommand_name(String command_name) {
		this.command_name = command_name;
	}

	public Integer getCommand_prize_type() {
		return command_prize_type;
	}

	public void setCommand_prize_type(Integer command_prize_type) {
		this.command_prize_type = command_prize_type;
	}

	public String getCommand_name_price() {
		return command_name_price;
	}

	public void setCommand_name_price(String command_name_price) {
		this.command_name_price = command_name_price;
	}

	public String getCommand_number() {
		return command_number;
	}

	public void setCommand_number(String command_number) {
		this.command_number = command_number;
	}

	public String getCommand_remark() {
		return command_remark;
	}

	public void setCommand_remark(String command_remark) {
		this.command_remark = command_remark;
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


}
