package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class TranDto {

    /**id*/
    private Long id;
    /**用户编号*/
    private int user_no;
    /**转入用户*/
    private int tran_user;
    /**货币类型*/
    private int coin_type;
    /**转入数量*/
    private BigDecimal tran_num;
    /**手续费*/
    private BigDecimal poundage;
    /**状态*/
    private int state;
    /**日期*/
    private Date date;
    /** 备用字段1 */
    private String attr1;
    /** 备用字段2 */
    private String attr2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getTran_user() {
        return tran_user;
    }

    public void setTran_user(int tran_user) {
        this.tran_user = tran_user;
    }

    public int getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(int coin_type) {
        this.coin_type = coin_type;
    }

    public BigDecimal getTran_num() {
        return tran_num;
    }

    public void setTran_num(BigDecimal tran_num) {
        this.tran_num = tran_num;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
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
}
