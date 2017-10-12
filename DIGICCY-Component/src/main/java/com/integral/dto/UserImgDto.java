package com.integral.dto;

/**
 * 上传图片验证
 * 
 * @author Administrator
 *
 */
public class UserImgDto {

	// 主键ID
	private String id;

	// 身份证正面地址
	private String uri_front;

	// 身份证反面地址
	private String uri_reverse;

	// 用户ID
	private String user_id;

	// 用户名称
	private String user_name;

	// 创建时间
	private String crate_time;

	// 审批状态
	private String state;

	/**
	 * 拒绝原因
	 */
	private String refuse_reason;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri_front() {
		return uri_front;
	}

	public void setUri_front(String uri_front) {
		this.uri_front = uri_front;
	}

	public String getUri_reverse() {
		return uri_reverse;
	}

	public void setUri_reverse(String uri_reverse) {
		this.uri_reverse = uri_reverse;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCrate_time() {
		return crate_time;
	}

	public void setCrate_time(String crate_time) {
		this.crate_time = crate_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getRefuse_reason() {
		return refuse_reason;
	}

	public void setRefuse_reason(String refuse_reason) {
		this.refuse_reason = refuse_reason;
	}

	@Override
	public String toString() {
		return "UserImgDto [id=" + id + ", uri_front=" + uri_front + ", uri_reverse=" + uri_reverse + ", user_id="
				+ user_id + ", user_name=" + user_name + ", crate_time=" + crate_time + ", state=" + state
				+ ", refuse_reason=" + refuse_reason + "]";
	}
	 
	
}
