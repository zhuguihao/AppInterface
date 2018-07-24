package com.common.dto;

import java.util.List;

public class GroupDto {
	private String id;
	private String groupCode;
	private String groupName;
	private String isDel;
	private List<GroupDto> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public List<GroupDto> getChildren() {
		return children;
	}

	public void setChildren(List<GroupDto> children) {
		this.children = children;
	}

}
