package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class RecProfitDto {

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 被推荐用户编号 */
    private Integer rec_user;
    /* 手机号 */
    private String phone;
    /* 注册日期 */
    private Date reg_date;
    /* 是否充值 */
    private Integer recharge;
    /* 是否交易 */
    private Integer deal;
    /* 是否实名 */
    private Integer real;
    /* 收益金额 */
    private BigDecimal profit_price;
    /* 日期 */
    private Date date;
    /* 备注 */
    private String remark;
    /*  备用字段2 */
    private String attr2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public Integer getRec_user() {
        return rec_user;
    }

    public void setRec_user(Integer rec_user) {
        this.rec_user = rec_user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Integer getRecharge() {
        return recharge;
    }

    public void setRecharge(Integer recharge) {
        this.recharge = recharge;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public Integer getReal() {
        return real;
    }

    public void setReal(Integer real) {
        this.real = real;
    }

    public BigDecimal getProfit_price() {
        return profit_price;
    }

    public void setProfit_price(BigDecimal profit_price) {
        this.profit_price = profit_price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }
}
