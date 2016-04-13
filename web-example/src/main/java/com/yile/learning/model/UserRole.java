package com.yile.learning.model;

import com.rabbitframework.jadb.annontations.Column;
import com.rabbitframework.jadb.annontations.Table;

@Table
public class UserRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private int id;
	@Column
	private int userId;
	@Column
	private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
