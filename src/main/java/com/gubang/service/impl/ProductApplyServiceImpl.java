package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gubang.constant.ApplyCode;
import com.gubang.dto.query.ProductApplyScanDto;
import com.gubang.entity.ProductSaleInfo;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleInfoMapper;
import com.gubang.service.ProductApplyService;
import com.gubang.util.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductApplyServiceImpl implements ProductApplyService {

	@Autowired
	ProductSaleInfoMapper productSaleInfoMapper;

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Override
	public ResultDTO checkStatus(UserInfo userInfo, ProductApplyScanDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			
			/**
			 * status:apply_sold_product（售出状态）
			 * 查询售出状态的产品
			 */
			ProductSaleInfo productSaleInfo = new ProductSaleInfo();
			productSaleInfo.setBarCode(params.getBarCode());
			productSaleInfo.setProductStatus(ApplyCode.APPLY_SOLD_PRODUCT.getCode());
			productSaleInfo = productSaleInfoMapper.selectByProductSaleInfoParams(productSaleInfo);
			if (null == productSaleInfo) {
				return result.setNotFoundProduct();
			}
			
			/**
			 * 查询是否已经存在当前申请人的申请单
			 * 存在返回申请单状态
			 * 不存在则新建申请单
			 */
			
			return result.setSuccess(productSaleInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "查询售后产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

}
