package com.gubang.entity;

import java.io.Serializable;
import java.util.Date;

public class Dict implements Serializable {
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
	/**
	 * 是否删除（N/Y）
	 */
	private String isDel;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;

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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}