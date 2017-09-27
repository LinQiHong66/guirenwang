package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class WalletAddressCommand {

    @TargetAggregateIdentifier
    /**id*/
    private long id;
    /**用户编号*/
    private int user_no;
    /**币种编号*/
    private int coin_no;
    /**钱包标识*/
    private String idtf;
    /**地址*/
    private String address;
    /**日期*/
    private Date date;
    /**备用字段1*/
    private String atte1;
    /**备用字段2*/
    private String atte2;
    /**操作类型*/
    private String operation;


    public WalletAddressCommand(long id, int user_no, int coin_no, String idtf, String address, Date date,String operation) {
        this.id = id;
        this.user_no = user_no;
        this.coin_no = coin_no;
        this.idtf = idtf;
        this.address = address;
        this.date = date;
        this.operation = operation;
    }

    public WalletAddressCommand(long id, String address, String operation) {
        this.id = id;
        this.address = address;
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public int getUser_no() {
        return user_no;
    }

    public int getCoin_no() {
        return coin_no;
    }

    public String getIdtf() {
        return idtf;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public String getOperation() {
        return operation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public void setCoin_no(int coin_no) {
        this.coin_no = coin_no;
    }

    public void setIdtf(String idtf) {
        this.idtf = idtf;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAtte1() {
        return atte1;
    }

    public void setAtte1(String atte1) {
        this.atte1 = atte1;
    }

    public String getAtte2() {
        return atte2;
    }

    public void setAtte2(String atte2) {
        this.atte2 = atte2;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
