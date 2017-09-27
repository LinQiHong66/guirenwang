package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created by yc on 2016/12/21 0021.
 */
public class MyrecCommand {

    @TargetAggregateIdentifier
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
    /**操作类型*/
    private String operation;

    public MyrecCommand(long id, int user_no, int rec_user, int rec_type, int auth, Date date, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.rec_user = rec_user;
        this.rec_type = rec_type;
        this.auth = auth;
        this.date = date;
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public int getUser_no() {
        return user_no;
    }

    public int getRec_user() {
        return rec_user;
    }

    public int getRec_type() {
        return rec_type;
    }

    public int getAuth() {
        return auth;
    }

    public Date getDate() {
        return date;
    }

    public String getAttr1() {
        return attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public String getOperation() {
        return operation;
    }
}
