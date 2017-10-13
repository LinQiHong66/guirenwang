package com.integral.dto;

/**
 * 完成任务获取积分状态实体
 * @author fangzhenxing
 * time 2017年9月18日13:38:25
 */
public class IntegralCompleteDto {
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 积分奖励类型
	 */
	private String type;
	
	/**
	 * 积分奖励数量
	 */
	private String num;
	
	/**
	 * 积分奖励状态
	 */
	private String state;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	
	/**
	 * 拒绝原因
	 */
	private String Refuse_reason;
	
	/**
	 * 正面
	 */
	private String z_name;
	
	/**
	 * 反面
	 */
	private String f_name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getRefuse_reason() {
		return Refuse_reason;
	}

	public void setRefuse_reason(String refuse_reason) {
		Refuse_reason = refuse_reason;
	}

	public String getZ_name() {
		return z_name;
	}

	public void setZ_name(String z_name) {
		this.z_name = z_name;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	@Override
	public String toString() {
		return "IntegralCompleteDto [id=" + id + ", type=" + type + ", num=" + num + ", state=" + state + ", userId="
				+ userId + ", Refuse_reason=" + Refuse_reason + ", z_name=" + z_name + ", f_name=" + f_name + "]";
	}

	
	
	
}
