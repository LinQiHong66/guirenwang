package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class pricesSumsDto {

	 //24小时交易额
	 private BigDecimal prices;
	 //24小时交易量
	 private BigDecimal sums;
	public BigDecimal getPrices() {
		return prices;
	}
	public void setPrices(BigDecimal prices) {
		this.prices = prices;
	}
	public BigDecimal getSums() {
		return sums;
	}
	public void setSums(BigDecimal sums) {
		this.sums = sums;
	}
	 
	 
}
