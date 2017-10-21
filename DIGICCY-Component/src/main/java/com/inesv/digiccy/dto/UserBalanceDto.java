package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class UserBalanceDto {
	/** 编号 */
	private long id;
	/** 用户编号 */
	private Integer user_no;
	/** 货币类型 0:人民币，1:比特币 2:宏强股 3:莱特币*/
	private Integer coin_type;
	/** 可用货币 */
	private BigDecimal enable_coin;
	/** 冻结货币 */
	private BigDecimal unable_coin;
	/** 总资产 */
	private BigDecimal total_price;

	/** 时间 */
	private Date date;
	/** 备用字段1 */
	private String attr1;
	/** 备用字段2 */
	private String attr2;
	

	/** 币种代码 */
	private String coinCode;
	/** 当前市价*/
	private double curPrice;
	/** 总市价 */
	private double sumCurPrice;
 
	private String userName;
 
	private String dates;
 
	
	private String realName;
	
	private String userCode;
	
	/** 币种名称*/
	private String coinName;
 
	/** 钱包地址 */
	private String wallet_address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getUser_no() {
		return user_no;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}

	public Integer getCoin_type() {
		return coin_type;
	}

	public void setCoin_type(Integer coin_type) {
		this.coin_type = coin_type;
	}

	public BigDecimal getEnable_coin() {
		return enable_coin;
	}

	public void setEnable_coin(BigDecimal enable_coin) {
		this.enable_coin = enable_coin;
	}

	public BigDecimal getUnable_coin() {
		return unable_coin;
	}

	public void setUnable_coin(BigDecimal unable_coin) {
		this.unable_coin = unable_coin;
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
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

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public double getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(double curPrice) {
		this.curPrice = curPrice;
	}

	public double getSumCurPrice() {
		return sumCurPrice;
	}

	public void setSumCurPrice(double sumCurPrice) {
		this.sumCurPrice = sumCurPrice;
	}
 
 

	public String getUserName() {
		return userName;
	}

	public String getDates() {
		return dates;
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

	public String getWallet_address() {
		return wallet_address;
	}

	public void setWallet_address(String wallet_address) {
		this.wallet_address = wallet_address;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}
	
 

}
