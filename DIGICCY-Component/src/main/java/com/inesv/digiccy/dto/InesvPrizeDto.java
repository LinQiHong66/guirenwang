package com.inesv.digiccy.dto;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 我的奖品
 */
public class InesvPrizeDto {

    /**编号*/
    private Integer id;
    /**用户编号*/
    private Integer user_no;
    /**下家用户名*/
    private Integer rec_user;
    /**奖励类型*/
    private Integer prize_type;
    /**奖励说明*/
    private String prize_explain;
    /**操作金额*/
    private BigDecimal opt_price;
    /**奖励金额*/
    private BigDecimal prize_price;
    /**状态*/
    private Integer state;
    /**时间*/
    private Date date;

    @Override
    public String toString() {
        return "InesvPrizeDto{" +
                "id=" + id +
                ", user_no=" + user_no +
                ", rec_user=" + rec_user +
                ", prize_type=" + prize_type +
                ", prize_explain='" + prize_explain + '\'' +
                ", opt_price=" + opt_price +
                ", prize_price=" + prize_price +
                ", state=" + state +
                ", date=" + date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPrize_type() {
        return prize_type;
    }

    public void setPrize_type(Integer prize_type) {
        this.prize_type = prize_type;
    }

    public String getPrize_explain() {
        return prize_explain;
    }

    public void setPrize_explain(String prize_explain) {
        this.prize_explain = prize_explain;
    }

    public BigDecimal getOpt_price() {
        return opt_price;
    }

    public void setOpt_price(BigDecimal opt_price) {
        this.opt_price = opt_price;
    }

    public BigDecimal getPrize_price() {
        return prize_price;
    }

    public void setPrize_price(BigDecimal prize_price) {
        this.prize_price = prize_price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
