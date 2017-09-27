package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class SubCoreCommand {

    @TargetAggregateIdentifier
    private long id;
    private String sub_name;
    private int coin_type;
    private BigDecimal price;
    private BigDecimal num;
    private BigDecimal already;
    private BigDecimal limit_buy;
    private Date date;
    private Integer thaw_num;
    private Integer cycle;
    private Integer status;
    private String operation;
    private String photo;

    public SubCoreCommand(long id, String sub_name, int coin_type, BigDecimal price, BigDecimal num, BigDecimal already, BigDecimal limit_buy,
                          Date date, Integer thaw_num, Integer cycle, Integer status ,String operation) {
        this.id = id;
        this.sub_name = sub_name;
        this.coin_type = coin_type;
        this.price = price;
        this.num = num;
        this.already = already;
        this.limit_buy = limit_buy;
        this.date = date;
        this.thaw_num = thaw_num;
        this.cycle = cycle;
        this.status = status;
        this.photo = photo;
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public String getSub_name() {
        return sub_name;
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

    public Integer getThaw_num() {
        return thaw_num;
    }

    public Integer getCycle() {
        return cycle;
    }

    public String getOperation() {
        return operation;
    }

    public Integer getStatus() {
        return status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
