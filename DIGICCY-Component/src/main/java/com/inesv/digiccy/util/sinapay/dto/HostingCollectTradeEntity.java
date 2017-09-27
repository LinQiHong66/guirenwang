package com.inesv.digiccy.util.sinapay.dto;

/**
 * 
 * @author Administrator
 *
 */
public class HostingCollectTradeEntity {
	
	//订单ID
	private String order_id;
	
	//备注(必填)
	private String summary;
	
	//支付者新浪帐号
	private String payer_id;
	
	//支付者IP
	private String payer_ip;
	
	//支付金额,单位元
	private String amount;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(String payer_id) {
		this.payer_id = payer_id;
	}

	public String getPayer_ip() {
		return payer_ip;
	}

	public void setPayer_ip(String payer_ip) {
		this.payer_ip = payer_ip;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	

}
