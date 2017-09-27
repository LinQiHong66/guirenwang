package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class RmbRechargeEvnet {

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
    /**操作类型*/
    private String operating;

    public RmbRechargeEvnet(long id, int user_no, int recharge_type, BigDecimal recharge_price, String recharge_order, BigDecimal actual_price, int state, Date date, String operating) {
        this.id = id;
        this.user_no = user_no;
        this.recharge_type = recharge_type;
        this.recharge_price = recharge_price;
        this.recharge_order = recharge_order;
        this.actual_price = actual_price;
        this.state = state;
        this.date = date;
        this.operating = operating;
    }

    public long getId() {
        return id;
    }

    public int getUser_no() {
        return user_no;
    }

    public int getRecharge_type() {
        return recharge_type;
    }

    public BigDecimal getRecharge_price() {
        return recharge_price;
    }

    public String getRecharge_order() {
        return recharge_order;
    }

    public BigDecimal getActual_price() {
        return actual_price;
    }

    public int getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public String getOperating() {
        return operating;
    }
}
