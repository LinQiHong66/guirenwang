package com.inesv.digiccy.event;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class RoleEvent {

    private Integer roleId;
    private String name;
    private String description;
    private String operation;

    public RoleEvent(Integer roleId, String name, String description, String operation) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.operation = operation;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOperation() {
        return operation;
    }
}

