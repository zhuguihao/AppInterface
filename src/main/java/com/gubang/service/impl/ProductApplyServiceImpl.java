package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.gubang.mapper.ProductSaleApplyQueryMapper;
import com.gubang.mapper.ProductSaleInfoMapper;
import com.gubang.service.ProductApplyService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleApplyVo;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductApplyServiceImpl implements ProductApplyService {

	@Autowired
	private ProductSaleInfoMapper productSaleInfoMapper;
	@Autowired
	private ProductSaleApplyMapper productSaleApplyMapper;
	@Autowired
	private ProductSaleApplyQueryMapper productSaleApplyQueryMapper;

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Override
	public ResultDTO checkStatus(UserInfo userInfo, ProductApplyScanDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			/**
			 * 查询当前产品编号的的产品信息
			 */
			ProductSaleInfo productSaleInfo = new ProductSaleInfo();
			productSaleInfo.setBarCode(params.getBarCode());
//			productSaleInfo.setProductStatus(ApplyCode.APPLY_SOLD_PRODUCT.getCode());
			productSaleInfo = productSaleInfoMapper.selectByProductSaleInfoParams(productSaleInfo);
			/**
			 * 不存在
			 */
			if (null == productSaleInfo) {
				return result.setNotFoundProduct();
			}
			
			/**
			 * 售出状态(可售后)
			 */
			if(ApplyCode.APPLY_OUT_STORAGE.getCode().equals(productSaleInfo.getProductStatus())){
				ApplyStatusResult applyStatusResult = new ApplyStatusResult();
				applyStatusResult.setApplyStatus(productSaleInfo.getProductStatus());
				return result.setSuccess(applyStatusResult);
			}
			/**
			 * 出库状态
			 */
			if(ApplyCode.APPLY_SOLD_PRODUCT.getCode().equals(productSaleInfo.getProductStatus())){
				/**
				 * 查询当前产品编号的申请单
				 */
				ProductSaleApply record = new ProductSaleApply();
				record.setProductSaleId(productSaleInfo.getId());
				ProductSaleApply productSaleApply = productSaleApplyMapper.selectByProductSaleInfoParams(record);
				ApplyStatusResult applyStatusResult = new ApplyStatusResult();
				applyStatusResult.setApplyStatus(productSaleApply == null ? null : productSaleApply.getApplyStatus());
				return result.setSuccess(applyStatusResult);
			}
			return result.setNotFoundProduct();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo==null?"":userInfo.getAccount() + "查询售后产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO subApply(UserInfo userInfo, ApplyDto params) {
		ResultDTO result = new ResultDTO();
		try {
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
			record.setCreateBy(userInfo==null?"":userInfo.getId());
			record.setCreateDate(new Date());
			record.setUpdateBy(userInfo==null?"":userInfo.getId());
			record.setUpdateDate(new Date());

			if (productSaleApplyMapper.insert(record) < 1) {
				return result.setSystemError();
			}

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo==null?"":userInfo.getAccount() + "客户提交售后单失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO applyWayBill(UserInfo userInfo, ApplyWayBillDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			/**
			 * 查询是否存在该笔等待客户提交快递单的单据
			 */
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setBarCode(params.getBarCode());
			ProductSaleApplyVo productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParam(record);

			if (null == productSaleApplyVo) {
				return result.setNotFoundApplyProduct();
			}

			/**
			 * 订单状态不是初审通过或者快递单被驳回状态的
			 */
			if (!(SaleApplyCode.THE_TRIAL_PASS.getCode().equals(productSaleApplyVo.getApplyStatus())
					|| SaleApplyCode.COURIER_TRACKING_REJECT.getCode().equals(productSaleApplyVo.getApplyStatus()))) {
				return result.setNotFoundApplyProduct();
			}

			/**
			 * 填写客户快递单信息
			 */
			ProductSaleApply productSaleApply = new ProductSaleApply();
			productSaleApply.setId(productSaleApplyVo.getId());
			productSaleApply.setApplyStatus(SaleApplyCode.COURIER_TRACKING.getCode());
			productSaleApply.setWaybillNumber(params.getWaybillNumber());
			productSaleApply.setAddressee(params.getAddressee());
			productSaleApply.setAddress(params.getAddress());
			productSaleApply.setAddressPhone(params.getAddressPhone());

			productSaleApply.setUpdateBy(userInfo==null?"":userInfo.getId());
			productSaleApply.setUpdateDate(new Date());
			productSaleApplyMapper.updateByPrimaryKeySelective(productSaleApply);

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo==null?"":userInfo.getAccount() + "售后单客户快递单保存失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO signTacking(UserInfo userInfo, ProductApplyScanDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			/**
			 * 查询是否存在该笔等待客户提交快递单的单据
			 */
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setBarCode(params.getBarCode());
			ProductSaleApplyVo productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParam(record);

			if (null == productSaleApplyVo) {
				return result.setNotFoundApplyProduct();
			}

			/**
			 * 订单状态不是待客户签收的
			 */
			if (!SaleApplyCode.COMPANY_COURIER_TRACKING.getCode().equals(productSaleApplyVo.getApplyStatus())) {
				return result.setNotFoundApplyProduct();
			}

			/**
			 * 客户签收快递，完成售后
			 */
			ProductSaleApply productSaleApply = new ProductSaleApply();
			productSaleApply.setId(productSaleApplyVo.getId());
			productSaleApply.setApplyStatus(SaleApplyCode.FINSH_APPLY.getCode());

			productSaleApply.setUpdateBy(userInfo==null?"":userInfo.getId());
			productSaleApply.setUpdateDate(new Date());
			productSaleApplyMapper.updateByPrimaryKeySelective(productSaleApply);

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo==null?"":userInfo.getAccount() + "售后单客户签收产品保存失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

}
