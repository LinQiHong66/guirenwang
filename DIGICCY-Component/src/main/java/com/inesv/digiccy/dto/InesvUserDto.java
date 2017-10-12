package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class InesvUserDto {

    /**编号*/
    private long id;
    /**用户名*/
    private String username;
    /**用户编号*/
    private int user_no;
    /**密码*/
    private String password;
    /**地区*/
    private String region;
    /**姓名*/
    private String real_name;
    /**邮箱*/
    private String mail;
    /**手机号码*/
    private String phone;
    /**手机号码状态 0未绑定  1已绑定*/
    private Integer phone_state;
    /**证件类型*/
    private int certificate_type;
    /**证件号码*/
    private String certificate_num;
    /**交易密码*/
    private String deal_pwd;
    /**交易密码状态*/
    private Integer deal_pwdstate;
    /**验证密码*/
    private String validate_pwd;
    /**验证密码状态 0未启用   1已启用*/
    private Integer validate_pwdstate;
    /**支付宝账号*/
    private String alipay;
    /**支付宝账号状态 0未绑定  1已绑定*/
    private Integer alipay_state;
    /**状态*/
    private int state;
    /**邀请码*/
    private String invite_num;
    /**操作时间*/
    private Date date;
    /**照片*/
    private String photo;
    /**0未发送 1待发送 2已发送*/
    private int photo_state;
    /**机构类型 0:顶级机构   1:经纪人   2:普通客户 */
    private Integer org_type;
    /**机构编码*/
    private String org_code;
    /**父机构编码*/
    private String org_parent_code;
    /**
     * 积分数
     */
    private String integral;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(int certificate_type) {
        this.certificate_type = certificate_type;
    }

    public String getCertificate_num() {
        return certificate_num;
    }

    public void setCertificate_num(String certificate_num) {
        this.certificate_num = certificate_num;
    }

    public String getDeal_pwd() {
        return deal_pwd;
    }

    public void setDeal_pwd(String deal_pwd) {
        this.deal_pwd = deal_pwd;
    }

    public String getValidate_pwd() {
        return validate_pwd;
    }

    public void setValidate_pwd(String validate_pwd) {
        this.validate_pwd = validate_pwd;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInvite_num() {
        return invite_num;
    }

    public void setInvite_num(String invite_num) {
        this.invite_num = invite_num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPhoto_state() {
        return photo_state;
    }

    public void setPhoto_state(int photo_state) {
        this.photo_state = photo_state;
    }

    public Integer getPhone_state() {
        return phone_state;
    }

    public void setPhone_state(Integer phone_state) {
        this.phone_state = phone_state;
    }

    public Integer getDeal_pwdstate() {
        return deal_pwdstate;
    }

    public void setDeal_pwdstate(Integer deal_pwdstate) {
        this.deal_pwdstate = deal_pwdstate;
    }

    public Integer getValidate_pwdstate() {
        return validate_pwdstate;
    }

    public void setValidate_pwdstate(Integer validate_pwdstate) {
        this.validate_pwdstate = validate_pwdstate;
    }

    public Integer getAlipay_state() {
        return alipay_state;
    }

    public void setAlipay_state(Integer alipay_state) {
        this.alipay_state = alipay_state;
    }
    
    public int getOrg_type() {
		return org_type;
	}

	public void setOrg_type(int org_type) {
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

	@Override
	public String toString() {
		return "InesvUserDto [id=" + id + ", username=" + username + ", user_no=" + user_no + ", password=" + password
				+ ", region=" + region + ", real_name=" + real_name + ", mail=" + mail + ", phone=" + phone
				+ ", phone_state=" + phone_state + ", certificate_type=" + certificate_type + ", certificate_num="
				+ certificate_num + ", deal_pwd=" + deal_pwd + ", deal_pwdstate=" + deal_pwdstate + ", validate_pwd="
				+ validate_pwd + ", validate_pwdstate=" + validate_pwdstate + ", alipay=" + alipay + ", alipay_state="
				+ alipay_state + ", state=" + state + ", invite_num=" + invite_num + ", date=" + date + ", photo="
				+ photo + ", photo_state=" + photo_state + ", org_type=" + org_type + ", org_code=" + org_code
				+ ", org_parent_code=" + org_parent_code + "]";
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
}
