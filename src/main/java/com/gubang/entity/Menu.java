package com.gubang.entity;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8304531234759615890L;

	private String id;
	/**
	 * 菜单URL
	 */
	private String menuUrl;
	/**
	 * 菜单图标
	 */
	private String menuIcon;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单跳转参数
	 */
	private String menuParams;
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

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuParams() {
		return menuParams;
	}

	public void setMenuParams(String menuParams) {
		this.menuParams = menuParams;
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