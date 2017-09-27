package com.inesv.digiccy.dto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class BounsInfoDto {

    /* 货币类型 */
    private Integer coin_type;
    /* 货币总额 */
    private BigDecimal sumCoin;
    /* 我的持币 */
    private BigDecimal myCoin;
    /* 比例 */
    private String ratio;

    public Integer getCoin_type() {
        return coin_type;
    }

    public BigDecimal getSumCoin() {
        return sumCoin;
    }

    public BigDecimal getMyCoin() {
        return myCoin;
    }

    public void setCoin_type(Integer coin_type) {
        this.coin_type = coin_type;
    }

    public void setSumCoin(BigDecimal sumCoin) {
        this.sumCoin = sumCoin;
    }

    public void setMyCoin(BigDecimal myCoin) {
        this.myCoin = myCoin;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}
