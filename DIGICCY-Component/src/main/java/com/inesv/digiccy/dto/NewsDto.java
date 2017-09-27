package com.inesv.digiccy.dto;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class NewsDto {

	/** 编号 */
	private long id;
	/** 新闻标题 */
	private String news_title;
 
	/** 新闻内容 */
	private String news_content;
	
	/** 新闻作者 */
	private String news_author;
	
	private String date;
	
	/* 类型1为新闻2为公告
	 * **/
	private Integer type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_author() {
		return news_author;
	}

	public void setNews_author(String news_author) {
		this.news_author = news_author;
	}

 
 

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
 
}
