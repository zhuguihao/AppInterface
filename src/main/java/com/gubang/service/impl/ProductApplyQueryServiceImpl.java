package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.ApplyPolicyStateCode;
import com.gubang.constant.SaleApplyCode;
import com.gubang.dto.query.ProductApplyQueryDto;
import com.gubang.entity.TFile;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplyQueryMapper;
import com.gubang.mapper.TFileMapper;
import com.gubang.service.ProductApplyQueryService;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleApplyVo;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductApplyQueryServiceImpl implements ProductApplyQueryService {

	@Autowired
	private ProductSaleApplyQueryMapper productSaleApplyQueryMapper;
	@Autowired
	private TFileMapper tFileMapper;

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
			List<ProductSaleApplyVo> productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParams(record);

			/**
			 * 所有的单子
			 */
			JSONArray productSaleApplyArr = new JSONArray();
			/**
			 * 售后初审单
			 */
			JSONArray firstTrialArr = new JSONArray();
			/**
			 * 客户快递跟踪
			 */
			JSONArray courierTrackingArr = new JSONArray();
			/**
			 * 公司快递跟踪
			 */
			JSONArray companyCourierTrackingArr = new JSONArray();
			/**
			 * 售后部
			 */
			JSONArray aftersaledepartmentArr = new JSONArray();
			/**
			 * 售后部寄件
			 */
			JSONArray courierDepartmentArr = new JSONArray();
			/**
			 * 售后单完成
			 */
			JSONArray finshApplyArr = new JSONArray();

			for (ProductSaleApplyVo item : productSaleApplyVo) {
				// ProductSaleApplyQueryVo productSaleApplyQueryVo = new
				// ProductSaleApplyQueryVo();
				// productSaleApplyQueryVo.setId(item.getId());
				// productSaleApplyQueryVo.setAddress(item.getAddress());
				// productSaleApplyQueryVo.setAddressee(item.getAddressee());
				// productSaleApplyQueryVo.setAddressPhone(item.getAddressPhone());
				// productSaleApplyQueryVo.setAfterSaleTime(item.getAfterSaleTime());
				// productSaleApplyQueryVo.setApplyStatus(item.getApplyStatus());
				// productSaleApplyQueryVo.setBarCode(item.getBarCode());
				// productSaleApplyQueryVo.setCusName(item.getCusName());
				// productSaleApplyQueryVo.setCusTelphone(item.getCusTelphone());
				// productSaleApplyQueryVo.setCustomerId(item.getCustomerId());
				// productSaleApplyQueryVo.setDistributionPrice(item.getDistributionPrice());
				// productSaleApplyQueryVo.setFaultPoint(item.getFaultPoint());
				// productSaleApplyQueryVo.setPolicy(item.getPolicy());
				// productSaleApplyQueryVo.setPolicyReplace(item.getPolicyReplace());
				// productSaleApplyQueryVo.setProDesc(item.getProDesc());
				// productSaleApplyQueryVo.setProductStatus(item.getProductStatus());
				// productSaleApplyQueryVo.setProModel(item.getProModel());
				// productSaleApplyQueryVo.setProName(item.getProName());
				// productSaleApplyQueryVo.setRetailPrice(item.getRetailPrice());
				// productSaleApplyQueryVo.setSeries(item.getSeries());
				// productSaleApplyQueryVo.setVoltageRange(item.getVoltageRange());
				// productSaleApplyQueryVo.setWaybillNumber(item.getWaybillNumber());
				//
				// productSaleApplyQueryVo.setApplyUser(item.getApplyUser());
				// productSaleApplyQueryVo.setApplyDesc(item.getApplyDesc());
				// productSaleApplyQueryVo.setIsPay(item.getIsPay());
				// productSaleApplyQueryVo.setPayGoods(item.getPayGoods());
				// productSaleApplyQueryVo.setExpressAddress(item.getExpressAddress());
				// productSaleApplyQueryVo.setExpressName(item.getExpressName());
				// productSaleApplyQueryVo.setExpressPhone(item.getExpressPhone());
				// productSaleApplyQueryVo.setApplyRejectResion(item.getApplyRejectResion());
				// productSaleApplyQueryVo.setApplyPolicyState(item.getApplyPolicyState());
				// productSaleApplyQueryVo.setIsRecipient(item.getIsRecipient());
				// productSaleApplyQueryVo.setSysProductStatus(item.getSysProductStatus());
				// productSaleApplyQueryVo.setSysWaybillNumber(item.getSysWaybillNumber());

				if (SaleApplyCode.FIRST_TRIAL.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.FIRST_TRIAL.getDesc());
					firstTrialArr.add(item);
				} else if (SaleApplyCode.COURIER_TRACKING.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.COURIER_TRACKING.getDesc());
					courierTrackingArr.add(item);
				} else if (SaleApplyCode.COMPANY_COURIER_TRACKING.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.COMPANY_COURIER_TRACKING.getDesc());
					companyCourierTrackingArr.add(item);
				} else if (SaleApplyCode.AFTERSALE_DEPARTMENT.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.AFTERSALE_DEPARTMENT.getDesc());
					aftersaledepartmentArr.add(item);
				} else if (SaleApplyCode.COURIER_DEPARTMENT.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.COURIER_DEPARTMENT.getDesc());
					courierDepartmentArr.add(item);
				} else if (SaleApplyCode.FINSH_APPLY.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.FINSH_APPLY.getDesc());
					finshApplyArr.add(item);
				} else if (SaleApplyCode.THE_TRIAL_REJECT.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.THE_TRIAL_REJECT.getDesc());
				} else if (SaleApplyCode.THE_TRIAL_PASS.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.THE_TRIAL_PASS.getDesc());
				} else if (SaleApplyCode.COURIER_TRACKING_REJECT.getCode().equals(item.getApplyStatus())) {
					item.setApplyStatusDesc(SaleApplyCode.COURIER_TRACKING_REJECT.getDesc());
				}
				TFile file = new TFile();
				file.setApplyId(item.getId());
				List<TFile> files = tFileMapper.selectByFileParams(file);
				JSONArray fileUrls = new JSONArray();
				for (TFile fileUrl : files) {
					if (!StringUtils.isEmpty(fileUrl.getDownLoadUrl())) {
//						FileUrlVo fileUrlVo = new FileUrlVo();
//						fileUrlVo.setImageUrl(fileUrl.getDownLoadUrl());
						fileUrls.add(fileUrl.getDownLoadUrl());
					}
				}
				item.setFile(fileUrls);
				productSaleApplyArr.add(item);
			}
			JSONArray retArr = new JSONArray();

			JSONObject retObj = new JSONObject();
			retObj.put("key", "all");
			retObj.put("value", "全部");
			retObj.put("list", productSaleApplyArr);
			retArr.add(retObj);

			retObj = new JSONObject();
			retObj.put("key", SaleApplyCode.FIRST_TRIAL.getCode());
			retObj.put("value", SaleApplyCode.FIRST_TRIAL.getDesc());
			retObj.put("list", firstTrialArr);
			retArr.add(retObj);

			retObj = new JSONObject();
			retObj.put("key", SaleApplyCode.COURIER_TRACKING.getCode());
			retObj.put("value", SaleApplyCode.COURIER_TRACKING.getDesc());
			retObj.put("list", courierTrackingArr);
			retArr.add(retObj);

			retObj = new JSONObject();
			retObj.put("key", SaleApplyCode.AFTERSALE_DEPARTMENT.getCode());
			retObj.put("value", SaleApplyCode.AFTERSALE_DEPARTMENT.getDesc());
			retObj.put("list", aftersaledepartmentArr);
			retArr.add(retObj);

			retObj = new JSONObject();
			retObj.put("key", SaleApplyCode.COURIER_DEPARTMENT.getCode());
			retObj.put("value", SaleApplyCode.COURIER_DEPARTMENT.getDesc());
			retObj.put("list", courierDepartmentArr);
			retArr.add(retObj);

			retObj = new JSONObject();
			retObj.put("key", SaleApplyCode.COMPANY_COURIER_TRACKING.getCode());
			retObj.put("value", SaleApplyCode.COMPANY_COURIER_TRACKING.getDesc());
			retObj.put("list", companyCourierTrackingArr);
			retArr.add(retObj);

			retObj = new JSONObject();
			retObj.put("key", SaleApplyCode.FINSH_APPLY.getCode());
			retObj.put("value", SaleApplyCode.FINSH_APPLY.getDesc());
			retObj.put("list", finshApplyArr);
			retArr.add(retObj);

			return result.setSuccess(retArr);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "查询售后产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getCommpanyAddress(UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		JSONObject retObj = new JSONObject();
		retObj.put("companyName", "公司名称");
		retObj.put("expressName", "公司收件人");
		retObj.put("expressAddress", "公司收件地址");
		retObj.put("expressPhone", "公司电话");
		return result.setSuccess(retObj);
	}

	@Override
	public ResultDTO getApplyPolicyStateCode(UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		if (null == userInfo) {
			return result.setNotLogin();
		}
		JSONArray retArr = new JSONArray();
		JSONObject retObj = new JSONObject();
		retObj.put("key", ApplyPolicyStateCode.POLICY_REPLACE_STATUS.getCode());
		retObj.put("value", ApplyPolicyStateCode.POLICY_REPLACE_STATUS.getDesc());
		retArr.add(retObj);
		retObj = new JSONObject();
		retObj.put("key", ApplyPolicyStateCode.POLICY_STATUS.getCode());
		retObj.put("value", ApplyPolicyStateCode.POLICY_STATUS.getDesc());
		retArr.add(retObj);
		return result.setSuccess(retArr);
	}
}
