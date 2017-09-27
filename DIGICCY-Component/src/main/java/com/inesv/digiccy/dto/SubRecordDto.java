package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by huguokai on 2016/11/14 0014.
 * 认购中心实体类
 */
public class SubRecordDto {

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 货币编号 */
    private Integer coin_no;
    /* 备注名称 */
    private String sub_name;
    /* 价格 */
    private BigDecimal sub_price;
    /* 购买数量 */
    private BigDecimal sub_num;
    /* 总金额 */
    private BigDecimal sum_price;
    /* 解冻次数 */
    private Integer thaw_num;
    /* 解冻时间 */
    private Date thaw_time;
    /* 剩余冻结 */
    private BigDecimal sur_frozen;
    /* 状态 */
    private Integer state;
    /* 时间 */
    private Date date;
    /* 解冻数量 */
    private BigDecimal thaw_sum;
    /* 备用字段 */
    private String attr1;
    /* 备用字段 */
    private String attr2;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SubRecordDto(){}

    public long getId() {
        return id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public Integer getCoin_no() {
        return coin_no;
    }

    public String getSub_name() {
        return sub_name;
    }

    public BigDecimal getSub_price() {
        return sub_price;
    }

    public BigDecimal getSub_num() {
        return sub_num;
    }

    public BigDecimal getSum_price() {
        return sum_price;
    }

    public Integer getThaw_num() {
        return thaw_num;
    }

    public Date getThaw_time() {
        return thaw_time;
    }

    public BigDecimal getSur_frozen() {
        return sur_frozen;
    }

    public Integer getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getThaw_sum() {
        return thaw_sum;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public void setCoin_no(Integer coin_no) {
        this.coin_no = coin_no;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public void setSub_price(BigDecimal sub_price) {
        this.sub_price = sub_price;
    }

    public void setSub_num(BigDecimal sub_num) {
        this.sub_num = sub_num;
    }

    public void setSum_price(BigDecimal sum_price) {
        this.sum_price = sum_price;
    }

    public void setThaw_num(Integer thaw_num) {
        this.thaw_num = thaw_num;
    }

    public void setThaw_time(Date thaw_time) {
        this.thaw_time = thaw_time;
    }

    public void setSur_frozen(BigDecimal sur_frozen) {
        this.sur_frozen = sur_frozen;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setThaw_sum(BigDecimal thaw_sum) {
        this.thaw_sum = thaw_sum;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public SubRecordDto(Integer user_no, Integer coin_no, String sub_name, BigDecimal sub_price, BigDecimal sub_num, BigDecimal sum_price, Integer thaw_num, Date thaw_time, BigDecimal sur_frozen, Integer state, Date date, BigDecimal thaw_sum) {
        this.user_no = user_no;
        this.coin_no = coin_no;
        this.sub_name = sub_name;
        this.sub_price = sub_price;
        this.sub_num = sub_num;
        this.sum_price = sum_price;
        this.thaw_num = thaw_num;
        this.thaw_time = thaw_time;
        this.sur_frozen = sur_frozen;
        this.state = state;
        this.date = date;
        this.thaw_sum = thaw_sum;
    }
}
