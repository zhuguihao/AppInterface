package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.dto.GetGroupUserDto;
import com.common.dto.LoginDto;
import com.common.service.UserService;
import com.gubang.config.UserInfoParam;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/login")
public class LoginModuleController {
	@Autowired
	private UserService userService;

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultDTO login(@RequestBody LoginDto params) {
		return userService.login(params);
	}
	
	/**
	 * 获取角色绑定的用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getGroupUser", method = RequestMethod.POST)
	public ResultDTO getGroupUser(@UserInfoParam UserInfo userInfo, @RequestBody GetGroupUserDto params) {
		return userService.getGroupUser(userInfo, params);
	}

}
