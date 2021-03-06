package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.common.service.DictService;
import com.common.service.GroupService;
import com.common.service.ComMenuService;
import com.common.service.UserService;
import com.gubang.config.PCUserInfoParam;
import com.gubang.entity.Dict;
import com.gubang.entity.Group;
import com.gubang.entity.Menu;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/serviceCenter")
public class ServiceCenter {
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;
	@Autowired
	private ComMenuService menuService;
	@Autowired
	private GroupService groupService;

	/**
	 * 获取数据字典
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDict", method = RequestMethod.POST)
	public ResultDTO getDict(@PCUserInfoParam UserInfo userInfo, @RequestBody Dict params) {
		return dictService.getDict(userInfo, params);
	}

	/**
	 * 修改数据字典
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editDict", method = RequestMethod.POST)
	public ResultDTO editDict(@PCUserInfoParam UserInfo userInfo, @RequestBody Dict params) {
		return dictService.editDict(userInfo, params);
	}

	/**
	 * 添加数据字典
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addDict", method = RequestMethod.POST)
	public ResultDTO addDict(@PCUserInfoParam UserInfo userInfo, @RequestBody Dict params) {
		return dictService.addDict(userInfo, params);
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public ResultDTO getUser(@PCUserInfoParam UserInfo userInfo, @RequestBody UserInfo params) {
		return userService.getUser(userInfo, params);
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public ResultDTO editUser(@PCUserInfoParam UserInfo userInfo, @RequestBody UserInfo params) {
		return userService.editUser(userInfo, params);
	}
	
	/**
	 * 获取菜单数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.POST)
	public ResultDTO getMenu(@PCUserInfoParam UserInfo userInfo, @RequestBody Menu params) {
		return menuService.getMenu(userInfo, params);
	}
	
	/**
	 * 角色获取当前拥有的菜单信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getWebMenu", method = RequestMethod.POST)
	public ResultDTO getWebMenu(@PCUserInfoParam UserInfo userInfo, @RequestBody Menu params) {
		return menuService.getWebMenu(userInfo, params);
	}
	
	/**
	 * 获取用户角色数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getGroup", method = RequestMethod.POST)
	public ResultDTO getGroup(@PCUserInfoParam UserInfo userInfo, @RequestBody Group params) {
		return groupService.getGroup(userInfo, params);
	}
}
