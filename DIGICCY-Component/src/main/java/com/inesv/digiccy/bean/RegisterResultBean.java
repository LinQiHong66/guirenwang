package com.inesv.digiccy.bean;

/**
 * 注册返回结果
 * 
 * @author lumia
 *
 */
public class RegisterResultBean extends BaseResponse {
	private long userNo;
	private int count;

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
