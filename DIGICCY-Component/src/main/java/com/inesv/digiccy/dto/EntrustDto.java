package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EntrustDto {
	private Long id;
	/** 用户编号 */
	private Integer user_no;
	/** 委托币种 */
	private Integer entrust_coin;
	/** 委托类型 0：买 1：卖 */
	private Integer entrust_type;
	/** 委托价格 */
	private BigDecimal entrust_price;
	/** 委托数量 */
	private BigDecimal entrust_num;
	/** 成交数量 */
	private BigDecimal deal_num;
	/** 手续费 */
	private BigDecimal piundatge;
	/** 状态 0:委托中 1：已完成 2：撤销 */
	private Integer state;
	/** 时间 */
	private Date date;


	private Long attr1;
	private String attr2;
	private String attr3;
	public EntrustDto(Long id, Integer user_no, Integer entrust_coin,
			Integer entrust_type, BigDecimal entrust_price,
			BigDecimal entrust_num, BigDecimal deal_num, BigDecimal piundatge,
			Integer state, Date date,Long attr1) {
		this.id = id;
		this.user_no = user_no;
		this.entrust_coin = entrust_coin;
		this.entrust_type = entrust_type;
		this.entrust_price = entrust_price;
		this.entrust_num = entrust_num;
		this.deal_num = deal_num;
		this.piundatge = piundatge;
		this.state = state;
		this.date = date;
		this.attr1 = attr1;
	}

	public EntrustDto() {
	}

	public Long getId() {
		return id;
	}

	public Integer getUser_no() {
		return user_no;
	}

	public Integer getEntrust_coin() {
		return entrust_coin;
	}

	public Integer getEntrust_type() {
		return entrust_type;
	}

	public BigDecimal getEntrust_price() {
		return entrust_price;
	}

	public BigDecimal getEntrust_num() {
		return entrust_num;
	}

	public BigDecimal getDeal_num() {
		return deal_num;
	}

	public BigDecimal getPiundatge() {
		return piundatge;
	}

	public Integer getState() {
		return state;
	}

	public Date getDate() {
		return date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}

	public void setEntrust_coin(Integer entrust_coin) {
		this.entrust_coin = entrust_coin;
	}

	public void setEntrust_type(Integer entrust_type) {
		this.entrust_type = entrust_type;
	}

	public void setEntrust_price(BigDecimal entrust_price) {
		this.entrust_price = entrust_price;
	}

	public void setEntrust_num(BigDecimal entrust_num) {
		this.entrust_num = entrust_num;
	}

	public void setDeal_num(BigDecimal deal_num) {
		this.deal_num = deal_num;
	}

	public void setPiundatge(BigDecimal piundatge) {
		this.piundatge = piundatge;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAttr1() {
		return attr1;
	}

	public void setAttr1(Long attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
}
