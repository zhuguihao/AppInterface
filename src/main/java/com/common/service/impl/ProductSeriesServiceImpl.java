package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.common.service.ProductSeriesService;
import com.gubang.entity.ProductSeriesInfo;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSeriesInfoMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class ProductSeriesServiceImpl implements ProductSeriesService {
	@Autowired
	private ProductSeriesInfoMapper productSeriesInfoMapper;

	@Override
	public ResultDTO getProductSeries(UserInfo userInfo, ProductSeriesInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			
			List<ProductSeriesInfo> getProductSeriesInfo = productSeriesInfoMapper.get(params);

			return result.setSuccess(getProductSeriesInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO insert(UserInfo userInfo, ProductSeriesInfo params) {
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
			productSeriesInfoMapper.insertSelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO pcProductUpdate(UserInfo userInfo, ProductSeriesInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			productSeriesInfoMapper.updateByPrimaryKeySelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
	
}