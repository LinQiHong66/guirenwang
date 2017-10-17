package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PoundatgeDto {
	
	private Long id;
	
	private Integer user_no;
	
	private String user_name;
	
	private String user_code;
	
	private Integer optype;
	
	private Integer type;
	
	private BigDecimal money;
	
	private BigDecimal sum_money;
	
	private Date date;
	
	private String attr1;
	
	private String attr2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUser_no() {
		return user_no;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public Integer getOptype() {
		return optype;
	}

	public void setOptype(Integer optype) {
		this.optype = optype;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getSum_money() {
		return sum_money;
	}

	public void setSum_money(BigDecimal sum_money) {
		this.sum_money = sum_money;
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
	
}
