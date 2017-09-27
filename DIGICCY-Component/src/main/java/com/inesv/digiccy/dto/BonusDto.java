package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class BonusDto {

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 货币种类 */
    private Integer coin_type;
    /* 全站总额 */
    private BigDecimal total;
    /* 我的资产 */
    private BigDecimal assets;
    /* 持有比例 */
    private BigDecimal percent;
    /* 时间 */
    private Date date;
    /* 备用字段 */
    private String attr1;
    /* 备用字段 */
    private String attr2;

    public BonusDto(){}

    public BonusDto(Integer user_no, Integer coin_type, BigDecimal total, BigDecimal assets, BigDecimal percent, Date date) {
        this.user_no = user_no;
        this.coin_type = coin_type;
        this.total = total;
        this.assets = assets;
        this.percent = percent;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public Integer getCoin_type() {
        return coin_type;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getAssets() {
        return assets;
    }

    public BigDecimal getPercent() {
        return percent;
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

    public void setCoin_type(Integer coin_type) {
        this.coin_type = coin_type;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
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
        return "BonusDto{" +
                "id=" + id +
                ", user_no=" + user_no +
                ", coin_type=" + coin_type +
                ", total=" + total +
                ", assets=" + assets +
                ", percent=" + percent +
                ", date=" + date +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                '}';
    }
}
