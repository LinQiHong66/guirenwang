package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/06/07 0008.
 */
public class KDealDetailDto {

	/** id */
	private String id_num;
	/** 开盘价格 */
	private BigDecimal begin_price;
	/** 收盘价格 */
	private BigDecimal end_price;
	/** 总涨跌差价 */
	private String difference_price;
	/** 涨跌百分比 */
	private String difference_percentage;
	/** 最低价 */
	private BigDecimal min_price;
	/** 最高价 */
	private BigDecimal max_price;
	/** 开盘时间 */
	private Date begin_date;
	/** 收盘时间 */
	private Date end_date;
	/** 开盘时间 */
	private String begin_date_num;
	/** 收盘时间 */
	private String end_date_num;
	/** 货币类型 */
	private String price_type;
	/** 成交量*/
	private String deal_num;
	/** 备用字段1 */
	private String attr1;
	/** 备用字段2 */
	private String attr2;

	public String getId_num() {
		return id_num;
	}

	public void setId_num(String id_num) {
		this.id_num = id_num;
	}

	public BigDecimal getBegin_price() {
		return begin_price;
	}

	public void setBegin_price(BigDecimal begin_price) {
		this.begin_price = begin_price;
	}

	public BigDecimal getEnd_price() {
		return end_price;
	}

	public void setEnd_price(BigDecimal end_price) {
		this.end_price = end_price;
	}

	public String getDifference_price() {
		return difference_price;
	}

	public void setDifference_price(String difference_price) {
		this.difference_price = difference_price;
	}

	public String getDifference_percentage() {
		return difference_percentage;
	}

	public void setDifference_percentage(String difference_percentage) {
		this.difference_percentage = difference_percentage;
	}

	public BigDecimal getMin_price() {
		return min_price;
	}

	public void setMin_price(BigDecimal min_price) {
		this.min_price = min_price;
	}

	public BigDecimal getMax_price() {
		return max_price;
	}

	public void setMax_price(BigDecimal max_price) {
		this.max_price = max_price;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public String getBegin_date_num() {
		return begin_date_num;
	}

	public void setBegin_date_num(String begin_date_num) {
		this.begin_date_num = begin_date_num;
	}

	public String getEnd_date_num() {
		return end_date_num;
	}

	public void setEnd_date_num(String end_date_num) {
		this.end_date_num = end_date_num;
	}

	public String getPrice_type() {
		return price_type;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}
	
	public String getDeal_num() {
		return deal_num;
	}

	public void setDeal_num(String deal_num) {
		this.deal_num = deal_num;
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
