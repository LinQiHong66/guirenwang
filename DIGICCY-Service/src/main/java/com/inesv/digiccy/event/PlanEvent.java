package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PlanEvent {
	private Long id;
	
	private Integer user_id;
	
	private Integer bill_id;
	
	private int plan_type;
	
	private BigDecimal top_money_start;
	
	private BigDecimal top_money_stop;
	
	private BigDecimal low_money_start;
	
	private BigDecimal low_money_stop;
	
	private int plan_status;
	
	private Timestamp plan_time;
	
	private Long remark;//对应交易记录主键
	
	private BigDecimal plan_money;
	
	private String operation;
	
	public PlanEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlanEvent(Long id, Integer user_id, Integer bill_id, int plan_type,
			BigDecimal top_money_start, BigDecimal top_money_stop,
			BigDecimal low_money_start, BigDecimal low_money_stop,
			int plan_status, Timestamp plan_time, Long remark,BigDecimal plan_money,
			String operation) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.bill_id = bill_id;
		this.plan_type = plan_type;
		this.top_money_start = top_money_start;
		this.top_money_stop = top_money_stop;
		this.low_money_start = low_money_start;
		this.low_money_stop = low_money_stop;
		this.plan_status = plan_status;
		this.plan_time = plan_time;
		this.remark = remark;
		this.plan_money=plan_money;
		this.operation = operation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getBill_id() {
		return bill_id;
	}

	public void setBill_id(Integer bill_id) {
		this.bill_id = bill_id;
	}

	public int getPlan_type() {
		return plan_type;
	}

	public void setPlan_type(int plan_type) {
		this.plan_type = plan_type;
	}

	public BigDecimal getTop_money_start() {
		return top_money_start;
	}

	public void setTop_money_start(BigDecimal top_money_start) {
		this.top_money_start = top_money_start;
	}

	public BigDecimal getTop_money_stop() {
		return top_money_stop;
	}

	public void setTop_money_stop(BigDecimal top_money_stop) {
		this.top_money_stop = top_money_stop;
	}

	public BigDecimal getLow_money_start() {
		return low_money_start;
	}

	public void setLow_money_start(BigDecimal low_money_start) {
		this.low_money_start = low_money_start;
	}

	public BigDecimal getLow_money_stop() {
		return low_money_stop;
	}

	public void setLow_money_stop(BigDecimal low_money_stop) {
		this.low_money_stop = low_money_stop;
	}

	public int getPlan_status() {
		return plan_status;
	}

	public void setPlan_status(int plan_status) {
		this.plan_status = plan_status;
	}

	public Timestamp getPlan_time() {
		return plan_time;
	}

	public void setPlan_time(Timestamp plan_time) {
		this.plan_time = plan_time;
	}

	public Long getRemark() {
		return remark;
	}

	public void setRemark(Long remark) {
		this.remark = remark;
	}

	public BigDecimal getPlan_money() {
		return plan_money;
	}

	public void setPlan_money(BigDecimal plan_money) {
		this.plan_money = plan_money;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
}
