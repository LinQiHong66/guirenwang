package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
public class TradeDetailDifferenceDto {

    private Long id;
    
    private String buy_user;
    
    private String sell_user;
    
    private String buy_entrust_no;
    
    private String sell_entrust_no;
    
    private String entrust_coin;
    
    private String convert_coin;
    
    private String trade_num;
    
    private String buy_price;
    
    private String buy_sum_price;
    
    private String sell_price;
    
    private String sell_sum_price;
    
    private String rmb_difference_price;
    
    private String xnb_difference_price;
    
    private Date date;
    
    private String attr1;
    
    private String attr2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuy_user() {
		return buy_user;
	}

	public void setBuy_user(String buy_user) {
		this.buy_user = buy_user;
	}

	public String getSell_user() {
		return sell_user;
	}

	public void setSell_user(String sell_user) {
		this.sell_user = sell_user;
	}

	public String getBuy_entrust_no() {
		return buy_entrust_no;
	}

	public void setBuy_entrust_no(String buy_entrust_no) {
		this.buy_entrust_no = buy_entrust_no;
	}

	public String getSell_entrust_no() {
		return sell_entrust_no;
	}

	public void setSell_entrust_no(String sell_entrust_no) {
		this.sell_entrust_no = sell_entrust_no;
	}

	public String getEntrust_coin() {
		return entrust_coin;
	}

	public void setEntrust_coin(String entrust_coin) {
		this.entrust_coin = entrust_coin;
	}

	public String getConvert_coin() {
		return convert_coin;
	}

	public void setConvert_coin(String convert_coin) {
		this.convert_coin = convert_coin;
	}

	public String getTrade_num() {
		return trade_num;
	}

	public void setTrade_num(String trade_num) {
		this.trade_num = trade_num;
	}

	public String getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(String buy_price) {
		this.buy_price = buy_price;
	}

	public String getBuy_sum_price() {
		return buy_sum_price;
	}

	public void setBuy_sum_price(String buy_sum_price) {
		this.buy_sum_price = buy_sum_price;
	}

	public String getSell_price() {
		return sell_price;
	}

	public void setSell_price(String sell_price) {
		this.sell_price = sell_price;
	}

	public String getSell_sum_price() {
		return sell_sum_price;
	}

	public void setSell_sum_price(String sell_sum_price) {
		this.sell_sum_price = sell_sum_price;
	}

	public String getRmb_difference_price() {
		return rmb_difference_price;
	}

	public void setRmb_difference_price(String rmb_difference_price) {
		this.rmb_difference_price = rmb_difference_price;
	}

	public String getXnb_difference_price() {
		return xnb_difference_price;
	}

	public void setXnb_difference_price(String xnb_difference_price) {
		this.xnb_difference_price = xnb_difference_price;
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
    
}
