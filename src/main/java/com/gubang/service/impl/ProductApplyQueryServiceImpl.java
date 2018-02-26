package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gubang.dto.query.ProductApplyQueryDto;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplyQueryMapper;
import com.gubang.service.ProductApplyQueryService;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleApplyVo;

import java.awt.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductApplyQueryServiceImpl implements ProductApplyQueryService {

	@Autowired
	private ProductSaleApplyQueryMapper productSaleApplyQueryMapper;

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Override
	public ResultDTO sysApply(UserInfo userInfo, ProductApplyQueryDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}

			/**
			 * 查询当前产品编号的申请单
			 */
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setApplyStatus(params.getApplyStatus());
			ProductSaleApplyVo productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParams(record);
			if (null == productSaleApplyVo) {
				return result.setNotFoundProduct();
			}
			
			return result.setSuccess(productSaleApplyVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "查询售后产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}
}
