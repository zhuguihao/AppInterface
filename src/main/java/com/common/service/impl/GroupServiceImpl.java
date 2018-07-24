package com.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.dto.GroupDto;
import com.common.service.GroupService;
import com.gubang.constant.Constant;
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
			List<Group> getGroup = groupMapper.getGroup(params);

			return result.setSuccess(this.getGroupDto(getGroup));
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	private List<GroupDto> getGroupDto(List<Group> group) {
		List<GroupDto> list = new ArrayList<GroupDto>();
		for (Group item : group) {
			if(StringUtils.isEmpty(item.getParentId())){
				GroupDto groupDto = new GroupDto();
				groupDto.setId(item.getId());
				groupDto.setGroupCode(item.getGroupCode());
				groupDto.setGroupName(item.getGroupName());
				groupDto.setIsDel(item.getIsDel());

				List<GroupDto> clidrenList = new ArrayList<GroupDto>();
				for (Group children : group) {
					if (item.getId().equals(children.getParentId())) {
						GroupDto childrenGroupDto = new GroupDto();
						childrenGroupDto.setId(children.getId());
						childrenGroupDto.setGroupCode(children.getGroupCode());
						childrenGroupDto.setGroupName(children.getGroupName());
						childrenGroupDto.setIsDel(children.getIsDel());
						clidrenList.add(childrenGroupDto);

					}
				}
				groupDto.setChildren(clidrenList);
				list.add(groupDto);
			}			
		}
		return list;
	}

	@Override
	public ResultDTO editGroup(UserInfo userInfo, Group params) {
		ResultDTO result = new ResultDTO();
		try {
			// params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			groupMapper.editGroup(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO addGroup(UserInfo userInfo, Group params) {
		ResultDTO result = new ResultDTO();
		try {
			params.setId(CommonUtil.getUUid());

			// params.setCreateBy(userInfo.getId());
			// params.setCreateDate(new Date());
			// params.setUpdateBy(userInfo.getId());
			// params.setUpdateDate(new Date());
			groupMapper.addGroup(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

}