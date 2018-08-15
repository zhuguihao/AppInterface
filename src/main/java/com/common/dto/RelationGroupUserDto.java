package com.common.dto;

import com.alibaba.fastjson.JSONArray;
import com.gubang.util.CommonUtil;
import com.gubang.util.EntityHelper;

public class RelationGroupUserDto extends EntityHelper {
	private String id;
	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 用户IDs
	 */
	private JSONArray ids;

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
	
	public boolean inValid() {
		return CommonUtil.isEmpty(roleId);
	}
}
