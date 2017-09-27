package com.inesv.digiccy.event;

/**
 * 修改用户-角色-权限
 * Created by JimJim on 2016/11/16 0016.
 */
public class AuthEvent {

    private Integer authId;
    private Integer userId;
    private String name;
    private String password;
    private Integer roleId;
    private String resId;
    private String operation;

    public AuthEvent(Integer authId, Integer userId, String name, String password, Integer roleId, String resId, String operation) {
        this.authId = authId;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.resId = resId;
        this.operation = operation;
    }

    public AuthEvent() {
    }

    public Integer getAuthId() {
        return authId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
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
}
