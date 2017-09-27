package com.inesv.digiccy.dto;

public class InesvUserAddressLevelDto {
	private Long user_id;
	
	private Integer level;
	
	private String address_code;
	
	private Boolean status;

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

	public String getAddress_code() {
		return address_code;
	}

	public void setAddress_code(String address_code) {
		this.address_code = address_code;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "InesvUserAddressLevelDto [user_id=" + user_id + ", level="
				+ level + ", address_code=" + address_code + ", status="
				+ status + "]";
	}
	
	
	
}
