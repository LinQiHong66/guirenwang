package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class RmbWithdrawDto {

    /**编号*/
    private Integer id;
    /**用户编号*/
    private int user_no;
    /**银行编号*/
    private int bank;
    /**银行名*/
    private String bankName;
    /**银行名*/
    private String remark_name;
    /**提现金额*/
    private BigDecimal price;
    /**手续费*/
    private BigDecimal poundage;
    /**到账金额*/
    private BigDecimal actual_price;
    /**提现时间*/
    private Date date;
    /**状态*/
    private Integer state;
    /**备用字段1*/
    private String attr1;
    /**备用字段2*/
    private String attr2;
    /**备用字段3*/
    private String attr3;

    public RmbWithdrawDto() {

    }

    public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public String getRemark_name() {
        return remark_name;
    }

    public void setRemark_name(String remark_name) {
        this.remark_name = remark_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public BigDecimal getActual_price() {
        return actual_price;
    }

    public void setActual_price(BigDecimal actual_price) {
        this.actual_price = actual_price;
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
    
    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
