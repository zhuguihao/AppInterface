package com.common.service;

import com.gubang.entity.Group;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface GroupService {

	ResultDTO getGroup(UserInfo userInfo, Group params);

	ResultDTO editGroup(UserInfo userInfo, Group params);

	ResultDTO addGroup(UserInfo userInfo, Group params);

//	ResultDTO getGroupList(UserInfo userInfo, DictDto params);
}
