package com.common.service;

import com.common.dto.RelationGroupUserDto;
import com.common.dto.RelationMenuDto;
import com.gubang.entity.Group;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface GroupService {

	ResultDTO getGroup(UserInfo userInfo, Group params);

	ResultDTO editGroup(UserInfo userInfo, Group params);

	ResultDTO addGroupChildren(UserInfo userInfo, Group params);

	ResultDTO relationMenu(UserInfo userInfo, RelationMenuDto params);
	
	ResultDTO relationUser(UserInfo userInfo, RelationGroupUserDto params);
}
