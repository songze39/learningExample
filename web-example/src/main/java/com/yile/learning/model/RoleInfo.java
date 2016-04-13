package com.yile.learning.model;

import com.rabbitframework.jadb.annontations.Column;
import com.rabbitframework.jadb.annontations.Table;

@Table
public class RoleInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private int roleId;
	@Column
	private String roleName;
	@Column
	private String roleCode;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
