package com.inesv.digiccy.api.command;


public class UserLevelCommand {
	private Long level_id;
	
	private Long user_id;
	
	private Integer level;
	
	private Boolean status;
	
	private String operation;
	
	
	

	public UserLevelCommand(Long level_id, Long user_id, Integer level,
			Boolean status,String opration) {
		super();
		this.level_id = level_id;
		this.user_id = user_id;
		this.level = level;
		this.status = status;
		this.operation = opration;
	}

	public Long getLevel_id() {
		return level_id;
	}

	public void setLevel_id(Long level_id) {
		this.level_id = level_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
	
}
