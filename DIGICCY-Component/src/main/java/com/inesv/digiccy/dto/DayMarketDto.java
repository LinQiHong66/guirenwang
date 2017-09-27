package com.inesv.digiccy.dto;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

import java.math.BigDecimal;
import java.util.Date;


/**
 * 每日行情
 * */
public class DayMarketDto {
    private long id;
    /**货币种类/交易市场*/
    private Integer coin_type;
    /**最新成交价格*/
    private BigDecimal newes_deal;
    /**买一价*/
    private BigDecimal buy_price;
    /**卖一价*/
    private BigDecimal sell_price;
    /**成交量*/
    private BigDecimal deal_num;
    /**成交额*/
    private BigDecimal deal_price;
    /**日涨跌*/
    private String day_percent;
    /** 最高价 */
    private BigDecimal max_price;
    /** 最低价 */
    private BigDecimal min_price;
    /**状态*/
    private  Integer state;
    /**时间*/
    private String date;
    /**备用字段1*/
    private String attr1;
    /**备用字段2*/
    private String attr2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(Integer coin_type) {
        this.coin_type = coin_type;
    }

    public BigDecimal getNewes_deal() {
        return newes_deal;
    }

    public void setNewes_deal(BigDecimal newes_deal) {
        this.newes_deal = newes_deal;
    }

    public BigDecimal getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(BigDecimal buy_price) {
        this.buy_price = buy_price;
    }

    public BigDecimal getSell_price() {
        return sell_price;
    }

    public void setSell_price(BigDecimal sell_price) {
        this.sell_price = sell_price;
    }

    public BigDecimal getDeal_num() {
        return deal_num;
    }

    public void setDeal_num(BigDecimal deal_num) {
        this.deal_num = deal_num;
    }

    public BigDecimal getDeal_price() {
		return deal_price;
	}

	public void setDeal_price(BigDecimal deal_price) {
		this.deal_price = deal_price;
	}

	public String getDay_percent() {
        return day_percent;
    }

    public void setDay_percent(String day_percent) {
        this.day_percent = day_percent;
    }
    
    public BigDecimal getMax_price() {
		return max_price;
	}

	public void setMax_price(BigDecimal max_price) {
		this.max_price = max_price;
	}

	public BigDecimal getMin_price() {
		return min_price;
	}

	public void setMin_price(BigDecimal min_price) {
		this.min_price = min_price;
	}

	public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

	@Override
	public String toString() {
		return "DayMarketDto [id=" + id + ", coin_type=" + coin_type
				+ ", newes_deal=" + newes_deal + ", buy_price=" + buy_price
				+ ", sell_price=" + sell_price + ", deal_num=" + deal_num
				+ ", deal_price=" + deal_price + ", day_percent=" + day_percent
				+ ", max_price=" + max_price + ", min_price=" + min_price
				+ ", state=" + state + ", date=" + date + ", attr1=" + attr1
				+ ", attr2=" + attr2 + "]";
	}

	public DayMarketDto() {
	}
}
