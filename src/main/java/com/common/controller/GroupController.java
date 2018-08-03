package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.common.service.GroupService;
import com.gubang.config.UserInfoParam;
import com.gubang.entity.Group;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;

	/**
	 * 新增角色---添加当前角色子节点的数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addGroupChildren", method = RequestMethod.POST)
	public ResultDTO addGroupChildren(@UserInfoParam UserInfo userInfo, @RequestBody Group params) {
		return groupService.addGroupChildren(userInfo, params);
	}

	/**
	 * 修改角色 
	 * @return
	 */
	@RequestMapping(value = "/editGroup", method = RequestMethod.POST)
	public ResultDTO editGroup(@UserInfoParam UserInfo userInfo, @RequestBody Group params) {
		return groupService.editGroup(userInfo, params);
	}
}
