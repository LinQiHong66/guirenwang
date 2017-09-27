package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 众筹项目详情Dto
 * Created by JimJim on 2016/11/17 0017.
 */
public class CrowdFundingDetailsDto {

    private Long id;
    /** 用户编号 */
    private Integer user_id;
    /** 众筹项目编号 */
    private String ico_id;
    /** 参与数量 */
    private Integer ico_user_number;
    /** 参与总金额 */
    private BigDecimal ico_user_sumprice;

    private Date date;

    private String attr1;

    private String attr2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getIco_id() {
		return ico_id;
	}

	public void setIco_id(String ico_id) {
		this.ico_id = ico_id;
	}

	public Integer getIco_user_number() {
		return ico_user_number;
	}

	public void setIco_user_number(Integer ico_user_number) {
		this.ico_user_number = ico_user_number;
	}

	public BigDecimal getIco_user_sumprice() {
		return ico_user_sumprice;
	}

	public void setIco_user_sumprice(BigDecimal ico_user_sumprice) {
		this.ico_user_sumprice = ico_user_sumprice;
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
