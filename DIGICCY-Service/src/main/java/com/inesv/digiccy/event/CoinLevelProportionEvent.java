package com.inesv.digiccy.event;

import java.math.BigDecimal;

public class CoinLevelProportionEvent {
	
	private Long id;
	
	private Long coin_no;
	
	private BigDecimal level_one;
	
	private BigDecimal level_two;
	
	private BigDecimal level_three;
	
	private String operation;

	
	
	
	public CoinLevelProportionEvent(Long id, Long coin_no,
			BigDecimal level_one, BigDecimal level_two, BigDecimal level_three,
			String operation) {
		this.id = id;
		this.coin_no = coin_no;
		this.level_one = level_one;
		this.level_two = level_two;
		this.level_three = level_three;
		this.operation = operation;
	}

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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	
	
	
	
}
