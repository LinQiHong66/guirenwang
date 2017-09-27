package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class LoginLogDto {

    /* 编号 */
    private long id;
    /* 用户编号 */
    private Integer user_no;
    /* 登录类型 */
    private Integer opt_type;
    /* 备注 */
    private String opt_remark;
    /* ip */
    private String opt_ip;
    /* 地址 */
    private String opt_address;
    /* 状态 */
    private Integer state;
    /* 日期 */
    private Date date;
    /* 备用字段 */
    private String attr1;
    /* 备用字段 */
    private String attr2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public Integer getOpt_type() {
        return opt_type;
    }

    public void setOpt_type(Integer opt_type) {
        this.opt_type = opt_type;
    }

    public String getOpt_remark() {
        return opt_remark;
    }

    public void setOpt_remark(String opt_remark) {
        this.opt_remark = opt_remark;
    }

    public String getOpt_ip() {
        return opt_ip;
    }

    public void setOpt_ip(String opt_ip) {
        this.opt_ip = opt_ip;
    }

    public String getOpt_address() {
        return opt_address;
    }

    public void setOpt_address(String opt_address) {
        this.opt_address = opt_address;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public LoginLogDto(Integer user_no, Integer opt_type, String opt_remark, String opt_ip, String opt_address, Integer state, Date date) {
        this.user_no = user_no;
        this.opt_type = opt_type;
        this.opt_remark = opt_remark;
        this.opt_ip = opt_ip;
        this.opt_address = opt_address;
        this.state = state;
        this.date = date;
    }

    public LoginLogDto(){}
}
