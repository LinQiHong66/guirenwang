package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JimJim on 2016/12/7 0007.
 */
public class BonusDetaillEvent {

    private Integer bonusDetailId;

    private String bonus_name;

    private Integer coin_type;

    private BigDecimal num;

    private Date date;

    private Integer state;

    private String operation;

    public BonusDetaillEvent(Integer bonusDetailId, String bonus_name, Integer coin_type, BigDecimal num, Date date, Integer state, String operation) {
        this.bonusDetailId = bonusDetailId;
        this.bonus_name = bonus_name;
        this.coin_type = coin_type;
        this.num = num;
        this.date = date;
        this.state = state;
        this.operation = operation;
    }

    public Integer getBonusDetailId() {
        return bonusDetailId;
    }

    public void setBonusDetailId(Integer bonusDetailId) {
        this.bonusDetailId = bonusDetailId;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
