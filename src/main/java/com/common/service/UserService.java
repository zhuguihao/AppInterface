package com.common.service;

import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface UserService {

	ResultDTO getUser(UserInfo userInfo, UserInfo params);

	ResultDTO editUser(UserInfo userInfo, UserInfo params);

	ResultDTO addUser(UserInfo userInfo, UserInfo params);
}
