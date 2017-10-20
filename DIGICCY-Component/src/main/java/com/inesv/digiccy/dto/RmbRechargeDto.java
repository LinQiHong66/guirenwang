package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class RmbRechargeDto {

    /**编号*/
    private long id;
    /**用户编号*/
    private int user_no;
    /**充值方式*/
    private int recharge_type;
    /**充值金额*/
    private BigDecimal recharge_price;
    /**订单号*/
    private String recharge_order;
    /**实际到账*/
    private BigDecimal actual_price;
    /**状态*/
    private int state;
    /**充值时间*/
    private Date date;
    /**用户编码*/
    private String userCode;
    /**真实姓名*/
    private String realName;
    /**备用字段1*/
    private String attr1;
    /**备用字段2*/
    private int attr2;
    /**备用字段1*/
    private String attr3;

    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getRecharge_type() {
		return recharge_type;
	}
	public void setRecharge_type(int recharge_type) {
		this.recharge_type = recharge_type;
	}
	public BigDecimal getRecharge_price() {
		return recharge_price;
	}
	public void setRecharge_price(BigDecimal recharge_price) {
		this.recharge_price = recharge_price;
	}
	public String getRecharge_order() {
		return recharge_order;
	}
	public void setRecharge_order(String recharge_order) {
		this.recharge_order = recharge_order;
	}
	public BigDecimal getActual_price() {
		return actual_price;
	}
	public void setActual_price(BigDecimal actual_price) {
		this.actual_price = actual_price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public int getAttr2() {
		return attr2;
	}
	public void setAttr2(int attr2) {
		this.attr2 = attr2;
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
		if(this.userCode==null) {
			this.userCode = "--";
		}
	}
	public String getUserCode() {
		return userCode;
	}
}
