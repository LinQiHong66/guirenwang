package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 众筹项目Dto
 * Created by JimJim on 2017/06/05 0017.
 */
public class CoinTranAstrictDto {

	private Long id;
	
	private Integer coin_no;
	
	private BigDecimal buy_min_price;
	
	private BigDecimal buy_max_price;
	
	private BigDecimal sell_min_price;
	
	private BigDecimal sell_max_price;
	
	private BigDecimal single_min_price;
	
	private BigDecimal single_max_price;
	
	private Double rose_astrict;
	
	private Double drop_astrict;
	
	private Date begin_date;
	
	private Date end_date;
	
	private Integer state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCoin_no() {
		return coin_no;
	}

	public void setCoin_no(Integer coin_no) {
		this.coin_no = coin_no;
	}

	public BigDecimal getBuy_min_price() {
		return buy_min_price;
	}

	public void setBuy_min_price(BigDecimal buy_min_price) {
		this.buy_min_price = buy_min_price;
	}

	public BigDecimal getBuy_max_price() {
		return buy_max_price;
	}

	public void setBuy_max_price(BigDecimal buy_max_price) {
		this.buy_max_price = buy_max_price;
	}

	public BigDecimal getSell_min_price() {
		return sell_min_price;
	}

	public void setSell_min_price(BigDecimal sell_min_price) {
		this.sell_min_price = sell_min_price;
	}

	public BigDecimal getSell_max_price() {
		return sell_max_price;
	}

	public void setSell_max_price(BigDecimal sell_max_price) {
		this.sell_max_price = sell_max_price;
	}

	public BigDecimal getSingle_min_price() {
		return single_min_price;
	}

	public void setSingle_min_price(BigDecimal single_min_price) {
		this.single_min_price = single_min_price;
	}

	public BigDecimal getSingle_max_price() {
		return single_max_price;
	}

	public void setSingle_max_price(BigDecimal single_max_price) {
		this.single_max_price = single_max_price;
	}

	public Double getRose_astrict() {
		return rose_astrict;
	}

	public void setRose_astrict(Double rose_astrict) {
		this.rose_astrict = rose_astrict;
	}

	public Double getDrop_astrict() {
		return drop_astrict;
	}

	public void setDrop_astrict(Double drop_astrict) {
		this.drop_astrict = drop_astrict;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
}
