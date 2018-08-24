package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.common.service.PCProductService;
import com.gubang.entity.ProductInfo;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductInfoMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class PCProductServiceImpl implements PCProductService {
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public ResultDTO getProduct(UserInfo userInfo, ProductInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}

			List<ProductInfo> getProductSeriesInfo = productInfoMapper.get(params);

			return result.setSuccess(getProductSeriesInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO insert(UserInfo userInfo, ProductInfo params) {
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
			productInfoMapper.insertSelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO update(UserInfo userInfo, ProductInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			productInfoMapper.updateByPrimaryKeySelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
	
}