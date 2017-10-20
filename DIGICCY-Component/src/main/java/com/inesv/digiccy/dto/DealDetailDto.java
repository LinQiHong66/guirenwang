package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class DealDetailDto {

	/** 编号 */
	private long id;
	/** 用户编号 */
	private Integer user_no;
	/** 货币类型 */
	private Integer coin_no;
	/** 交易类型 0：买 1：卖*/
	private Integer deal_type;
	/** 货币类型 */
	private BigDecimal deal_price; 
	/** 最佳价格 */
	private BigDecimal optimum_price;
	/** 最大交易量 */
	private BigDecimal most_amount;
	/** 比例 */
	private BigDecimal percent;
	/** 交易数量 */
	private BigDecimal deal_num;
	/** 总额 */
	private BigDecimal sum_price;
	/** 手续费比例 */
	private BigDecimal poundage_scale;
	/** 手续费 */
	private BigDecimal poundage;
	/** 时间 */
	private Date date;
	/** 备用字段1 */
	private String attr1;
	/** 备用字段2 */
	private String attr2;
	/** 备用字段2 */
	private String dates;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getUser_no() {
		return user_no;
	}
	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}
	public Integer getCoin_no() {
		return coin_no;
	}
	public void setCoin_no(Integer coin_no) {
		this.coin_no = coin_no;
	}
	public Integer getDeal_type() {
		return deal_type;
	}
	public void setDeal_type(Integer deal_type) {
		this.deal_type = deal_type;
	}
	public BigDecimal getDeal_price() {
		return deal_price;
	}
	public void setDeal_price(BigDecimal deal_price) {
		this.deal_price = deal_price;
	}
	public BigDecimal getOptimum_price() {
		return optimum_price;
	}
	public void setOptimum_price(BigDecimal optimum_price) {
		this.optimum_price = optimum_price;
	}
	public BigDecimal getMost_amount() {
		return most_amount;
	}
	public void setMost_amount(BigDecimal most_amount) {
		this.most_amount = most_amount;
	}
	public BigDecimal getPercent() {
		return percent;
	}
	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
	public BigDecimal getDeal_num() {
		return deal_num;
	}
	public void setDeal_num(BigDecimal deal_num) {
		this.deal_num = deal_num;
	}
	public BigDecimal getSum_price() {
		return sum_price;
	}
	public void setSum_price(BigDecimal sum_price) {
		this.sum_price = sum_price;
	}
	public BigDecimal getPoundage_scale() {
		return poundage_scale;
	}
	public void setPoundage_scale(BigDecimal poundage_scale) {
		this.poundage_scale = poundage_scale;
	}
	public BigDecimal getPoundage() {
		return poundage;
	}
	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	
}
