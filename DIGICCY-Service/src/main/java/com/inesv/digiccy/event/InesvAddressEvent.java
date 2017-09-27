package com.inesv.digiccy.event;

public class InesvAddressEvent {
	
	private Long id;
	
	private String address;
	
	private int address_status;
	
	private String address_name;
	
	private String address_code;
	
	private int address_level;
	
	private String operation;
	
	
	
	public InesvAddressEvent(Long id,String address, int address_status,
			String address_name, String address_code, int address_level,
			String operation) {
		super();
		this.address = address;
		this.address_status = address_status;
		this.address_name = address_name;
		this.address_code = address_code;
		this.address_level = address_level;
		this.operation = operation;
	}

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
	
}
