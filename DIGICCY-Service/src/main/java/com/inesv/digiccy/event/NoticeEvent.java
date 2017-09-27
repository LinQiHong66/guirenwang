package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by JimJim on 2016/12/13 0013.
 */
public class NoticeEvent {

    private long noticeId;
    /**公告类型*/
    private Integer notice_type;
    /**公告备注*/
    private String notice_bz;
    /**发布人*/
    private String person;
    /**时间*/
    private Date date;
    /**标题*/
    private String title;
    /**内容*/
    private String context;
    /**名称类型*/
    private String notice_nametype;

    private String operation;

    public NoticeEvent(long noticeId, Integer notice_type, String notice_bz, String person, Date date, String title, String context, String notice_nametype, String operation) {
        this.noticeId = noticeId;
        this.notice_type = notice_type;
        this.notice_bz = notice_bz;
        this.person = person;
        this.date = date;
        this.title = title;
        this.context = context;
        this.operation = operation;
        this.notice_nametype = notice_nametype;
    }

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
	public String getNotice_nametype() {
		return notice_nametype;
	}

	public void setNotice_nametype(String notice_nametype) {
		this.notice_nametype = notice_nametype;
	}
}
