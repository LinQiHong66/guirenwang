package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class FinancialDto {

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 理财名称 */
    private String fina_name;
    /* 理财数量 */
    private BigDecimal fina_num;
    /* 年利率 */
    private BigDecimal rate;
    /* 涨息币种 */
    private Integer coin_type;
    /* 收益次数 */
    private BigDecimal profit_num;
    /* 理财周期 */
    private Integer cycle;
    /* 累计收益 */
    private BigDecimal profit_sum;
    /* 理财时间 */
    private Date fina_time;
    /* 下次利息 */
    private BigDecimal integererest;
    /* 状态 */
    private Integer state;
    /* 时间 */
    private Date date;
    /* 备用字段 */
    private String attr1;
    /* 备用字段 */
    private String attr2;

    public FinancialDto(Integer user_no, String fina_name, BigDecimal fina_num, BigDecimal rate, Integer coin_type, BigDecimal profit_num, Integer cycle, BigDecimal profit_sum, Date fina_time, BigDecimal integererest, Integer state, Date date) {
        this.user_no = user_no;
        this.fina_name = fina_name;
        this.fina_num = fina_num;
        this.rate = rate;
        this.coin_type = coin_type;
        this.profit_num = profit_num;
        this.cycle = cycle;
        this.profit_sum = profit_sum;
        this.fina_time = fina_time;
        this.integererest = integererest;
        this.state = state;
        this.date = date;
    }

    public FinancialDto() {}

    public long getId() {
        return id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public String getFina_name() {
        return fina_name;
    }

    public BigDecimal getFina_num() {
        return fina_num;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Integer getCoin_type() {
        return coin_type;
    }

    public BigDecimal getProfit_num() {
        return profit_num;
    }

    public Integer getCycle() {
        return cycle;
    }

    public BigDecimal getProfit_sum() {
        return profit_sum;
    }

    public Date getFina_time() {
        return fina_time;
    }

    public BigDecimal getIntegererest() {
        return integererest;
    }

    public Integer getState() {
        return state;
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

    public void setFina_name(String fina_name) {
        this.fina_name = fina_name;
    }

    public void setFina_num(BigDecimal fina_num) {
        this.fina_num = fina_num;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setCoin_type(Integer coin_type) {
        this.coin_type = coin_type;
    }

    public void setProfit_num(BigDecimal profit_num) {
        this.profit_num = profit_num;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public void setProfit_sum(BigDecimal profit_sum) {
        this.profit_sum = profit_sum;
    }

    public void setFina_time(Date fina_time) {
        this.fina_time = fina_time;
    }

    public void setIntegererest(BigDecimal integererest) {
        this.integererest = integererest;
    }

    public void setState(Integer state) {
        this.state = state;
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

}
