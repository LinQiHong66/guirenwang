package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class RmbWithdrawEvent {

    /**编号*/
    private Integer id;
    /**用户编号*/
    private int user_no;
    /**银行编号*/
    private int bank;
    /**提现金额*/
    private BigDecimal price;
    /**手续费*/
    private BigDecimal poundage;
    /**到账金额*/
    private BigDecimal actual_price;
    /**状态*/
    private Integer state;
    /**提现时间*/
    private Date date;
    /** 操作类型*/
    private String operation;

    public RmbWithdrawEvent(Integer id,int user_no, int bank, BigDecimal price, BigDecimal poundage, BigDecimal actual_price, Date date,Integer state, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.bank = bank;
        this.price = price;
        this.poundage = poundage;
        this.actual_price = actual_price;
        this.date = date;
        this.state = state;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public int getUser_no() {
        return user_no;
    }

    public int getBank() {
        return bank;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public BigDecimal getActual_price() {
        return actual_price;
    }

    public Date getDate() {
        return date;
    }

    public Integer getState() {
        return state;
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}

	public void setActual_price(BigDecimal actual_price) {
		this.actual_price = actual_price;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
        return operation;
    }
}
