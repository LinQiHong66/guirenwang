package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class RecSubProfitDto {

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 被推荐用户编号 */
    private Integer rec_user;
    /* 货币类型 */
    private Integer coin_type;
    /* 收益货币数量 */
    private BigDecimal profit_number;
    /* 认购日期 */
    private Date sub_date;
    /* 备用字段1 */
    private String attr1;
    /*  备用字段2 */
    private String attr2;
    
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
	public Integer getRec_user() {
		return rec_user;
	}
	public void setRec_user(Integer rec_user) {
		this.rec_user = rec_user;
	}
	public Integer getCoin_type() {
		return coin_type;
	}
	public void setCoin_type(Integer coin_type) {
		this.coin_type = coin_type;
	}
	public BigDecimal getProfit_number() {
		return profit_number;
	}
	public void setProfit_number(BigDecimal profit_number) {
		this.profit_number = profit_number;
	}
	public Date getSub_date() {
		return sub_date;
	}
	public void setSub_date(Date sub_date) {
		this.sub_date = sub_date;
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
	public RecSubProfitDto(long id, Integer user_no, Integer rec_user,
			Integer coin_type, BigDecimal profit_number, Date sub_date,
			String attr1, String attr2) {
		this.id = id;
		this.user_no = user_no;
		this.rec_user = rec_user;
		this.coin_type = coin_type;
		this.profit_number = profit_number;
		this.sub_date = sub_date;
		this.attr1 = attr1;
		this.attr2 = attr2;
	}
	
	public RecSubProfitDto() {
	}
	
	public RecSubProfitDto(Integer user_no, Integer rec_user,
			Integer coin_type, BigDecimal profit_number, Date sub_date) {
		this.user_no = user_no;
		this.rec_user = rec_user;
		this.coin_type = coin_type;
		this.profit_number = profit_number;
		this.sub_date = sub_date;
	}
}
