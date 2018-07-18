package com.common.service;

import com.gubang.entity.Menu;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface CommonMenuService {

	ResultDTO getMenu(UserInfo userInfo, Menu params);

	ResultDTO editMenu(UserInfo userInfo, Menu params);

	ResultDTO addMenu(UserInfo userInfo, Menu params);
}
