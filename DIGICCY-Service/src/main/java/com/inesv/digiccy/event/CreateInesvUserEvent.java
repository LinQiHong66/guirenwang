package com.inesv.digiccy.event;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class CreateInesvUserEvent {
    private long id;
//    private String username;//用户名
    private int user_no;  //用户编号
    private String password;//密码
//    private String region;//地区
//    private String real_name;//姓名
//    private String mail;//邮箱
    private String phone;//手机号码
    private Integer phone_state; //手机号码状态 0未绑定  1已绑定
//    private long certificate_type;//证件类型
//    private String certificate_num;//证件号码
    private String deal_pwd;//交易密码
    private Integer deal_pwdstate;
    private String validate_pwd;//验证密码
    private Integer validate_pwdstate; //验证密码状态 0未启用   1已启用
    private String alipay;//支付宝账号
    private Integer alipay_state; //支付宝账号状态 0未绑定  1已绑定
//    private long state;//状态
//    private String invite_num;//邀请码
//    private Date date;//操作时间
    private String photo;//备用字段1
    private Integer photo_state;//备用字段2
    private String operation;

    public long getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public Integer getPhoto_state() {
        return photo_state;
    }

    public String getPassword() {
        return password;
    }

    public String getOperation() {
        return operation;
    }

    public int getUser_no() {
        return user_no;
    }

    public String getDeal_pwd() {
        return deal_pwd;
    }

    public String getPhone() {
        return phone;
    }

    public String getAlipay() {
        return alipay;
    }

    public Integer getDeal_pwdstate() {
        return deal_pwdstate;
    }

    public Integer getPhone_state() {
        return phone_state;
    }

    public String getValidate_pwd() {
        return validate_pwd;
    }

    public Integer getValidate_pwdstate() {
        return validate_pwdstate;
    }

    public Integer getAlipay_state() {
        return alipay_state;
    }

    public CreateInesvUserEvent(long id, String password, String phone, Integer phone_state, String deal_pwd, Integer deal_pwdstate, String validate_pwd, Integer validate_pwdstate, String alipay, Integer alipay_state, String photo, Integer photo_state, String operation, int user_no) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.phone_state = phone_state;
        this.deal_pwd = deal_pwd;
        this.deal_pwdstate = deal_pwdstate;
        this.validate_pwd =validate_pwd;
        this.validate_pwdstate =validate_pwdstate;
        this.alipay = alipay;
        this.alipay_state = alipay_state;
        this.photo = photo;
        this.photo_state = photo_state;
        this.operation = operation;
        this.user_no = user_no;

    }
}
