package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class UserBalanceViewDto {

	@Override
	public String toString() {
		return "UserBalanceViewDto [date=" + date + ", coin=" + coin + ", enable_coin=" + enable_coin + ", unable_coin="
				+ unable_coin + ", total_price=" + total_price + "]";
	}
	/** 日期 */
	private String date;
	/** 币种 */
	private String coin;
 
	/** 可用货币 */
	private BigDecimal enable_coin;
	/** 冻结货币 */
	private BigDecimal unable_coin;
	/** 总资产 */
	private BigDecimal total_price;
	
	/** 币种编码 */
	private String coinCode;
	
	
	
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public BigDecimal getEnable_coin() {
		return enable_coin;
	}
	public void setEnable_coin(BigDecimal enable_coin) {
		this.enable_coin = enable_coin;
	}
	public BigDecimal getUnable_coin() {
		return unable_coin;
	}
	public void setUnable_coin(BigDecimal unable_coin) {
		this.unable_coin = unable_coin;
	}
	public BigDecimal getTotal_price() {
		return total_price;
	}
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}
 
 
}
