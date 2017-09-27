package com.inesv.digiccy.dto;

import java.math.BigDecimal;

public class BonusLevelDto {
	
	private Long id;
	
	private Long bonus_source;
	
	private Long bonus_coin;
	
	private Long bonus_user;
	
	private int bonus_type;
	
	private BigDecimal bonus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBonus_source() {
		return bonus_source;
	}

	public void setBonus_source(Long bonus_source) {
		this.bonus_source = bonus_source;
	}

	public Long getBonus_coin() {
		return bonus_coin;
	}

	public void setBonus_coin(Long bonus_coin) {
		this.bonus_coin = bonus_coin;
	}

	public Long getBonus_user() {
		return bonus_user;
	}

	public void setBonus_user(Long bonus_user) {
		this.bonus_user = bonus_user;
	}

	public int getBonus_type() {
		return bonus_type;
	}

	public void setBonus_type(int bonus_type) {
		this.bonus_type = bonus_type;
	}

	
	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String toString() {
		return "BonusLevelDto [id=" + id + ", bonus_source=" + bonus_source
				+ ", bonus_coin=" + bonus_coin + ", bonus_user=" + bonus_user
				+ ", bonus_type=" + bonus_type + ", bonus=" + bonus + "]";
	}

	
	
	
}
