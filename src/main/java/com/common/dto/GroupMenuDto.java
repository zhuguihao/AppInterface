package com.common.dto;

import com.gubang.util.CommonUtil;

public class GroupMenuDto {
	/**
	 * 选中的角色ID
	 */
	private String roleId;
	/**
	 * 菜单类型
	 */
	private String type;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(roleId) || CommonUtil.isEmpty(type);
	}
}
