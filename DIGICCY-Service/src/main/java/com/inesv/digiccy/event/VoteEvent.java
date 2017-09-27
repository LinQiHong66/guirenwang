package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class VoteEvent {

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 投票类型 */
    private Integer vote_type;
    /* 货币种类 */
    private Integer coin_type;
    /* 时间 */
    private Date date;

    public VoteEvent(Integer user_no, Integer vote_type, Integer coin_type, Date date) {
        this.user_no = user_no;
        this.vote_type = vote_type;
        this.coin_type = coin_type;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public Integer getVote_type() {
        return vote_type;
    }

    public Integer getCoin_type() {
        return coin_type;
    }

    public Date getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public void setVote_type(Integer vote_type) {
        this.vote_type = vote_type;
    }

    public void setCoin_type(Integer coin_type) {
        this.coin_type = coin_type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
