package com.common.dto;

import com.alibaba.fastjson.JSONArray;
import com.gubang.util.CommonUtil;
import com.gubang.util.EntityHelper;

public class RelationMenuDto extends EntityHelper {
	/**
	 * ID
	 */
	private String id;
	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 菜单IDs
	 */
	private JSONArray ids;
	/**
	 * 菜单类型
	 */
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public JSONArray getIds() {
		return ids;
	}

	public void setIds(JSONArray ids) {
		this.ids = ids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean inValid() {
		return CommonUtil.isEmpty(roleId);
	}
}
