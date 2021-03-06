package com.common.service;

import com.common.dto.GroupMenuDto;
import com.gubang.entity.Menu;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ComMenuService {

	ResultDTO getMenu(UserInfo userInfo, Menu params);
	
	ResultDTO getGroupMenu(UserInfo userInfo, GroupMenuDto params);

	ResultDTO editMenu(UserInfo userInfo, Menu params);

	ResultDTO addMenu(UserInfo userInfo, Menu params);

	ResultDTO getWebMenu(UserInfo userInfo, Menu params);
}
