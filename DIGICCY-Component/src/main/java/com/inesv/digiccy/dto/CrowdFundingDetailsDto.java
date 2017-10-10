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
    
    /** 快递公司名称 */
    private String logistics_company;
    /** 单号 */
    private String logistics_number;
    /** 当前状态 */
    private String logistics_status;
    /** 当前快递公司编号 */
    private String logistics_code;

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

	public String getLogistics_company() {
		return logistics_company;
	}

	public void setLogistics_company(String logistics_company) {
		this.logistics_company = logistics_company;
	}

	public String getLogistics_number() {
		return logistics_number;
	}

	public void setLogistics_number(String logistics_number) {
		this.logistics_number = logistics_number;
	}

	public String getLogistics_status() {
		return logistics_status;
	}

	public void setLogistics_status(String logistics_status) {
		this.logistics_status = logistics_status;
	}

	public String getLogistics_code() {
		return logistics_code;
	}

	public void setLogistics_code(String logistics_code) {
		this.logistics_code = logistics_code;
	}

	@Override
	public String toString() {
		return "CrowdFundingDetailsDto [id=" + id + ", user_id=" + user_id + ", ico_id=" + ico_id + ", ico_user_number="
				+ ico_user_number + ", ico_user_sumprice=" + ico_user_sumprice + ", date=" + date + ", attr1=" + attr1
				+ ", attr2=" + attr2 + ", logistics_company=" + logistics_company + ", logistics_number="
				+ logistics_number + ", logistics_status=" + logistics_status + ", logistics_code=" + logistics_code
				+ "]";
	}
    
}
