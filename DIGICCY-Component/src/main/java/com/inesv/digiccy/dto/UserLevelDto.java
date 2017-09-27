package com.inesv.digiccy.dto;

public class UserLevelDto {
	
	private Long level_id;
	
	private Long user_id;
	
	private Integer level;
	
	private Boolean status;
	
	

	public UserLevelDto() {
		
	}

	public UserLevelDto( Long user_id, Integer level, Boolean status) {
		
		this.user_id = user_id;
		this.level = level;
		this.status = status;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
}
