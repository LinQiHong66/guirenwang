package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class AssessDto {

	/** 编号 */
	private long id;
	/** 用户编号 */
	private String username;
	/** 货币类型 */
	private Integer coin_no;
	/** 评价星级 */
	private String assess;
	/** 评价星级 */
	private Integer assess_level;
	/** 技术评分 */
	private Integer skill_assess;
	/** 应用评分 */
	private Integer apply_assess;
	/** 前景评分 */
	private Integer vista_assess;
	/** 时间 */
	private Date date;
	/** 备用字段1 */
	private String attr1;
	/** 备用字段2 */
	private String attr2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAssess_level() {
		return assess_level;
	}

	public void setAssess_level(Integer assess_level) {
		this.assess_level = assess_level;
	}

	public Integer getSkill_assess() {
		return skill_assess;
	}

	public void setSkill_assess(Integer skill_assess) {
		this.skill_assess = skill_assess;
	}

	public Integer getApply_assess() {
		return apply_assess;
	}

	public void setApply_assess(Integer apply_assess) {
		this.apply_assess = apply_assess;
	}

	public Integer getVista_assess() {
		return vista_assess;
	}

	public void setVista_assess(Integer vista_assess) {
		this.vista_assess = vista_assess;
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

	public Integer getCoin_no() {
		return coin_no;
	}

	public void setCoin_no(Integer coin_no) {
		this.coin_no = coin_no;
	}

	public String getAssess() {
		return assess;
	}

	public void setAssess(String assess) {
		this.assess = assess;
	}
}
