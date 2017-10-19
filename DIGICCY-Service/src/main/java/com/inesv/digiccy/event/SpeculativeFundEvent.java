package com.inesv.digiccy.event;

import java.util.Date;

public class SpeculativeFundEvent {
	private int id;// 编号
	private int user_no;// 用户编号
	private int coin_no;// 货币类型
	private int deal_type;// 交易类型0：买 1：卖
	private double deal_price;// 交易价格
	private double optimum_price;// 最佳价格
	private double most_amount;// 最大交易量
	private double percent;// 比例
	private double deal_num;// 交易数量
	private double sum_price;// 总额
	private double poundage;// 手续费
	private Date date;// 时间
	private String attr1;// 备用字段1
	private String attr2;// 备用字段2
	private String operation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getCoin_no() {
		return coin_no;
	}

	public void setCoin_no(int coin_no) {
		this.coin_no = coin_no;
	}

	public int getDeal_type() {
		return deal_type;
	}

	public void setDeal_type(int deal_type) {
		this.deal_type = deal_type;
	}

	public double getDeal_price() {
		return deal_price;
	}

	public void setDeal_price(double deal_price) {
		this.deal_price = deal_price;
	}

	public double getOptimum_price() {
		return optimum_price;
	}

	public void setOptimum_price(double optimum_price) {
		this.optimum_price = optimum_price;
	}

	public double getMost_amount() {
		return most_amount;
	}

	public void setMost_amount(double most_amount) {
		this.most_amount = most_amount;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public double getDeal_num() {
		return deal_num;
	}

	public void setDeal_num(double deal_num) {
		this.deal_num = deal_num;
	}

	public double getSum_price() {
		return sum_price;
	}

	public void setSum_price(double sum_price) {
		this.sum_price = sum_price;
	}

	public double getPoundage() {
		return poundage;
	}

	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public SpeculativeFundEvent(int id, int user_no, int coin_no, int deal_type, double deal_price,
			double optimum_price, double most_amount, double percent, double deal_num, double sum_price,
			double poundage, Date date, String attr1, String attr2, String operation) {
		this.id = id;
		this.user_no = user_no;
		this.coin_no = coin_no;
		this.deal_type = deal_type;
		this.deal_price = deal_price;
		this.optimum_price = optimum_price;
		this.most_amount = most_amount;
		this.percent = percent;
		this.deal_num = deal_num;
		this.sum_price = sum_price;
		this.poundage = poundage;
		this.date = date;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.operation = operation;
	}

}
