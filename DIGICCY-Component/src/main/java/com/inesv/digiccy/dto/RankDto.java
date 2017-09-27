package com.inesv.digiccy.dto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class RankDto {

    /* 排名 */
    private Integer rank;
    /* 用户编号 */
    private Integer user_no;
    /* 邀请人数 */
    private Integer people;
    /* 收益总金额 */
    private BigDecimal detail;

    public Integer getRank() {
        return rank;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public Integer getPeople() {
        return people;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public BigDecimal getDetail() {
        return detail;
    }

    public void setDetail(BigDecimal detail) {
        this.detail = detail;
    }
}
