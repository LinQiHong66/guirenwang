package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JimJim on 2016/12/7 0007.
 */
public class BonusDetailCommand {

    @TargetAggregateIdentifier
    private Integer bonusDetailId;

    private String bonus_name;

    private Integer coin_type;

    private BigDecimal num;

    private Date date;

    private Integer state;

    private String operation;

    public BonusDetailCommand(Integer bonusDetailId, String bonus_name, Integer coin_type, BigDecimal num, Date date, Integer state, String operation) {
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

    public String getBonus_name() {
        return bonus_name;
    }

    public Integer getCoin_type() {
        return coin_type;
    }

    public BigDecimal getNum() {
        return num;
    }

    public Date getDate() {
        return date;
    }

    public Integer getState() {
        return state;
    }

    public String getOperation() {
        return operation;
    }
}
