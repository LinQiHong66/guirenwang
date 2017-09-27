package com.inesv.digiccy.common.payhistory.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class ShopWalletPayHistoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  
	 */
	private int id;
	/**
	 * 
	 */
	private int uid;
	/**
	 * 支付之前 钱包余额
	 */
	private BigDecimal before_money = new BigDecimal(0);
	/**
	 * 金额
	 */
	private BigDecimal money;
	/**
	 * 支付订单号
	 */
	private String order_number;
	/**
	 * 流水号：充值产生
	 */
	private String serial_number;
	/**
	 * 支付宝 流水号
	 */
	private String trade_no;
	/**
	 * 支付状态 ： 1 交易完成，3:进行中  6 交易关闭
	 */
	private int status;
	/**
	 * 支付失败原因：1 钱包被锁定 2 钱包余额不足 3 系统错误
	 */
	private int pay_failed_cause;
	/**
	 *  1 充值操作 2 支付操作
	 */
	private int type;
	/**
	 *  进账方式：  1 思埠钱包支付  2 支付宝支付 3 网银支付 4 快捷支付 10退款进账
	 */
	private int in_path;
	/**
	 * 付款银行
	 */
	private String pay_bank;
	/**
	 * 
	 */
	private String createtime;
	/**
	 * 支付标志,默认为1表示成功，0表示失败
	 */
	private int pay_flag;
	/**
	 * 交易对方名
	 */
	private String opposite_name;
	/**
	 * 交易对方id
	 */
	private int opposite_id;
	/**
	 * 0 Web端 1 android 2 ios 
	 */
	private int source;
	/**
	 * 用户商城订单code 
	 */
	private String code;
	/**
	 * 商品交易名称
	 */
	private String order_goods_name;
	/**
	 * 记录交易完成时间
	 */
	private String successtime;
	/**
	 * 返利冻结资金
	 */
	private BigDecimal rebate = new BigDecimal(0);
	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 0 :商城 1：快购
	 */
	private int platform;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public int getUid() {

		return uid;
	}

	public void setUid(int uid) {

		this.uid = uid;
	}

	public BigDecimal getBefore_money() {

		return before_money;
	}

	public void setBefore_money(BigDecimal before_money) {

		this.before_money = before_money;
	}

	public BigDecimal getMoney() {

		return money;
	}

	public void setMoney(BigDecimal money) {

		this.money = money;
	}

	public String getOrder_number() {

		return order_number;
	}

	public void setOrder_number(String order_number) {

		this.order_number = order_number;
	}

	public String getSerial_number() {

		return serial_number;
	}

	public void setSerial_number(String serial_number) {

		this.serial_number = serial_number;
	}

	public String getTrade_no() {

		return trade_no;
	}

	public void setTrade_no(String trade_no) {

		this.trade_no = trade_no;
	}

	public int getStatus() {

		return status;
	}

	public void setStatus(int status) {

		this.status = status;
	}

	public int getPay_failed_cause() {
		return pay_failed_cause;
	}

	public void setPay_failed_cause(int pay_failed_cause) {
		this.pay_failed_cause = pay_failed_cause;
	}

	public int getType() {

		return type;
	}

	public void setType(int type) {

		this.type = type;
	}

	public int getIn_path() {

		return in_path;
	}

	public void setIn_path(int in_path) {

		this.in_path = in_path;
	}

	public String getPay_bank() {

		return pay_bank;
	}

	public void setPay_bank(String pay_bank) {

		this.pay_bank = pay_bank;
	}

	public String getCreatetime() {

		return createtime;
	}

	public void setCreatetime(String createtime) {

		this.createtime = createtime;
	}

	public int getPay_flag() {

		return pay_flag;
	}

	public void setPay_flag(int pay_flag) {

		this.pay_flag = pay_flag;
	}

	public String getOpposite_name() {

		return opposite_name;
	}

	public void setOpposite_name(String opposite_name) {

		this.opposite_name = opposite_name;
	}

	public int getOpposite_id() {

		return opposite_id;
	}

	public void setOpposite_id(int opposite_id) {

		this.opposite_id = opposite_id;
	}

	public int getSource() {

		return source;
	}

	public void setSource(int source) {

		this.source = source;
	}

	public String getCode() {

		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public String getOrder_goods_name() {

		return order_goods_name;
	}

	public void setOrder_goods_name(String order_goods_name) {

		this.order_goods_name = order_goods_name;
	}

	public String getSuccesstime() {

		return successtime;
	}

	public void setSuccesstime(String successtime) {

		this.successtime = successtime;
	}

	public BigDecimal getRebate() {

		return rebate;
	}

	public void setRebate(BigDecimal rebate) {

		this.rebate = rebate;
	}

	public String getRemarks() {

		return remarks;
	}

	public void setRemarks(String remarks) {

		this.remarks = remarks;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Id", id);
		map.put("Uid", uid);
		map.put("Before_money", before_money);
		map.put("Money", money);
		map.put("Order_number", order_number);
		map.put("Serial_number", serial_number);
		map.put("Trade_no", trade_no);
		map.put("Status", status);
		map.put("Pay_failed_cause", pay_failed_cause);
		map.put("Type", type);
		map.put("In_path", in_path);
		map.put("Pay_bank", pay_bank);
		map.put("Createtime", createtime);
		map.put("Pay_flag", pay_flag);
		map.put("Opposite_name", opposite_name);
		map.put("Opposite_id", opposite_id);
		map.put("Source", source);
		map.put("Code", code);
		map.put("Order_goods_name", order_goods_name);
		map.put("Successtime", successtime);
		map.put("Rebate", rebate);
		map.put("Remarks", remarks);
		String value = "";
		try {
			value = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
