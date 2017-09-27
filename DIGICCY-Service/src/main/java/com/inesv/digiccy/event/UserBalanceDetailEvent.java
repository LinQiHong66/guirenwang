package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class UserBalanceDetailEvent {
	private Long id;
    private Integer user_no;
    private String admin_no;
    private Integer coin_type;
    private BigDecimal coin_price;
    private String remark;
    private Date date;
    /**操作类型*/
    private String operation;
    
	public UserBalanceDetailEvent(Long id, Integer user_no, String admin_no,
			Integer coin_type, BigDecimal coin_price, String remark, Date date,
			String operation) {
		this.id = id;
		this.user_no = user_no;
		this.admin_no = admin_no;
		this.coin_type = coin_type;
		this.coin_price = coin_price;
		this.remark = remark;
		this.date = date;
		this.operation = operation;
	}
	public Long getId() {
		return id;
	}
	public Integer getUser_no() {
		return user_no;
	}
	public String getAdmin_no() {
		return admin_no;
	}
	public Integer getCoin_type() {
		return coin_type;
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
	public Date getDate() {
		return date;
	}
	public String getOperation() {
		return operation;
	}
    
}
