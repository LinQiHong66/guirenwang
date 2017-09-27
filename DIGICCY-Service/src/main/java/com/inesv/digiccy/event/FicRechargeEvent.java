package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class FicRechargeEvent {

    /**编号*/
    private Integer id;
    /**用户编号*/
    private int user_no;
    /**货币种类*/
    private int coin_no;
    /**充值地址*/
    private String address;
    /**实际充值*/
    private BigDecimal actual_price;
    /**赠送*/
    private BigDecimal give_price;
    /**总到账量*/
    private BigDecimal sum_price;
    /**状态*/
    private int state;
    /**日期*/
    private Date date;
    /**交易id*/
    private String tixid;
    /** 操作类型*/
    private String operation;

    public FicRechargeEvent(Integer id, int user_no, int coin_no, String address, BigDecimal actual_price, BigDecimal give_price, BigDecimal sum_price, int state, Date date, String tixid, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.coin_no = coin_no;
        this.address = address;
        this.actual_price = actual_price;
        this.give_price = give_price;
        this.sum_price = sum_price;
        this.state = state;
        this.date = date;
        this.tixid = tixid;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public int getUser_no() {
        return user_no;
    }

    public int getCoin_no() {
        return coin_no;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getActual_price() {
        return actual_price;
    }

    public BigDecimal getGive_price() {
        return give_price;
    }

    public BigDecimal getSum_price() {
        return sum_price;
    }

    public int getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public String getTixid() {
        return tixid;
    }

    public String getOperation() {
        return operation;
    }
}
