package com.respon;

/**
 * 返回工具類
 * @author fangzhenxing
 * time 2017年9月18日18:24:49
 */ 
public class R {
	
	//登录唯一符
	public final static String  LOGIN_CODE="denglu";
	
	private int code=0;
	
	private String msg;
	
	private Object data;

	public int getCode() {
		return code;
	}
	
	/**
	 * 設置請求返回狀態碼
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	
	/**
	 * 返回提示信息
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}
	
	/**
	 * 設置返回数据
	 * @param date
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	
	/**
	 * 判断字符串是否为空或者空字符串
	 * @param st
	 * @return
	 */
	public  static boolean isNull(String st){
		if(st!=null && !st.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
}
