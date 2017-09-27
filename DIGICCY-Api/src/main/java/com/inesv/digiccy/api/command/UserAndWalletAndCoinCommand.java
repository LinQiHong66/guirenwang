package com.inesv.digiccy.api.command;

import java.util.Date;

/**
 * Created by JimJim on 2017/1/4 0004.
 */
public class UserAndWalletAndCoinCommand {
    /**用户名*/
    private String username;
    /**用户编号*/
    private int user_no;
    /** 货币编号 */
    private Integer coin_no;
    /** 货币名称 */
    private String coin_name;
    /**钱包标签*/
    private String idtf;
    /**钱包地址*/
    private String address;
    /**钱包创建时间*/
    private Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public Integer getCoin_no() {
        return coin_no;
    }

    public void setCoin_no(Integer coin_no) {
        this.coin_no = coin_no;
    }

    public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getIdtf() {
        return idtf;
    }

    public void setIdtf(String idtf) {
        this.idtf = idtf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
