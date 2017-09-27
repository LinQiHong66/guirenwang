package com.inesv.digiccy.api.command;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class FicWithdrawCommand {

    /**编号*/
    private Integer id;
    /**用户编号*/
    private int user_no;
    /**货币种类*/
    private int coin_no;
    /**转出数量*/
    private BigDecimal coin_sum;
    /**提现地址*/
    private String address;
    /**手续费*/
    private BigDecimal poundage;
    /**实际到账*/
    private BigDecimal actual_price;
    /**状态*/
    private int sate;
    /**提现时间*/
    private Date date;
    /**备用字段1*/
    private String attr1;
    /**备用字段2*/
    private String attr2;
    /**操作类型*/
    private String operation;

    public FicWithdrawCommand(Integer id, int user_no, int coin_no, BigDecimal coin_sum, String address, BigDecimal poundage, BigDecimal actual_price, int sate, Date date, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.coin_no = coin_no;
        this.coin_sum = coin_sum;
        this.address = address;
        this.poundage = poundage;
        this.actual_price = actual_price;
        this.sate = sate;
        this.date = date;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getCoin_no() {
        return coin_no;
    }

    public void setCoin_no(int coin_no) {
        this.coin_no = coin_no;
    }

    public BigDecimal getCoin_sum() {
        return coin_sum;
    }

    public void setCoin_sum(BigDecimal coin_sum) {
        this.coin_sum = coin_sum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public BigDecimal getActual_price() {
        return actual_price;
    }

    public void setActual_price(BigDecimal actual_price) {
        this.actual_price = actual_price;
    }

    public int getSate() {
        return sate;
    }

    public void setSate(int sate) {
        this.sate = sate;
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
