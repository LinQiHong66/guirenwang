package com.inesv.digiccy.persistence.user;

import java.sql.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class InesvUserEntity {
    private long id;
    private String username;//用户名
    private long user_no;
    private String password;//密码
    private String region;//地区
    private String real_name;//姓名
    private String mail;//邮箱
    private String phone;//手机号码
    private long certificate_type;//证件类型
    private String certificate_num;//证件号码
    private String deal_pwd;//交易密码
    private String validate_pwd;//验证密码
    private String alipay;//支付宝账号
    private int state;//状态
    private String invite_num;//邀请码
    private Date date;//操作时间
    private String photo;//备用字段1
    private Integer photo_state;//备用字段2

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

    public long getUser_no() {
        return user_no;
    }

    public void setUser_no(long user_no) {
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

    public long getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(long certificate_type) {
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

    public Integer getPhoto_state() {
        return photo_state;
    }

    public void setPhoto_state(Integer photo_state) {
        this.photo_state = photo_state;
    }

}
