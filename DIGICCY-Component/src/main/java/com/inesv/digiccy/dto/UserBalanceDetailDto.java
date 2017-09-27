package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 众筹项目Dto
 * Created by JimJim on 2017/06/05 0017.
 */
public class UserBalanceDetailDto {

	private Long id;
    private Integer user_no;
    private String admin_no;
    private Integer coin_type;
    private BigDecimal coin_price;
    private String remark;
    private Date date;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUser_no() {
		return user_no;
	}
	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}
	public String getAdmin_no() {
		return admin_no;
	}
	public void setAdmin_no(String admin_no) {
		this.admin_no = admin_no;
	}
	public Integer getCoin_type() {
		return coin_type;
	}
	public void setCoin_type(Integer coin_type) {
		this.coin_type = coin_type;
	}
	public BigDecimal getCoin_price() {
		return coin_price;
	}
	public void setCoin_price(BigDecimal coin_price) {
		this.coin_price = coin_price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
    
}
