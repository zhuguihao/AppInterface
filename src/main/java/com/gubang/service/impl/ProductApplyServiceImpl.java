package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.ApplyCode;
import com.gubang.constant.Constant;
import com.gubang.constant.SaleApplyCode;
import com.gubang.dto.apply.ApplyDto;
import com.gubang.dto.apply.ApplyWayBillDto;
import com.gubang.dto.query.ProductApplyScanDto;
import com.gubang.dto.result.ApplyStatusResult;
import com.gubang.entity.ProductSaleApply;
import com.gubang.entity.ProductSaleInfo;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplyMapper;
import com.gubang.mapper.ProductSaleInfoMapper;
import com.gubang.service.ProductApplyService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductApplyServiceImpl implements ProductApplyService {

	@Autowired
	private ProductSaleInfoMapper productSaleInfoMapper;
	@Autowired
	private ProductSaleApplyMapper productSaleApplyMapper;

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
			 * status:apply_sold_product（售出状态） 查询售出状态的产品
			 */
			ProductSaleInfo productSaleInfo = new ProductSaleInfo();
			productSaleInfo.setBarCode(params.getBarCode());
			productSaleInfo.setProductStatus(ApplyCode.APPLY_SOLD_PRODUCT.getCode());
			productSaleInfo = productSaleInfoMapper.selectByProductSaleInfoParams(productSaleInfo);
			if (null == productSaleInfo) {
				return result.setNotFoundProduct();
			}

			/**
			 * 查询当前产品编号的申请单
			 */
			ProductSaleApply record = new ProductSaleApply();
			record.setProductSaleId(productSaleInfo.getId());
			ProductSaleApply productSaleApply = productSaleApplyMapper.selectByProductSaleInfoParams(record);
			if (null == productSaleApply) {
				return result.setNotFoundProduct();
			}

			/**
			 * 返回申请单状态
			 */
			ApplyStatusResult applyStatusResult = new ApplyStatusResult();
			applyStatusResult.setApplyStatus(productSaleApply.getApplyStatus());

			return result.setSuccess(applyStatusResult);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "查询售后产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO subApply(UserInfo userInfo, ApplyDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			/**
			 * status:apply_sold_product（售出状态） 查询售出状态的产品
			 */
			ProductSaleInfo productSaleInfo = new ProductSaleInfo();
			productSaleInfo.setBarCode(params.getBarCode());
			productSaleInfo.setProductStatus(ApplyCode.APPLY_SOLD_PRODUCT.getCode());
			productSaleInfo = productSaleInfoMapper.selectByProductSaleInfoParams(productSaleInfo);
			if (null == productSaleInfo) {
				return result.setNotFoundProduct();
			}

			/**
			 * 新建申请单
			 */
			ProductSaleApply record = new ProductSaleApply();
			record.setId(CommonUtil.getUUid());
			record.setProductSaleId(productSaleInfo.getId());
			record.setFaultPoint(params.getFaultPoint());
			record.setCusName(params.getCusName());
			record.setCusTelphone(params.getCusTelphone());
			record.setApplyStatus(SaleApplyCode.FIRST_TRIAL.getCode());
			record.setIsDel(Constant.DB_FALSE_FLAG);
			record.setCreateBy(userInfo.getId());
			record.setCreateDate(new Date());
			record.setUpdateBy(userInfo.getId());
			record.setUpdateDate(new Date());

			if (productSaleApplyMapper.insert(record) < 1) {
				return result.setSystemError();
			}

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "客户提交售后单失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO applyWayBill(UserInfo userInfo, ApplyWayBillDto params) {
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
//			if (null == productSaleApplyVo) {
//				return result.setNotFoundApplyProduct();
//			}
			return null;
		} catch (Exception e) {
			/**
			 * 回滚事务
			 */
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			log.error(userInfo.getAccount() + "售后单初审拒绝保存失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

}
