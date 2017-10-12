package com.integral.dto;


/**
 * 任务完成记录表
 * @author fangzhenxing
 * time 2017年9月23日11:02:07
 */
public class CompletionDto{
		
	private String comId;

	private String userId;
	
	private String integralId;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIntegralId() {
		return integralId;
	}

	public void setIntegralId(String integralId) {
		this.integralId = integralId;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	@Override
	public String toString() {
		return "CompletionDto [comId=" + comId + ", userId=" + userId + ", integralId=" + integralId + "]";
	}
	
	
}
