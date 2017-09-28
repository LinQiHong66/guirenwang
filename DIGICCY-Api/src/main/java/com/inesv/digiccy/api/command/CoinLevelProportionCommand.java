package com.inesv.digiccy.api.command;

import java.math.BigDecimal;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CoinLevelProportionCommand {
	
	@TargetAggregateIdentifier
	private Long id;
	
	private Long coin_no;
	
	private BigDecimal level_one;
	
	private BigDecimal level_two;
	
	private BigDecimal level_three;
	
	private BigDecimal level_four;
	
	private BigDecimal level_five;
	
	private Integer level_type;
	
	private Integer state;
	
	private String attr1;
	
	private String attr2;
	
	private String operation;

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

	public BigDecimal getLevel_four() {
		return level_four;
	}

	public void setLevel_four(BigDecimal level_four) {
		this.level_four = level_four;
	}

	public BigDecimal getLevel_five() {
		return level_five;
	}

	public void setLevel_five(BigDecimal level_five) {
		this.level_five = level_five;
	}

	public Integer getLevel_type() {
		return level_type;
	}

	public void setLevel_type(Integer level_type) {
		this.level_type = level_type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public CoinLevelProportionCommand(Long id, Long coin_no, BigDecimal level_one, BigDecimal level_two,
			BigDecimal level_three, BigDecimal level_four, BigDecimal level_five, Integer level_type, Integer state,
			String attr1, String attr2, String operation) {
		this.id = id;
		this.coin_no = coin_no;
		this.level_one = level_one;
		this.level_two = level_two;
		this.level_three = level_three;
		this.level_four = level_four;
		this.level_five = level_five;
		this.level_type = level_type;
		this.state = state;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.operation = operation;
	}
	
}
