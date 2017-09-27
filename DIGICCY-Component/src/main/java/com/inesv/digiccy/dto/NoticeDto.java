package com.inesv.digiccy.dto;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

import java.util.Date;

/**
 * 公告
 */
public class NoticeDto {
	private long id;
	/** 公告类型 */
	private Integer notice_type;
	/** 公告备注 */
	private String notice_bz;
	/** 发布人 */
	private String person;
	/** 时间 */
	private Date date;
	/** 标题 */
	private String title;
	/** 内容 */
	private String context;
	/** 名称类型 */
	private String notice_nametype;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNotice_type() {
		return notice_type;
	}

	public void setNotice_type(Integer notice_type) {
		this.notice_type = notice_type;
	}

	public String getNotice_bz() {
		return notice_bz;
	}

	public void setNotice_bz(String notice_bz) {
		this.notice_bz = notice_bz;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getNotice_nametype() {
		return notice_nametype;
	}

	public void setNotice_nametype(String notice_nametype) {
		this.notice_nametype = notice_nametype;
	}

}
