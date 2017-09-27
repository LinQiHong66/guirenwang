package com.inesv.digiccy.dto.auth;
/**
 * 用户角色
 * @author Luxh
 */
public class UserRoleDto {
	private Integer id;
	/**用户id*/
	private Integer userId;
	/**角色id*/
	private Integer roleId;
	
	//getter setter
	//...

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
