package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.common.service.ProductNameService;
import com.gubang.entity.ProductNameInfo;
import com.gubang.entity.ProductSeriesInfo;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductNameInfoMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class ProductNameServiceImpl implements ProductNameService {
	@Autowired
	private ProductNameInfoMapper productNameInfoMapper;

	@Override
	public ResultDTO getProductName(UserInfo userInfo, ProductNameInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			
			List<ProductSeriesInfo> getProductSeriesInfo = productNameInfoMapper.get(params);

			return result.setSuccess(getProductSeriesInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO insert(UserInfo userInfo, ProductNameInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			params.setId(CommonUtil.getUUid());

			params.setCreateBy(userInfo.getId());
			params.setCreateDate(new Date());
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			productNameInfoMapper.insertSelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO update(UserInfo userInfo, ProductNameInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			productNameInfoMapper.updateByPrimaryKeySelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
	
}