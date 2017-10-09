package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinTranTypeEvent {

private Integer id;
	
	private Integer coin_no;
	
	private Integer tran_coin_no;
	
	private Integer state;
	
    private Date date;
    
    private String attr1;
    
    private String attr2;

    private String operation;

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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public CoinTranTypeEvent(Integer id, Integer coin_no, Integer tran_coin_no, Integer state, Date date, String attr1,
			String attr2, String operation) {
		this.id = id;
		this.coin_no = coin_no;
		this.tran_coin_no = tran_coin_no;
		this.state = state;
		this.date = date;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.operation = operation;
	}
	
}
