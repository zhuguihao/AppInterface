package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.dto.GroupMenuDto;
import com.common.dto.RelationMenuDto;
import com.common.service.ComMenuService;
import com.gubang.config.UserInfoParam;
import com.gubang.entity.Menu;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/menu")
public class ComMenuController {
	@Autowired
	private ComMenuService menuService;

	/**
	 * 添加菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public ResultDTO addMenu(@UserInfoParam UserInfo userInfo, @RequestBody Menu params) {
		return menuService.addMenu(userInfo, params);
	}

	/**
	 * 修改菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editMenu", method = RequestMethod.POST)
	public ResultDTO editMenu(@UserInfoParam UserInfo userInfo, @RequestBody Menu params) {
		return menuService.editMenu(userInfo, params);
	}
	
	/**
	 * 角色关联菜单表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/relationMenu", method = RequestMethod.POST)
	public ResultDTO relationMenu(@UserInfoParam UserInfo userInfo, @RequestBody RelationMenuDto params) {
		return menuService.relationMenu(userInfo, params);
	}
	
	/**
	 * 查询菜单表及已关联表ID
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getGroupMenu", method = RequestMethod.POST)
	public ResultDTO getGroupMenu(@UserInfoParam UserInfo userInfo, @RequestBody GroupMenuDto params) {
		return menuService.getGroupMenu(userInfo, params);
	}
}
