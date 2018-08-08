package com.gubang.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Menu implements Serializable {
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
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 是否为标题
	 */
	private String isTitle;
	/**
	 * 父级ID
	 */
	private String parentId;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String isDel;
	private List<Menu> children;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsTitle() {
		return isTitle;
	}

	public void setIsTitle(String isTitle) {
		this.isTitle = isTitle;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

}