package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JimJim on 2016/12/7 0007.
 */
public class BonusDetailDto {

    private Integer id;

    private String bonus_name;

    private Integer coin_type;

    private BigDecimal num;

    private Date date;

    private Integer state;

    private String attr1;

    private String attr2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBonus_name() {
        return bonus_name;
    }

    public void setBonus_name(String bonus_name) {
        this.bonus_name = bonus_name;
    }

    public Integer getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(Integer coin_type) {
        this.coin_type = coin_type;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }
}
