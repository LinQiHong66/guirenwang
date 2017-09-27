package com.inesv.digiccy.dto;

public class InesvAddressDto {
	
	private Long address_id;
	
	private String address;
	
	private int address_status;
	
	private String address_name;
	
	private String address_code;
	
	private int address_level;

	
	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAddress_status() {
		return address_status;
	}

	public void setAddress_status(int address_status) {
		this.address_status = address_status;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public String getAddress_code() {
		return address_code;
	}

	public void setAddress_code(String address_code) {
		this.address_code = address_code;
	}

	public int getAddress_level() {
		return address_level;
	}

	public void setAddress_level(int address_level) {
		this.address_level = address_level;
	}
	
	
	
}
