package com.gubang.entity;

import java.io.Serializable;
import java.util.Date;

public class MenuCenter implements Serializable {
	private static final long serialVersionUID = 3979663464232523342L;
	private String id;
	/**
	 * 用户角色组ID
	 */
	private String tSysGroupId;
	/**
	 * 菜单ID
	 */
	private String tSysMenuId;
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

	public String gettSysMenuId() {
		return tSysMenuId;
	}

	public void settSysMenuId(String tSysMenuId) {
		this.tSysMenuId = tSysMenuId;
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