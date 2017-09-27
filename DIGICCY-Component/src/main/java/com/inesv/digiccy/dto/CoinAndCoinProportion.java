package com.inesv.digiccy.dto;

import java.math.BigDecimal;

public class CoinAndCoinProportion {
	
	private Long id;
	
	private Integer coin_no;
	
	private String coin_name;
	
	private BigDecimal level_one;
	
	private BigDecimal level_two;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCoin_no() {
		return coin_no;
	}

	public void setCoin_no(Integer coin_no) {
		this.coin_no = coin_no;
	}

	public String getCoin_name() {
		return coin_name;
	}

	public void setCoin_name(String coin_name) {
		this.coin_name = coin_name;
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

	@Override
	public String toString() {
		return "CoinAndCoinProportion [id=" + id + ", coin_no=" + coin_no
				+ ", coin_name=" + coin_name + ", level_one=" + level_one
				+ ", level_two=" + level_two + "]";
	}
	
	
}
