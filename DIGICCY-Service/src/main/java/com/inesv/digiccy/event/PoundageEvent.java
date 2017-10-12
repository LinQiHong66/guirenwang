package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yc on 2017/1/5 0005.
 */
public class PoundageEvent {
    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 用户账号 */
    private String user_name;
    /* 用户编号 */
    private String user_code;
    /* 操作类型 0:买，1:卖，2:充值，3:提现 4转账*/
    private Integer optype;
    /* 货币类型 */
    private Integer type;
    /* 手续费费用 */
    private BigDecimal money;
    /* 订单金额*/
    private BigDecimal sum_money;
    /* 日期 */
    private Date date;
    /*  备用字段1 */
    private String attr1;
    /*  备用字段2 */
    private String attr2;
    /**操作类型*/
    private String operation;
    
	public PoundageEvent(long id, Integer user_no, String user_name, String user_code, Integer optype, Integer type,
			BigDecimal money, BigDecimal sum_money, Date date, String operation) {
		this.id = id;
		this.user_no = user_no;
		this.user_name = user_name;
		this.user_code = user_code;
		this.optype = optype;
		this.type = type;
		this.money = money;
		this.sum_money = sum_money;
		this.date = date;
		this.operation = operation;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
