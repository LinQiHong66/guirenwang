package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class CommandRedEvent {
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
    
    private String operation;

	public CommandRedEvent(Long id, String command_no, String command_name,
			Integer command_prize_type, String command_name_price,
			String command_number, String command_remark, Integer state,
			Date date, String operation) {
		super();
		this.id = id;
		this.command_no = command_no;
		this.command_name = command_name;
		this.command_prize_type = command_prize_type;
		this.command_name_price = command_name_price;
		this.command_number = command_number;
		this.command_remark = command_remark;
		this.state = state;
		this.date = date;
		this.operation = operation;
	}

	public Long getId() {
		return id;
	}

	public String getCommand_no() {
		return command_no;
	}

	public String getCommand_name() {
		return command_name;
	}

	public Integer getCommand_prize_type() {
		return command_prize_type;
	}

	public String getCommand_name_price() {
		return command_name_price;
	}

	public String getCommand_number() {
		return command_number;
	}

	public String getCommand_remark() {
		return command_remark;
	}

	public Integer getState() {
		return state;
	}

	public Date getDate() {
		return date;
	}

	public String getOperation() {
		return operation;
	}
}
