package com.inesv.digiccy.dto;

import java.math.BigDecimal;

/**
 * Created by JimJim on 2016/12/7 0007.
 */
public class CoinCountDto {

    private Integer coinId;

    private String coinname;

    private BigDecimal total;

    public Integer getCoinId() {
        return coinId;
    }

    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
    }

    public String getCoinname() {
        return coinname;
    }

    public void setCoinname(String coinname) {
        this.coinname = coinname;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
