package com.integral.dto;


/**
 * 积分获取规则
 * @author fangzhenxing
 * time  2017年9月18日13:55:08
 */
public class IntegralRuleDto {
	
	/**
	 * 主键ID
	 */
	private  String id;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 单日上限
	 */
	private String number;
	
	/**
	 * 操作详情
	 */
	private String instruction;
	
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 操作标识符
	 */
	private String identifier;
	
	/**
	 * 条件
	 */
	private String conditions;
	
	/**
	 * 奖励分
	 */
	private String reward;
	
	/**
	 * 当前规则状态
	 */
	private String state;
	
	

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "IntegralRuleDto [id=" + id + ", type=" + type + ", number=" + number + ", instruction=" + instruction
				+ ", userId=" + userId + ", identifier=" + identifier + ", conditions=" + conditions + ", reward="
				+ reward + ", state=" + state + "]";
	}
	
	
	
}
