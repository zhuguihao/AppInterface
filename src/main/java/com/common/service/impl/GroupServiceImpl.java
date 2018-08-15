package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.RelationGroupUserDto;
import com.common.dto.RelationMenuDto;
import com.common.service.GroupService;
import com.gubang.entity.Group;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.GroupMapper;
import com.gubang.mapper.MenuCenterMapper;
import com.gubang.mapper.UserGroupMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private MenuCenterMapper menuCenterMapper;
	@Autowired
	private UserGroupMapper userGroupMapper;

	@Override
	public ResultDTO getGroup(UserInfo userInfo, Group params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			/**
			 * 1.查询所有角色表信息 2.查询当前角色ID 3.格式化当前角色ID下的角色信息
			 */
			List<Group> getGroup = groupMapper.getGroup(params);
			List<Group> treeGroup = CommonUtil.formatTree(getGroup, userInfo.getGroupId());
			return result.setSuccess(treeGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO editGroup(UserInfo userInfo, Group params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			groupMapper.editGroup(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO addGroupChildren(UserInfo userInfo, Group params) {
		ResultDTO result = new ResultDTO();
		try {
			if(null == userInfo){
				return result.setNotLogin();
			}
			params.setId(CommonUtil.getUUid());
			params.setParentId(params.getParentId());
			 params.setCreateBy(userInfo.getId());
			params.setCreateDate(new Date());
			 params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			groupMapper.addGroup(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
	
	@Override
	@Transactional
	public ResultDTO relationMenu(UserInfo userInfo, RelationMenuDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			
			if(params.inValid()){
				return result.setParameterInvalid();
			}
			/**
			 * 1.删除所有角色ID相关的菜单关联 2.按照前端传的重新绑定菜单关联
			 */
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			menuCenterMapper.deleteRoleMenu(params);
			if (params.getIds().size() > 0) {
				params.setCreateBy(userInfo.getId());
				params.setCreateDate(new Date());
				menuCenterMapper.insertRoleMenu(params);
			}
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO relationUser(UserInfo userInfo, RelationGroupUserDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			
			if(params.inValid()){
				return result.setParameterInvalid();
			}
			/**
			 *	1.按照前端传的重新绑定菜单关联
			 */
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			userGroupMapper.deleteRoleUser(params);
			if (params.getIds().size() > 0) {
				params.setCreateBy(userInfo.getId());
				params.setCreateDate(new Date());
				userGroupMapper.insertRoleUser(params);
			}
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

}