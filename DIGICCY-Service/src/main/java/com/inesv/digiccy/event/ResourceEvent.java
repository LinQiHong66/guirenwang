package com.inesv.digiccy.event;

/**
 * Created by JimJim on 2016/11/10 0010.
 */
public class ResourceEvent {

    private Integer resourceId;

    private String type;

    private String value;

    private Integer parent;

    private String operation;

    private Integer common;

    public ResourceEvent(Integer resourceId, String type, String value, Integer parent, String operation, Integer common) {
        this.resourceId = resourceId;
        this.type = type;
        this.value = value;
        this.parent = parent;
        this.operation = operation;
        this.common = common;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public Integer getParent() {
        return parent;
    }

    public String getOperation() {
        return operation;
    }

    public Integer getCommon() {
        return common;
    }
}
