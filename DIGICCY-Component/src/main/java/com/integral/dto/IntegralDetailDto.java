package com.integral.dto;


/**
 * 积分明细
 * @author fangzhenxing
 * time 2017年9月18日13:37:47
 */
public class IntegralDetailDto {
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 积分数量
	 */
	private String number;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 唯一标识
	 */
	private String identifier;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return "IntegralDetailDto [id=" + id + ", createTime=" + createTime + ", type=" + type + ", number=" + number
				+ ", userId=" + userId + ", userName=" + userName + ", identifier=" + identifier + "]";
	}
	
	
}
