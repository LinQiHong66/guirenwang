package com.inesv.digiccy.dto;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class InesvPhoneDto {
    /**编号 */
    private Integer id;
    /**用户编号 */
    private String user_no;
    /**手机号码 */
    private String phone;
    /**状态 */
    private Integer state;
    /**验证码 */
    private Integer code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
