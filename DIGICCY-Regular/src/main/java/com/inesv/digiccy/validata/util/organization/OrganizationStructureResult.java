package com.inesv.digiccy.validata.util.organization;

/**
 * 注册用户时机构验证的结果
 * 
 * @author Administrator
 *
 */
public class OrganizationStructureResult {

	/** 验证结果 */
	private boolean isSuccess;

	/** 验证错误描述 */
	private String errorMsg;

	/** 机构类型 0:机构 1:经纪人 2:普通客户 */
	private Integer org_type;

	/** 父机构编码 */
	private String org_parent_code;
	
	private String org_code;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getOrg_type() {
		return org_type;
	}

	public void setOrg_type(Integer org_type) {
		this.org_type = org_type;
	}

	public String getOrg_parent_code() {
		return org_parent_code;
	}

	public void setOrg_parent_code(String org_parent_code) {
		this.org_parent_code = org_parent_code;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

}
