package com.common.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.common.service.CommonMenuService;
import com.gubang.entity.Menu;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.MenuMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class CommonMenuServiceImpl implements CommonMenuService {
	@Autowired
	MenuMapper menuMapper;
	
	@Override
	public ResultDTO getMenu(UserInfo userInfo, Menu params) {
		ResultDTO result = new ResultDTO();
		try {
			List<Menu> getMenu = menuMapper.getMenu(params);
			
			return result.setSuccess(getMenu);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO editMenu(UserInfo userInfo, Menu params) {
		ResultDTO result = new ResultDTO();
		try {
//			params.setUpdateBy(userInfo.getId());
//			params.setUpdateDate(new Date());
//			menuMapper.updateByPrimaryKeySelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO addMenu(UserInfo userInfo, Menu params) {
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