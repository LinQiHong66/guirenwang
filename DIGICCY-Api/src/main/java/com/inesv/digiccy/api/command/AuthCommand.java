package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by JimJim on 2016/11/16 0016.
 */
public class AuthCommand {

    @TargetAggregateIdentifier
    private Integer authId;
    private Integer userId;
    private String name;
    private String password;
    private Integer roleId;
    private String resId;
    private String operation;

    public AuthCommand(Integer authId, Integer userId, String name, String password, Integer roleId, String resId, String operation) {
        this.authId = authId;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.resId = resId;
        this.operation = operation;
    }

    public Integer getAuthId() {
        return authId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getResId() {
        return resId;
    }

    public String getOperation() {
        return operation;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
