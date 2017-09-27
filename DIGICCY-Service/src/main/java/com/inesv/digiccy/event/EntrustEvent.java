package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.util.Date;

public class EntrustEvent {
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
    /** 操作类型*/
    private String operation;
    
    private Long attr1;
    
    
	public EntrustEvent() {
	}

	public EntrustEvent(Long id, Integer user_no, Integer entrust_coin,
			Integer entrust_type, BigDecimal entrust_price,
			BigDecimal entrust_num, BigDecimal deal_num, BigDecimal piundatge,
			Integer state, Date date, String operation,Long attr1) {
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
		this.operation = operation;
		this.attr1 = attr1;
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

	public String getOperation() {
		return operation;
	}

	public Long getAttr1() {
		return attr1;
	}

	public void setAttr1(Long attr1) {
		this.attr1 = attr1;
	}
    
	
}
