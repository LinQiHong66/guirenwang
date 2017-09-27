package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by yc on 2016/12/19 0019.
 * 钱包地址
 */
public class WalletAddressEvent {

    /**id*/
    private long id;
    /**用户编号*/
    private int user_no;
    /**币种编号*/
    private int coin_no;
    /**身份证*/
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

    public WalletAddressEvent(long id, int user_no, int coin_no, String idtf, String address, Date date, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.coin_no = coin_no;
        this.idtf = idtf;
        this.address = address;
        this.date = date;
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
}
