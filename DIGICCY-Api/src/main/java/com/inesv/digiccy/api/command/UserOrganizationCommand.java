package com.inesv.digiccy.api.command;

import java.util.Date;

/**
 * Created by JimJim on 2016/12/9 0009.
 */
public class UserOrganizationCommand {

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

    private String operation;
    
	public UserOrganizationCommand(int id, long user_no, int state, int org_type, Date date, String attr1, String attr2,
			String operation) {
		this.id = id;
		this.user_no = user_no;
		this.state = state;
		this.org_type = org_type;
		this.date = date;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.operation = operation;
	}

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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

    
}
