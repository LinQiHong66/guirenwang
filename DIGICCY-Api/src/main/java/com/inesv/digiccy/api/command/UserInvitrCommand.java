package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created by yc on 2016/12/20 0020.
 */
public class UserInvitrCommand {

    @TargetAggregateIdentifier
    /**编号*/
    private long id;
    /**用户编号*/
    private int user_no;
    /**邀请码*/
    private String invite_num;
    /**日期*/
    private Date date;
    /**操作类型*/
    private String operation;

    public UserInvitrCommand(long id, int user_no, String invite_num, Date date, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.invite_num = invite_num;
        this.date = date;
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public int getUser_no() {
        return user_no;
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
}
