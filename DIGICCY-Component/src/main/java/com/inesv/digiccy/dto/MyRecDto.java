package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by yc on 2016/12/9 0009.
 * 我的推荐
 */
public class MyRecDto {

    /**编号*/
    private long id;
    /**用户编号*/
    private int user_no;
    /**下家用户名*/
    private int rec_user;
    /**下家类型*/
    private int rec_type;
    /**是否认证*/
    private int auth;
    /**操作日期*/
    private Date date;
    /**备用字段1*/
    private String attr1;
    /**备用字段2*/
    private String attr2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getRec_user() {
        return rec_user;
    }

    public void setRec_user(int rec_user) {
        this.rec_user = rec_user;
    }

    public int getRec_type() {
        return rec_type;
    }

    public void setRec_type(int rec_type) {
        this.rec_type = rec_type;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
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
