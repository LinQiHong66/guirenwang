package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * 众筹项目Dto
 * Created by JimJim on 2017/06/05 0017.
 */
public class CoinTranTypeDto {
	private Integer id;
	
	private Integer coin_no;
	
	private Integer tran_coin_no;
	
	private Integer state;
	
    private Date date;
    
    private String attr1;
    
    private String attr2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCoin_no() {
		return coin_no;
	}

	public void setCoin_no(Integer coin_no) {
		this.coin_no = coin_no;
	}

	public Integer getTran_coin_no() {
		return tran_coin_no;
	}

	public void setTran_coin_no(Integer tran_coin_no) {
		this.tran_coin_no = tran_coin_no;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
