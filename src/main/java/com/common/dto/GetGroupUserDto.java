package com.common.dto;

import com.gubang.util.CommonUtil;

public class GetGroupUserDto{
	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 昵称
	 */
	private String nickName;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(roleId);
	}
}
