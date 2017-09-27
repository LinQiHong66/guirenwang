package com.inesv.digiccy.dto;

public class SequenceDto {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 序列名称
	 */
	private String seq_name;

	/**
	 * 当前序列号
	 */
	private Integer current_val;

	/**
	 * 步长
	 */
	private Integer step;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeq_name() {
		return seq_name;
	}

	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}

	public Integer getCurrent_val() {
		return current_val;
	}

	public void setCurrent_val(Integer current_val) {
		this.current_val = current_val;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

}
