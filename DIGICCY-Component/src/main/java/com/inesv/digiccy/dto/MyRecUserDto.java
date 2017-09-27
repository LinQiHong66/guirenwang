package com.inesv.digiccy.dto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class MyRecUserDto {

    /* 获得收益金额 */
    private BigDecimal price;
    /* 注册人数 */
    private Integer register;
    /* 充值人数 */
    private Integer recharge;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public Integer getRecharge() {
        return recharge;
    }

    public void setRecharge(Integer recharge) {
        this.recharge = recharge;
    }
}
