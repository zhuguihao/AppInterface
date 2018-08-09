package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.common.service.GroupService;
import com.gubang.entity.Group;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.GroupMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupMapper groupMapper;

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

}