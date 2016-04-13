package com.yile.learning.model;

import java.io.Serializable;

import com.rabbitframework.jadb.annontations.Column;
import com.rabbitframework.jadb.annontations.Table;

@Table
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private int userId;
	@Column
	private String userName;
	@Column
	private String userPwd;
	@Column
	private String token;
	@Column
	private String authorizeCode;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthorizeCode() {
		return authorizeCode;
	}

	public void setAuthorizeCode(String authorizeCode) {
		this.authorizeCode = authorizeCode;
	}

}
