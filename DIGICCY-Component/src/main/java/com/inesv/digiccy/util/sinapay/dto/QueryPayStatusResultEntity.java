package com.inesv.digiccy.util.sinapay.dto;

public class QueryPayStatusResultEntity {

	//支付是否成功
	private boolean success;
	
	//订单号
	private String order_id;

	//新浪支付请求结果字符串
	private String resultString;
	
	//请求解释
	private String desc;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "QueryPayStatusResultEntity [success=" + success + ", order_id=" + order_id + ", resultString="
				+ resultString + ", desc=" + desc + "]";
	}

}
