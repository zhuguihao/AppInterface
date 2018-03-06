package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.Constant;
import com.gubang.dto.query.FirstTrialDto;
import com.gubang.entity.ProductSaleApplySys;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplySysMapper;
import com.gubang.service.ProductApplySysService;
import com.gubang.util.ResultDTO;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductApplySysServiceImpl implements ProductApplySysService {

	@Autowired
	private ProductSaleApplySysMapper productSaleApplySysMapper;

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Override
	public ResultDTO  firstTrial(UserInfo userInfo, FirstTrialDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			
			/**
			 * 查询产品信息
			 */
//			ProductSaleApplyVo record = new ProductSaleApplyVo();
//			record.setId(params.getProductSaleApplyId());
//			ProductSaleApplyVo productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParams(record);
			/**
			 * 保存电话回访信息
			 */
			ProductSaleApplySys record = new ProductSaleApplySys();
			record.setProductSaleApplyId(params.getProductSaleApplyId());
			if(!StringUtils.isEmpty(params.getIsPay())){
				record.setIsPay(Constant.IS_PAY_GOODS);
				record.setPayGoods(params.getPayGoods());
			}
//			record.setApplyPolicyState(applyPolicyState);
			record.setApplyRejectResion(params.getApplyRejectResion());
			record.setApplyUser(userInfo.getId());
			
			record.setCreateBy(userInfo.getId());
			record.setCreateDate(new Date());
			record.setUpdateBy(userInfo.getId());
			record.setUpdateDate(new Date());
			productSaleApplySysMapper.insert(record);
			
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "售后单电话回访保存失败：" + e.getMessage());
			return result.setSystemError();
		}
	}
}
