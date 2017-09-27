package com.inesv.digiccy.event;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class InesvPhoneEvent {
    private Integer id;
    /**用户编号*/
    private String user_no;
    /**手机号码*/
    private String phone;
    /**状态*/
    private Integer state;
    /**验证码*/
    private Integer code;
    /** 操作类型*/
    private String operation;

    public InesvPhoneEvent(Integer id, String user_no, String phone, Integer state, Integer code, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.phone = phone;
        this.state = state;
        this.code = code;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public String getUser_no() {
        return user_no;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getState() {
        return state;
    }

    public Integer getCode() {
        return code;
    }

    public String getOperation() {
        return operation;
    }
}
