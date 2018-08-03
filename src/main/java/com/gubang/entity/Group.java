package com.gubang.entity;

import java.io.Serializable;
import java.util.List;
import com.gubang.util.EntityHelper;

public class Group extends EntityHelper implements Serializable {
	private static final long serialVersionUID = -5879270185746026011L;
	private String id;
	/**
	 * 公司ID
	 */
	private String tSysCompanyId;
	/**
	 * 父级ID
	 */
	private String parentId;
	/**
	 * 角色代码
	 */
	private String groupCode;
	/**
	 * 角色名称
	 */
	private String groupName;
	/**
	 * 子节点
	 */
	private List<Group> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettSysCompanyId() {
		return tSysCompanyId;
	}

	public void settSysCompanyId(String tSysCompanyId) {
		this.tSysCompanyId = tSysCompanyId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Group> getChildren() {
		return children;
	}

	public void setChildren(List<Group> children) {
		this.children = children;
	}

}