package com.inesv.digiccy.dto;

import java.math.BigDecimal;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
public class CoinDetailDto {
	
	/*
	 * 最新价
	 */
	private BigDecimal newPrice;
	/*
	 * 买一价
	 */
	private BigDecimal buyPrice;
	/*
	 * 卖一价
	 */
	private BigDecimal sellPrice;
	/*
	 * 最高价
	 */
	private BigDecimal maxPrice;
	/*
	 * 最低价
	 */
	private BigDecimal minPrice;
	/*
	 * 成交量
	 */
	private BigDecimal volume;
	/*
	 * 成交额
	 */
	private BigDecimal turnover;
	/*
	 * 日涨跌
	 */
	private String highsAndLows;

    private String attr1;

    private String attr2;

    public BigDecimal getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public String getHighsAndLows() {
		return highsAndLows;
	}

	public void setHighsAndLows(String highsAndLows) {
		this.highsAndLows = highsAndLows;
	}
	
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
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
