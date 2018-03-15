package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.gubang.dto.query.ProductApplyQueryDto;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplyQueryMapper;
import com.gubang.service.ProductApplyQueryService;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleApplyQueryVo;
import com.gubang.vo.ProductSaleApplyVo;
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
			
			ProductSaleApplyQueryVo productSaleApplyQueryVo = new ProductSaleApplyQueryVo();
			productSaleApplyQueryVo.setId(productSaleApplyVo.getId());
			productSaleApplyQueryVo.setAddress(productSaleApplyVo.getAddress());
			productSaleApplyQueryVo.setAddressee(productSaleApplyVo.getAddressee());
			productSaleApplyQueryVo.setAddressPhone(productSaleApplyVo.getAddressPhone());
			productSaleApplyQueryVo.setAfterSaleTime(productSaleApplyVo.getAfterSaleTime());
			productSaleApplyQueryVo.setApplyStatus(productSaleApplyVo.getApplyStatus());
			productSaleApplyQueryVo.setBarCode(productSaleApplyVo.getBarCode());
			productSaleApplyQueryVo.setCusName(productSaleApplyVo.getCusName());
			productSaleApplyQueryVo.setCusTelphone(productSaleApplyVo.getCusTelphone());
			productSaleApplyQueryVo.setCustomerId(productSaleApplyVo.getCustomerId());
			productSaleApplyQueryVo.setDistributionPrice(productSaleApplyVo.getDistributionPrice());
			productSaleApplyQueryVo.setFaultPoint(productSaleApplyVo.getFaultPoint());
			productSaleApplyQueryVo.setPolicy(productSaleApplyVo.getPolicy());
			productSaleApplyQueryVo.setPolicyReplace(productSaleApplyVo.getPolicyReplace());
			productSaleApplyQueryVo.setProDesc(productSaleApplyVo.getProDesc());
			productSaleApplyQueryVo.setProductStatus(productSaleApplyVo.getProductStatus());
			productSaleApplyQueryVo.setProModel(productSaleApplyVo.getProModel());
			productSaleApplyQueryVo.setProName(productSaleApplyVo.getProName());
			productSaleApplyQueryVo.setRetailPrice(productSaleApplyVo.getRetailPrice());
			productSaleApplyQueryVo.setSeries(productSaleApplyVo.getSeries());
			productSaleApplyQueryVo.setVoltageRange(productSaleApplyVo.getVoltageRange());
			productSaleApplyQueryVo.setWaybillNumber(productSaleApplyVo.getWaybillNumber());
			
			return result.setSuccess(productSaleApplyQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "查询售后产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getCommpanyAddress(UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		if (null == userInfo) {
			return result.setNotLogin();
		}
		JSONObject retObj = new JSONObject();
		retObj.put("expressName", "公司收件人");
		retObj.put("expressAddress", "公司收件地址");
		retObj.put("expressPhone", "公司电话");
		return result.setSuccess(retObj);
	}
}
