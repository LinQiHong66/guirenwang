package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by yc on 2016/12/19 0019.
 * 钱包地址实体类
 *
 */
public class WalletAddressDto {

	 private String userName;
	
	 private String realName;
	
	 private String userCode;
	
     private Integer size;
     
    /**备用字段1*/
    private String coinName;
	
    /**地址*/
    private String address;
 
	
    private String addressTag;
	
    /**id*/
    private long id;
    /**用户编号*/
    private int user_no;
    /**币种编号*/
    private int coin_no;
    /**钱包标识*/
    private String idtf;

    /**日期*/
    private Date date;
    /**备用字段1*/
    private String atte1;
    /**备用字段2*/
    private String atte2;

    /**备用字段2*/
    private String coinCode;

    
    public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getIdtf() {
        return idtf;
    }

    public void setIdtf(String idtf) {
        this.idtf = idtf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAtte1() {
        return atte1;
    }

    public void setAtte1(String atte1) {
        this.atte1 = atte1;
    }

    public String getAtte2() {
        return atte2;
    }

    public void setAtte2(String atte2) {
        this.atte2 = atte2;
    }

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

	public String getAddressTag() {
		return addressTag;
	}

	public void setAddressTag(String addressTag) {
		this.addressTag = addressTag;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
    
    
}
