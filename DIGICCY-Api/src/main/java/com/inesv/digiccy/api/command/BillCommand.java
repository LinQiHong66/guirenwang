package com.inesv.digiccy.api.command;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class BillCommand {

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 充值手机 */
    private String recharge_phone;
    /* 充值金额 */
    private BigDecimal recharge_price;
    /* 付款方式 */
    private Integer pay_type;
    /* 付款金额 */
    private BigDecimal pay_price;
    /* 处理时间 */
    private Date handle_date;
    /* 状态 */
    private Integer state;
    /* 时间 */
    private Date date;
    /* 备用字段 */
    private String attr1;
    /* 备用字段 */
    private String attr2;

    private String operation;

    public BillCommand(Integer user_no, String recharge_phone, BigDecimal recharge_price, Integer pay_type, BigDecimal pay_price, Date handle_date, Integer state, Date date,String operation) {
        this.user_no = user_no;
        this.recharge_phone = recharge_phone;
        this.recharge_price = recharge_price;
        this.pay_type = pay_type;
        this.pay_price = pay_price;
        this.handle_date = handle_date;
        this.state = state;
        this.date = date;
        this.operation=operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public String getRecharge_phone() {
        return recharge_phone;
    }

    public BigDecimal getRecharge_price() {
        return recharge_price;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public BigDecimal getPay_price() {
        return pay_price;
    }

    public Date getHandle_date() {
        return handle_date;
    }

    public Integer getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public String getAttr1() {
        return attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public void setRecharge_phone(String recharge_phone) {
        this.recharge_phone = recharge_phone;
    }

    public void setRecharge_price(BigDecimal recharge_price) {
        this.recharge_price = recharge_price;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public void setPay_price(BigDecimal pay_price) {
        this.pay_price = pay_price;
    }

    public void setHandle_date(Date handle_date) {
        this.handle_date = handle_date;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }


}
