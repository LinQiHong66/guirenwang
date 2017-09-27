package com.inesv.digiccy.enums;

/**
 * Created by SKINK on 2016/12/31.
 */
public enum RechargeState {
    //充值中
    RECHARGEING(0),
    //充值完成
    RECHARGED(1);

    private int value;

    RechargeState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
