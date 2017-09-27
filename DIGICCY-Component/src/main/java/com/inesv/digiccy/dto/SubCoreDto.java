package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class SubCoreDto {

    /*编号*/
    private long id;
    /*货币类型*/
    private int coin_type;
    /*发行价格*/
    private BigDecimal price;
    /* 发行数量 */
    private BigDecimal num;
    /* 已认购 */
    private BigDecimal already;
    /* 限购 */
    private BigDecimal limit_buy;
    /* 时间 */
    private Date date;
    /* 解冻次数 */
    private Integer thaw_num;
    /* 解冻周期 */
    private Integer cycle;
    /*　货币名称　*/
    private String coin_name;
    /* 认购名称 */
    private String sub_name;
    /* 状态 */
    private Integer status;
    /* 图片 */
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getThaw_num() {
        return thaw_num;
    }

    public Integer getCycle() {
        return cycle;
    }

    public long getId() {
        return id;
    }

    public int getCoin_type() {
        return coin_type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public BigDecimal getAlready() {
        return already;
    }

    public BigDecimal getLimit_buy() {
        return limit_buy;
    }

    public Date getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCoin_type(int coin_type) {
        this.coin_type = coin_type;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public void setAlready(BigDecimal already) {
        this.already = already;
    }

    public void setLimit_buy(BigDecimal limit_buy) {
        this.limit_buy = limit_buy;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setThaw_num(Integer thaw_num) {
        this.thaw_num = thaw_num;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
