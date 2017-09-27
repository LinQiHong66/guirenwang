package com.inesv.digiccy.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class BonusRecordDto implements Serializable{

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 分红币种 */
    private Integer bonus_type;
    /* 分红名称 */
    private String bonus_name;
    /* 奖励币种 */
    private Integer reward_type;
    /* 分红总额 */
    private BigDecimal bonus_sum;
    /* 每个分红 */
    private BigDecimal each_bonus;
    /* 我的持币 */
    private BigDecimal coin_num;
    /* 我的分红 */
    private BigDecimal bonus;
    /* 时间 */
    private Date date;
    /* 备用字段 */
    private String attr1;
    /* 备用字段 */
    private String attr2;

    public BonusRecordDto() {
    }

    public BonusRecordDto(Integer user_no, Integer bonus_type, String bonus_name, Integer reward_type, BigDecimal bonus_sum, BigDecimal each_bonus, BigDecimal coin_num, BigDecimal bonus, Date date) {
        this.user_no = user_no;
        this.bonus_type = bonus_type;
        this.bonus_name = bonus_name;
        this.reward_type = reward_type;
        this.bonus_sum = bonus_sum;
        this.each_bonus = each_bonus;
        this.coin_num = coin_num;
        this.bonus = bonus;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public Integer getBonus_type() {
        return bonus_type;
    }

    public String getBonus_name() {
        return bonus_name;
    }

    public Integer getReward_type() {
        return reward_type;
    }

    public BigDecimal getBonus_sum() {
        return bonus_sum;
    }

    public BigDecimal getEach_bonus() {
        return each_bonus;
    }

    public BigDecimal getCoin_num() {
        return coin_num;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public Date getDate() {
        return date;
    }

    public String getAttr1() {
        return attr1;
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

    public void setBonus_type(Integer bonus_type) {
        this.bonus_type = bonus_type;
    }

    public void setBonus_name(String bonus_name) {
        this.bonus_name = bonus_name;
    }

    public void setReward_type(Integer reward_type) {
        this.reward_type = reward_type;
    }

    public void setBonus_sum(BigDecimal bonus_sum) {
        this.bonus_sum = bonus_sum;
    }

    public void setEach_bonus(BigDecimal each_bonus) {
        this.each_bonus = each_bonus;
    }

    public void setCoin_num(BigDecimal coin_num) {
        this.coin_num = coin_num;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    @Override
    public String toString() {
        return "BonusRecordDto{" +
                "id=" + id +
                ", user_no=" + user_no +
                ", bonus_type=" + bonus_type +
                ", bonus_name='" + bonus_name + '\'' +
                ", reward_type=" + reward_type +
                ", bonus_sum=" + bonus_sum +
                ", each_bonus=" + each_bonus +
                ", coin_num=" + coin_num +
                ", bonus=" + bonus +
                ", date=" + date +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                '}';
    }
}
