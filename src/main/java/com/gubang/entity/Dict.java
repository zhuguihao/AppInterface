package com.gubang.entity;

import java.io.Serializable;

import com.gubang.util.EntityHelper;

public class Dict extends EntityHelper implements Serializable {
	private static final long serialVersionUID = -3380899128864794397L;
	private String id;
	/**
	 * 父级ID
	 */
	private String parentId;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 备注
	 */
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}