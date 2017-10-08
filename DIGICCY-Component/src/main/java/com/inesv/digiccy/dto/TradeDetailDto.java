package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
public class TradeDetailDto {

    private Integer coin_type;
    
    private Long buy_user;
    
    private String buy_price;
    
    private String buy_number;
    
    private String buy_poundatge;
    
    private String sell_user;
    
    private String sell_price;
    
    private String sell_number;
    
    private String sell_poundatge;
    
    private String buy_entrust;
    
    private String sell_entrusr;
    
    private Date date;
    
    private String end_buy_rmb_price;
    
    private String end_buy_coin_price;
    
    private String end_sell_rmb_price;

    private String end_sell_coin_price;
    
	public Integer getCoin_type() {
		return coin_type;
	}

	public void setCoin_type(Integer coin_type) {
		this.coin_type = coin_type;
	}

	public Long getBuy_user() {
		return buy_user;
	}

	public void setBuy_user(Long buy_user) {
		this.buy_user = buy_user;
	}

	public String getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(String buy_price) {
		this.buy_price = buy_price;
	}

	public String getBuy_number() {
		return buy_number;
	}

	public void setBuy_number(String buy_number) {
		this.buy_number = buy_number;
	}

	public String getBuy_poundatge() {
		return buy_poundatge;
	}

	public void setBuy_poundatge(String buy_poundatge) {
		this.buy_poundatge = buy_poundatge;
	}

	public String getSell_user() {
		return sell_user;
	}

	public void setSell_user(String sell_user) {
		this.sell_user = sell_user;
	}

	public String getSell_price() {
		return sell_price;
	}

	public void setSell_price(String sell_price) {
		this.sell_price = sell_price;
	}

	public String getSell_number() {
		return sell_number;
	}

	public void setSell_number(String sell_number) {
		this.sell_number = sell_number;
	}

	public String getSell_poundatge() {
		return sell_poundatge;
	}

	public void setSell_poundatge(String sell_poundatge) {
		this.sell_poundatge = sell_poundatge;
	}
	
	public String getBuy_entrust() {
		return buy_entrust;
	}

	public void setBuy_entrust(String buy_entrust) {
		this.buy_entrust = buy_entrust;
	}

	public String getSell_entrusr() {
		return sell_entrusr;
	}

	public void setSell_entrusr(String sell_entrusr) {
		this.sell_entrusr = sell_entrusr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEnd_buy_rmb_price() {
		return end_buy_rmb_price;
	}

	public void setEnd_buy_rmb_price(String end_buy_rmb_price) {
		this.end_buy_rmb_price = end_buy_rmb_price;
	}

	public String getEnd_buy_coin_price() {
		return end_buy_coin_price;
	}

	public void setEnd_buy_coin_price(String end_buy_coin_price) {
		this.end_buy_coin_price = end_buy_coin_price;
	}

	public String getEnd_sell_rmb_price() {
		return end_sell_rmb_price;
	}

	public void setEnd_sell_rmb_price(String end_sell_rmb_price) {
		this.end_sell_rmb_price = end_sell_rmb_price;
	}

	public String getEnd_sell_coin_price() {
		return end_sell_coin_price;
	}

	public void setEnd_sell_coin_price(String end_sell_coin_price) {
		this.end_sell_coin_price = end_sell_coin_price;
	}
	
}
