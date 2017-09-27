package com.inesv.digiccy.event;

/**
 * Created by JimJim on 2016/12/9 0009.
 */
public class UserEvent {

    /**编号*/
    private long id;
    /**用户名*/
    private String username;
    /**用户编号*/
    private int user_no;
    /**密码*/
    private String password;
    /**姓名*/
    private String real_name;
    /**邮箱*/
    private String mail;
    /**手机号码*/
    private String phone;
    /**证件号码*/
    private String certificate_num;
    /**交易密码*/
    private String deal_pwd;
    /**支付宝账号*/
    private String alipay;
    /**状态*/
    private int state;

    private String operation;

    public UserEvent(long id, String username, int user_no, String password, String real_name, String mail, String phone,
                       String certificate_num, String deal_pwd, String alipay, int state, String operation) {
        this.id = id;
        this.username = username;
        this.user_no = user_no;
        this.password = password;
        this.real_name = real_name;
        this.mail = mail;
        this.phone = phone;
        this.certificate_num = certificate_num;
        this.deal_pwd = deal_pwd;
        this.alipay = alipay;
        this.state = state;
        this.operation = operation;
    }
    
    /*
     * 测试
     */
    public UserEvent(int user_no,String operation){
    	this.user_no=user_no;
    	this.operation=operation;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getUser_no() {
        return user_no;
    }

    public String getPassword() {
        return password;
    }

    public String getReal_name() {
        return real_name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getCertificate_num() {
        return certificate_num;
    }

    public String getDeal_pwd() {
        return deal_pwd;
    }

    public String getAlipay() {
        return alipay;
    }

    public int getState() {
        return state;
    }

    public String getOperation() {
        return operation;
    }
}
