package com.integral.dto;


/**
 * 等级特权
 * @author fangzhenxing
 * time 2017年9月18日13:40:37 
 *  
 */
public class IntegralGradeDto {
	
	/**
	 * 主键ID
	 */
	private String id;
	
	/**
	 * 等级
	 */
	private String grade;
	
	/**
	 * 积分条件
	 */
	private String conditions;
	
	/**
	 * 快速提现率
	 */
	private String quicks;
	
	/**
	 * 极速提现率
	 */
	private String speed;
	
	/**
	 * 符号
	 */
	private String additional;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getQuicks() {
		return quicks;
	}

	public void setQuicks(String quicks) {
		this.quicks = quicks;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}


	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	@Override
	public String toString() {
		return "IntegralGradeDto [id=" + id + ", grade=" + grade + ", conditions=" + conditions + ", quicks=" + quicks
				+ ", speed=" + speed + ", additional=" + additional + "]";
	}
	
	
	
}
