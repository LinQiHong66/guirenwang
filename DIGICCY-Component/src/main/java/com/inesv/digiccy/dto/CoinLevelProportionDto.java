package com.inesv.digiccy.dto;

import java.math.BigDecimal;

public class CoinLevelProportionDto {
	
	private Long id;
	
	private Long coin_no;
	
	private BigDecimal level_one;
	
	private BigDecimal level_two;
	
	private BigDecimal level_three;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCoin_no() {
		return coin_no;
	}

	public void setCoin_no(Long coin_no) {
		this.coin_no = coin_no;
	}

	public BigDecimal getLevel_one() {
		return level_one;
	}

	public void setLevel_one(BigDecimal level_one) {
		this.level_one = level_one;
	}

	public BigDecimal getLevel_two() {
		return level_two;
	}

	public void setLevel_two(BigDecimal level_two) {
		this.level_two = level_two;
	}

	public BigDecimal getLevel_three() {
		return level_three;
	}

	public void setLevel_three(BigDecimal level_three) {
		this.level_three = level_three;
	}

	@Override
	public String toString() {
		return "CoinLevelProportionDto [id=" + id + ", coin_no=" + coin_no
				+ ", level_one=" + level_one + ", level_two=" + level_two
				+ ", level_three=" + level_three + "]";
	}
	
	
	
	
	
}
