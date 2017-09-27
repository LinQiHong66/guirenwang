package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class HelpCenterEvent {
	private Long id;
	/** 帮助名称 */
    private String help_name;
    /** 帮助等级 */
    private Integer help_grade;
    /** 父类ID */
    private Long help_parent;
    /** 帮助详情*/
    private String help_remark;
    
    private String operation;

    public HelpCenterEvent(Long id, String help_name, Integer help_grade,
			Long help_parent, String help_remark, String operation) {
		this.id = id;
		this.help_name = help_name;
		this.help_grade = help_grade;
		this.help_parent = help_parent;
		this.help_remark = help_remark;
		this.operation = operation;
	}
	
	public Long getId() {
		return id;
	}

	public String getHelp_name() {
		return help_name;
	}

	public Integer getHelp_grade() {
		return help_grade;
	}

	public Long getHelp_parent() {
		return help_parent;
	}

	public String getHelp_remark() {
		return help_remark;
	}

	public String getOperation() {
		return operation;
	}
}
