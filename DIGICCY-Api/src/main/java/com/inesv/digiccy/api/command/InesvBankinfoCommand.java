package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */

public class InesvBankinfoCommand {

    @TargetAggregateIdentifier
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

    public InesvBankinfoCommand(long id, Integer user_no, String remark_name, String bank, String province, String city, String branch, String name, String bank_num, Date date, int state,String operation) {
        this.id = id;
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
        this.operation = operation;
    }

    public InesvBankinfoCommand(Integer user_no,String remark_name, String bank, String province, String city, String branch, String name, String bank_num,Date date,String operation) {
        this.user_no = user_no;
        this.remark_name = remark_name;
        this.bank = bank;
        this.province = province;
        this.city = city;
        this.branch = branch;
        this.name = name;
        this.bank_num = bank_num;
        this.date = date;
        this.operation = operation;
    }

    public InesvBankinfoCommand(long id,Integer user_no,String remark_name, String bank, String province, String city, String branch, String name, String bank_num,Date date,String operation) {
        this.id = id;
        this.user_no = user_no;
        this.remark_name = remark_name;
        this.bank = bank;
        this.province = province;
        this.city = city;
        this.branch = branch;
        this.name = name;
        this.bank_num = bank_num;
        this.date = date;
        this.operation = operation;
    }


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

	public void setId(long id) {
		this.id = id;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}

	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
    
}
