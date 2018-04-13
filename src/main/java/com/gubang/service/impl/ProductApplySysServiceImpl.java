package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.Constant;
import com.gubang.constant.SaleApplyCode;
import com.gubang.dto.apply.FirstTrialRejectDto;
import com.gubang.dto.apply.SignExpressDto;
import com.gubang.dto.query.FirstTrialDto;
import com.gubang.entity.ProductSaleApply;
import com.gubang.entity.ProductSaleApplySys;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplyMapper;
import com.gubang.mapper.ProductSaleApplyQueryMapper;
import com.gubang.mapper.ProductSaleApplySysMapper;
import com.gubang.service.ProductApplySysService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleApplyVo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductApplySysServiceImpl implements ProductApplySysService {

	@Autowired
	private ProductSaleApplySysMapper productSaleApplySysMapper;
	@Autowired
	private ProductSaleApplyMapper productSaleApplyMapper;
	@Autowired
	private ProductSaleApplyQueryMapper productSaleApplyQueryMapper;

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultDTO firstTrial(UserInfo userInfo, FirstTrialDto params) {
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
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setId(params.getProductSaleApplyId());
			record.setApplyStatus(SaleApplyCode.FIRST_TRIAL.getCode());
			ProductSaleApplyVo productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParam(record);
			if (null == productSaleApplyVo) {
				return result.setNotFoundApplyProduct();
			}

			/**
			 * 保存电话回访信息
			 */
			ProductSaleApplySys productSaleApplySys = new ProductSaleApplySys();
			productSaleApplySys.setId(CommonUtil.getUUid());
			productSaleApplySys.setProductSaleApplyId(params.getProductSaleApplyId());
			productSaleApplySys.setIsPay(Constant.IS_PAY_GOODS);
			productSaleApplySys.setPayGoods(params.getPayGoods());
			productSaleApplySys.setApplyUser(userInfo.getId());
			productSaleApplySys.setApplyDesc(params.getApplyDesc());
			productSaleApplySys.setExpressName(params.getExpressName());
			productSaleApplySys.setExpressPhone(params.getExpressPhone());
			productSaleApplySys.setExpressAddress(params.getExpressAddress());
			productSaleApplySys.setApplyPolicyState(params.getApplyPolicyState());

			productSaleApplySys.setCreateBy(userInfo.getId());
			productSaleApplySys.setCreateDate(new Date());
			productSaleApplySys.setUpdateBy(userInfo.getId());
			productSaleApplySys.setUpdateDate(new Date());
			productSaleApplySysMapper.insert(productSaleApplySys);

			/**
			 * 修改当前售后单的状态
			 */
			ProductSaleApply productSaleApply = new ProductSaleApply();
			productSaleApply.setId(params.getProductSaleApplyId());
			productSaleApply.setApplyStatus(SaleApplyCode.THE_TRIAL_PASS.getCode());

			productSaleApply.setUpdateBy(userInfo.getId());
			productSaleApply.setUpdateDate(new Date());
			productSaleApplyMapper.updateByPrimaryKeySelective(productSaleApply);

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			/**
			 * 回滚事务
			 */
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			log.error(userInfo.getAccount() + "售后单电话回访保存失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO firstTrialReject(UserInfo userInfo, FirstTrialRejectDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			/**
			 * 查询是否存在该笔等待拒绝初审的单据
			 */
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setId(params.getProductSaleApplyId());
			record.setApplyStatus(SaleApplyCode.FIRST_TRIAL.getCode());
			ProductSaleApplyVo productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParam(record);
			if (null == productSaleApplyVo) {
				return result.setNotFoundApplyProduct();
			}

			/**
			 * 修改当前售后单的状态
			 */
			ProductSaleApply productSaleApply = new ProductSaleApply();
			productSaleApply.setId(params.getProductSaleApplyId());
			productSaleApply.setApplyStatus(SaleApplyCode.THE_TRIAL_REJECT.getCode());

			productSaleApply.setUpdateBy(userInfo.getId());
			productSaleApply.setUpdateDate(new Date());
			productSaleApplyMapper.updateByPrimaryKeySelective(productSaleApply);

			/**
			 * 填写客服拒绝原因
			 */
			ProductSaleApplySys productSaleApplySys = new ProductSaleApplySys();
			productSaleApplySys.setId(CommonUtil.getUUid());
			productSaleApplySys.setApplyRejectResion(params.getApplyRejectResion());
			productSaleApplySys.setProductSaleApplyId(params.getProductSaleApplyId());

			productSaleApplySys.setCreateBy(userInfo.getId());
			productSaleApplySys.setCreateDate(new Date());
			productSaleApplySys.setUpdateBy(userInfo.getId());
			productSaleApplySys.setUpdateDate(new Date());
			productSaleApplySysMapper.insert(productSaleApplySys);

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "售后单初审拒绝保存失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO signExpress(UserInfo userInfo, SignExpressDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			/**
			 * 查询是否存在等待签收的单据
			 */
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setId(params.getProductSaleApplyId());
			record.setApplyStatus(SaleApplyCode.COURIER_TRACKING.getCode());
			ProductSaleApplyVo productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParam(record);
			if (null == productSaleApplyVo) {
				return result.setNotFoundApplyProduct();
			}

			/**
			 * 签收客户快递
			 */
			ProductSaleApply productSaleApply = new ProductSaleApply();
			productSaleApply.setId(params.getProductSaleApplyId());
			productSaleApply.setApplyStatus(SaleApplyCode.AFTERSALE_DEPARTMENT.getCode());

			productSaleApply.setUpdateBy(userInfo.getId());
			productSaleApply.setUpdateDate(new Date());
			productSaleApplyMapper.updateByPrimaryKeySelective(productSaleApply);

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "售后单签收客户快递保存失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO rejExpress(UserInfo userInfo, SignExpressDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			/**
			 * 查询是否存在等待签收的单据
			 */
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setId(params.getProductSaleApplyId());
			record.setApplyStatus(SaleApplyCode.COURIER_TRACKING.getCode());
			ProductSaleApplyVo productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParam(record);
			if (null == productSaleApplyVo) {
				return result.setNotFoundApplyProduct();
			}

			/**
			 * 驳回签收客户快递
			 */
			ProductSaleApply productSaleApply = new ProductSaleApply();
			productSaleApply.setId(params.getProductSaleApplyId());
			productSaleApply.setApplyStatus(SaleApplyCode.COURIER_TRACKING_REJECT.getCode());
			productSaleApply.setWaybillNumber("");
			productSaleApply.setAddressee("");
			productSaleApply.setAddress("");
			productSaleApply.setAddressPhone("");

			productSaleApply.setUpdateBy(userInfo.getId());
			productSaleApply.setUpdateDate(new Date());
			productSaleApplyMapper.updateByPrimaryKeySelective(productSaleApply);

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "售后单拒绝签收客户快递保存失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

}