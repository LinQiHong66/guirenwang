package com.inesv.digiccy.dto;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class VoteInfoDto {

    /* 货币类型 */
    private Integer coin_type;
    /* 支持票数 */
    private Integer Support;
    /* 反对票数 */
    private Integer Opposition;
    /* 总票数 */
    private Integer Total;

    public Integer getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(Integer coin_type) {
        this.coin_type = coin_type;
    }

    public void setSupport(Integer support) {
        Support = support;
    }

    public void setOpposition(Integer opposition) {
        Opposition = opposition;
    }

    public void setTotal(Integer total) {
        Total = total;
    }

    public Integer getSupport() {
        return Support;
    }

    public Integer getOpposition() {
        return Opposition;
    }

    public Integer getTotal() {
        return Total;
    }

}
