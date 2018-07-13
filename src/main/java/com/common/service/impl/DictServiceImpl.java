package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.common.service.DictService;
import com.gubang.entity.Dict;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.DictMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public ResultDTO getDict(UserInfo userInfo, Dict params) {
		ResultDTO result = new ResultDTO();
		try {
			List<Dict> getDict = dictMapper.getDict(params );
			return result.setSuccess(getDict);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO editDict(UserInfo userInfo, Dict params) {
		ResultDTO result = new ResultDTO();
		try {
//			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			dictMapper.editDict(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO addDict(UserInfo userInfo, Dict params) {
		ResultDTO result = new ResultDTO();
		try {
			params.setId(CommonUtil.getUUid());
			
//			params.setCreateBy(userInfo.getId());
//			params.setCreateDate(new Date());
//			params.setUpdateBy(userInfo.getId());
//			params.setUpdateDate(new Date());
			dictMapper.addDict(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
}