package com.inesv.digiccy.enums;

/**
 * Created by SKINK on 2017/1/1.
 */
public enum SqlOperate {
    INSERT("insert"),DELETE("delete"),UPDATE("update"),SELECT("select");

    private String value;

    SqlOperate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
