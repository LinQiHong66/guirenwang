/**
 * 
 */
package com.inesv.digiccy.common;


/**
 * @author houzhijia
 * @2014-9-10
 * @上午11:36:15
 */
public enum Code {
	
	success("0","ok"), 
	error("-1", "system error"),
	token_invalid("1","token invalid");
	
	/**
	 * @param code
	 * @param msg
	 */
	private Code ( String code ,String msg ) {
	
		this.code = code;
		this.msg = msg;
	}
	
	
	private String code;
	private String msg;
	
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getMsg(){
		return msg;
	}
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	
}
