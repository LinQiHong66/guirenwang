package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class RegUserEvent {
    private Integer id;
    /**用户名*/
    private String username;
    /**用户名*/
    private Integer user_no;
    /**密码*/
    private String password;
    /**地区*/
    private String region;
    /**姓名*/
    private String real_name;
    /**证件号码*/
    private String certificate_num;
    /**交易密码*/
    private String deal_pwd;
    /**邮箱*/
    private String mail;
    /**手机号码*/
    private String phone;
    /**状态*/
    private Integer state;
    /**邀请码*/
    private String invite_num;
    /**操作时间*/
    private Date date;
    /** 操作类型*/
    private String operation;
    /**机构类型 0:顶级机构   1:经纪人   2:普通客户 */
    private Integer org_type;
    /**机构编码*/
    private String org_code;
    /**父机构编码*/
    private String org_parent_code;
	public RegUserEvent(Integer id, String username, Integer user_no,
			String password, String region, String real_name,
			String certificate_num, String deal_pwd, String mail, String phone,
			Integer state, String invite_num, Date date, String operation,Integer org_type,
			String org_code,String org_parent_code) {
		this.id = id;
		this.username = username;
		this.user_no = user_no;
		this.password = password;
		this.region = region;
		this.real_name = real_name;
		this.certificate_num = certificate_num;
		this.deal_pwd = deal_pwd;
		this.mail = mail;
		this.phone = phone;
		this.state = state;
		this.invite_num = invite_num;
		this.date = date;
		this.operation = operation;
		this.org_type=org_type;
		this.org_code=org_code;
		this.org_parent_code=org_parent_code;
	}
	public Integer getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public Integer getUser_no() {
		return user_no;
	}
	public String getPassword() {
		return password;
	}
	public String getRegion() {
		return region;
	}
	public String getReal_name() {
		return real_name;
	}
	public String getCertificate_num() {
		return certificate_num;
	}
	public String getDeal_pwd() {
		return deal_pwd;
	}
	public String getMail() {
		return mail;
	}
	public String getPhone() {
		return phone;
	}
	public Integer getState() {
		return state;
	}
	public String getInvite_num() {
		return invite_num;
	}
	public Date getDate() {
		return date;
	}
	public String getOperation() {
		return operation;
	}
	public Integer getOrg_type() {
		return org_type;
	}
	public void setOrg_type(Integer org_type) {
		this.org_type = org_type;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getOrg_parent_code() {
		return org_parent_code;
	}
	public void setOrg_parent_code(String org_parent_code) {
		this.org_parent_code = org_parent_code;
	}

}
