package com.gubang.entity;

import java.io.Serializable;
import java.util.Date;

public class GroupUserCenter implements Serializable {
	private static final long serialVersionUID = 810402191034791994L;
	private String id;
	/**
	 * 用户角色组ID
	 */
	private String tSysGroupId;
	/**
	 * 用户ID
	 */
	private String tSysUserId;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String isDel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettSysGroupId() {
		return tSysGroupId;
	}

	public void settSysGroupId(String tSysGroupId) {
		this.tSysGroupId = tSysGroupId;
	}

	public String gettSysUserId() {
		return tSysUserId;
	}

	public void settSysUserId(String tSysUserId) {
		this.tSysUserId = tSysUserId;
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}