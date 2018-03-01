package com.gubang.service;

import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface MenuService {

	ResultDTO selectMenuByGroup(UserInfo userInfo);
	
}
