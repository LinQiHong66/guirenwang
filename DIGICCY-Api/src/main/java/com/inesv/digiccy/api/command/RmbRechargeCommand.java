package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class RmbRechargeCommand {

    @TargetAggregateIdentifier
    /**编号*/
    private long id;
    /**用户编号*/
    private int user_no;
    /**充值方式  -0网银1支付宝2微信*/
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
    /**备用字段1*/
    private String attr1;
    /**备用字段2*/
    private int attr2;
    /**操作类型*/
    private String operating;

    public RmbRechargeCommand(long id, int user_no, int recharge_type, BigDecimal recharge_price, String recharge_order, BigDecimal actual_price, int state, Date date, String operating) {
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

    public RmbRechargeCommand(long id,String recharge_order,int state, String operating) {
        this.id = id;
        this.recharge_order = recharge_order;
        this.state = state;
        this.operating = operating;
    }

    public RmbRechargeCommand(long id, int user_no, BigDecimal actual_price, String operating) {
        this.id = id;
        this.user_no = user_no;
        this.actual_price = actual_price;
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

    public String getAttr1() {
        return attr1;
    }

    public int getAttr2() {
        return attr2;
    }

    public String getOperating() {
        return operating;
    }
}
