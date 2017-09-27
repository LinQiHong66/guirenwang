package com.inesv.digiccy.api.command;

import java.math.BigDecimal;
import java.util.Date;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class DayMarketCommand {

    @TargetAggregateIdentifier
    private Long id;
    /** 货币种类/交易市场  0:人民币，1:比特币 2:宏强股 3:莱特币*/
    private Integer coin_type;
    /** 最新成交价格 */
    private BigDecimal newes_deal;
    /** 买一价 */
    private BigDecimal buy_price;
    /** 卖一价 */
    private BigDecimal sell_price;
    /** 成交量 */
    private BigDecimal deal_num;
    /** 成交额 */
    private BigDecimal deal_price;
    /** 日涨跌 */
    private BigDecimal day_percent;
    /** 最高价 */
    private BigDecimal max_price;
    /** 最低价 */
    private BigDecimal min_price;
    /** 状态  */
    private Integer state;
    /** 时间 */
    private Date date;
    /** 操作类型*/
    private String operation;
	public Long getId() {
		return id;
	}
	public Integer getCoin_type() {
		return coin_type;
	}
	public BigDecimal getNewes_deal() {
		return newes_deal;
	}
	public BigDecimal getBuy_price() {
		return buy_price;
	}
	public BigDecimal getSell_price() {
		return sell_price;
	}
	public BigDecimal getDeal_num() {
		return deal_num;
	}
	public BigDecimal getDeal_price() {
		return deal_price;
	}
	public BigDecimal getDay_percent() {
		return day_percent;
	}
	public BigDecimal getMax_price() {
		return max_price;
	}
	public BigDecimal getMin_price() {
		return min_price;
	}
	public Integer getState() {
		return state;
	}
	public Date getDate() {
		return date;
	}
	public String getOperation() {
		return operation;
	}
	public DayMarketCommand(Long id, Integer coin_type, BigDecimal newes_deal,
			BigDecimal buy_price, BigDecimal sell_price, BigDecimal deal_num,
			BigDecimal deal_price, BigDecimal day_percent,
			BigDecimal max_price, BigDecimal min_price, Integer state,
			Date date, String operation) {
		this.id = id;
		this.coin_type = coin_type;
		this.newes_deal = newes_deal;
		this.buy_price = buy_price;
		this.sell_price = sell_price;
		this.deal_num = deal_num;
		this.deal_price = deal_price;
		this.day_percent = day_percent;
		this.max_price = max_price;
		this.min_price = min_price;
		this.state = state;
		this.date = date;
		this.operation = operation;
	}
	public DayMarketCommand() {
	}
}
