package com.inesv.digiccy.dto;

/**
 * 资金异常Dto
 * 
 * @author lumia
 *
 */
public class SpeculativeFundsDto {
	private int id;
	private double inProperty;
	private double outProperty;
	private double totalProperty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getInProperty() {
		return inProperty;
	}

	public void setInProperty(double inProperty) {
		this.inProperty = inProperty;
	}

	public double getOutProperty() {
		return outProperty;
	}

	public void setOutProperty(double outProperty) {
		this.outProperty = outProperty;
	}

	public double getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(double totalProperty) {
		this.totalProperty = totalProperty;
	}

	public SpeculativeFundsDto(int id, double inProperty, double outProperty, double totalProperty) {
		this.id = id;
		this.inProperty = inProperty;
		this.outProperty = outProperty;
		this.totalProperty = totalProperty;
	}

}
