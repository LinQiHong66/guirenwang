package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class InesvUserOrganizationDto {

	 /**编号*/
    private int id;
    /**用户名*/
    private long user_no;
    /**状态*/
    private int state;
    /**机构等级*/
    private int org_type;
    /**时间*/
    private Date date;
    
    private String attr1;
    
    private String attr2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUser_no() {
		return user_no;
	}

	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getOrg_type() {
		return org_type;
	}

	public void setOrg_type(int org_type) {
		this.org_type = org_type;
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
