package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class ThawCommand {

    /* 编号 */
    @TargetAggregateIdentifier
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 货币编号 */
    private Integer coin_no;
    /* 认购名称 */
    private String sub_name;
    /* 认购价格 */
    private BigDecimal sub_price;
    /* 认购数量 */
    private BigDecimal sub_num;
    /* 认购总价 */
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
    /* 备用字段 */
    private BigDecimal thaw_sum;
    /* 备用字段 */
    private String attr2;

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

    public ThawCommand(long id ,Integer user_no, Integer coin_no, String sub_name, BigDecimal sub_price, BigDecimal sub_num, BigDecimal sum_price, Integer thaw_num, Date thaw_time, BigDecimal sur_frozen, Integer state, Date date, BigDecimal thaw_sum) {
        this.id = id;
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

    @Override
    public String toString() {
        return "SubRecordCommand{" +
                "id=" + id +
                ", user_no=" + user_no +
                ", coin_no=" + coin_no +
                ", sub_name='" + sub_name + '\'' +
                ", sub_price=" + sub_price +
                ", sub_num=" + sub_num +
                ", sum_price=" + sum_price +
                ", thaw_num=" + thaw_num +
                ", thaw_time=" + thaw_time +
                ", sur_frozen=" + sur_frozen +
                ", state=" + state +
                ", date=" + date +
                ", thaw_sum=" + thaw_sum +
                ", attr2='" + attr2 + '\'' +
                '}';
    }

}
