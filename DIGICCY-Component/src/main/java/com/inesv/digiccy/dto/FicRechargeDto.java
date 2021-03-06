package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class FicRechargeDto {

 

	/**编号*/
    private Integer id;
    /**用户编号*/
    private int user_no;
    /**货币种类*/
    private int coin_no;
    /**充值地址*/
    private String address;
    /**实际充值*/
    private BigDecimal actual_price;
    /**赠送*/
    private BigDecimal give_price;
    /**总到账量*/
    private BigDecimal sum_price;
    /**状态*/
    private int state;
    /**日期*/
    private Date date;
    /**交易id*/
    private String tixid;
    /**备用字段*/
    private String attr1;
    /**备用字段2*/
    private String attr2;
    
    private String userName;
    private String realName;
    private String userCode;
    private String coinName;
    private String addressFrom;
    private String number;
    private String realNumber;
 
 
    

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public String getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(String addressFrom) {
		this.addressFrom = addressFrom;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRealNumber() {
		return realNumber;
	}

	public void setRealNumber(String realNumber) {
		this.realNumber = realNumber;
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

    public int getCoin_no() {
        return coin_no;
    }

    public void setCoin_no(int coin_no) {
        this.coin_no = coin_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getActual_price() {
        return actual_price;
    }

    public void setActual_price(BigDecimal actual_price) {
        this.actual_price = actual_price;
    }

    public BigDecimal getGive_price() {
        return give_price;
    }

    public void setGive_price(BigDecimal give_price) {
        this.give_price = give_price;
    }

    public BigDecimal getSum_price() {
        return sum_price;
    }

    public void setSum_price(BigDecimal sum_price) {
        this.sum_price = sum_price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }



    
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTixid() {
        return tixid;
    }

    public void setTixid(String tixid) {
        this.tixid = tixid;
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
