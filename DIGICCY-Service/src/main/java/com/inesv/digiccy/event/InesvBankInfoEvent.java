package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class InesvBankInfoEvent {
    private long id;
    /**用户编号*/
    private Integer user_no;
    /**备注名称*/
    private String remark_name;
    /**开户银行*/
    private String bank;
    /**开户省份*/
    private String province;
    /**开户城市*/
    private String city;
    /**开户支行*/
    private String branch;
    /**开户姓名*/
    private String name;
    /**开户卡号*/
    private String bank_num;
    /**添加时间*/
    private Date date;
    /**操作类型 0：失败  1成功*/
    private int state;
    private String operation;

    public long getId() {
        return id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public String getRemark_name() {
        return remark_name;
    }

    public String getBank() {
        return bank;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getBranch() {
        return branch;
    }

    public String getName() {
        return name;
    }

    public String getBank_num() {
        return bank_num;
    }

    public Date getDate() {
        return date;
    }

    public int getState() {
        return state;
    }

    public String getOperation() {
        return operation;
    }

    public InesvBankInfoEvent(int user_no, String remark_name, String bank, String province, String city, String branch, String name, String bank_num, Date date, int state,long id,String operation) {
        this.user_no = user_no;
        this.remark_name = remark_name;
        this.bank = bank;
        this.province = province;
        this.city = city;
        this.branch = branch;
        this.name = name;
        this.bank_num = bank_num;
        this.date = date;
        this.state = state;
        this.id = id;
        this.operation = operation;
    }
}
