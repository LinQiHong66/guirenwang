package com.inesv.digiccy.dto;

import java.util.List;

/**
 * 众筹项目Dto
 * Created by JimJim on 2017/06/05 0017.
 */
public class HelpCenterDto {
	
	private Long id;
	/** 帮助名称 */
    private String help_name;
    /** 帮助等级 */
    private Integer help_grade;
    /** 父类ID */
    private Long help_parent;
    /** 帮助详情*/
    private String help_remark;
    /** 备用字段*/
    private List<HelpCenterDto> attr1;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHelp_name() {
		return help_name;
	}
	public void setHelp_name(String help_name) {
		this.help_name = help_name;
	}
	public Integer getHelp_grade() {
		return help_grade;
	}
	public void setHelp_grade(Integer help_grade) {
		this.help_grade = help_grade;
	}
	public Long getHelp_parent() {
		return help_parent;
	}
	public void setHelp_parent(Long help_parent) {
		this.help_parent = help_parent;
	}
	public String getHelp_remark() {
		return help_remark;
	}
	public void setHelp_remark(String help_remark) {
		this.help_remark = help_remark;
	}
	public List getAttr1() {
		return attr1;
	}
	public void setAttr1(List attr1) {
		this.attr1 = attr1;
	}
    
}
