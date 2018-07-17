package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.common.service.UserService;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.UserInfoMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Override
	public ResultDTO getUser(UserInfo userInfo, UserInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			List<UserInfo> getUser = userInfoMapper.getUser(params);
			
			return result.setSuccess(getUser);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO editUser(UserInfo userInfo, UserInfo params) {
		ResultDTO result = new ResultDTO();
		try {
//			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO addUser(UserInfo userInfo, UserInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			params.setId(CommonUtil.getUUid());
			
//			params.setCreateBy(userInfo.getId());
//			params.setCreateDate(new Date());
//			params.setUpdateBy(userInfo.getId());
//			params.setUpdateDate(new Date());
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
}