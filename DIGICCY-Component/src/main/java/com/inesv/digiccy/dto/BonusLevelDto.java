package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BonusLevelDto {

	private Long id;

	private Long bonus_source;

	private Long bonus_coin;

	private Long bonus_user;

	private String bonus_user_name;

	private String bonus_user_code;

	private Long bonus_rel;

	private String bonus_rel_code;

	private BigDecimal level_scale;

	private int bonus_type;

	private BigDecimal bonus;

	private BigDecimal sum_bonus;

	private String remark;

	private Date date;
	/** 备用字段 */
	private String attr1;
	private String attr2;
	private String attr3;
	private String attr4;

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

	public String getBonus_user_name() {
		return bonus_user_name;
	}

	public void setBonus_user_name(String bonus_user_name) {
		this.bonus_user_name = bonus_user_name;
	}

	public String getBonus_user_code() {
		return bonus_user_code;
	}

	public void setBonus_user_code(String bonus_user_code) {
		this.bonus_user_code = bonus_user_code;
	}

	public Long getBonus_rel() {
		return bonus_rel;
	}

	public void setBonus_rel(Long bonus_rel) {
		this.bonus_rel = bonus_rel;
	}

	public String getBonus_rel_code() {
		return bonus_rel_code;
	}

	public void setBonus_rel_code(String bonus_rel_code) {
		this.bonus_rel_code = bonus_rel_code;
	}

	public BigDecimal getLevel_scale() {
		return level_scale;
	}

	public void setLevel_scale(BigDecimal level_scale) {
		this.level_scale = level_scale;
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

	public BigDecimal getSum_bonus() {
		return sum_bonus;
	}

	public void setSum_bonus(BigDecimal sum_bonus) {
		this.sum_bonus = sum_bonus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public String getAttr4() {
		return attr4;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

}
