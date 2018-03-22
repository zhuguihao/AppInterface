package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.SaleApplyCode;
import com.gubang.dto.query.ProductApplyQueryDto;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplyQueryMapper;
import com.gubang.service.ProductApplyQueryService;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleApplyQueryVo;
import com.gubang.vo.ProductSaleApplyVo;

import java.util.List;

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
			List<ProductSaleApplyVo> productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParams(record);
			
			JSONArray productSaleApplyArr = new JSONArray();
			for (ProductSaleApplyVo item:productSaleApplyVo) {
				ProductSaleApplyQueryVo productSaleApplyQueryVo = new ProductSaleApplyQueryVo();
				productSaleApplyQueryVo.setId(item.getId());
				productSaleApplyQueryVo.setAddress(item.getAddress());
				productSaleApplyQueryVo.setAddressee(item.getAddressee());
				productSaleApplyQueryVo.setAddressPhone(item.getAddressPhone());
				productSaleApplyQueryVo.setAfterSaleTime(item.getAfterSaleTime());
				productSaleApplyQueryVo.setApplyStatus(item.getApplyStatus());
				productSaleApplyQueryVo.setBarCode(item.getBarCode());
				productSaleApplyQueryVo.setCusName(item.getCusName());
				productSaleApplyQueryVo.setCusTelphone(item.getCusTelphone());
				productSaleApplyQueryVo.setCustomerId(item.getCustomerId());
				productSaleApplyQueryVo.setDistributionPrice(item.getDistributionPrice());
				productSaleApplyQueryVo.setFaultPoint(item.getFaultPoint());
				productSaleApplyQueryVo.setPolicy(item.getPolicy());
				productSaleApplyQueryVo.setPolicyReplace(item.getPolicyReplace());
				productSaleApplyQueryVo.setProDesc(item.getProDesc());
				productSaleApplyQueryVo.setProductStatus(item.getProductStatus());
				productSaleApplyQueryVo.setProModel(item.getProModel());
				productSaleApplyQueryVo.setProName(item.getProName());
				productSaleApplyQueryVo.setRetailPrice(item.getRetailPrice());
				productSaleApplyQueryVo.setSeries(item.getSeries());
				productSaleApplyQueryVo.setVoltageRange(item.getVoltageRange());
				productSaleApplyQueryVo.setWaybillNumber(item.getWaybillNumber());
				productSaleApplyArr.add(productSaleApplyQueryVo);
			}

			return result.setSuccess(productSaleApplyArr);
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

	public JSONArray getApplyCenter(){
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("key", SaleApplyCode.FIRST_TRIAL.getCode());
		obj.put("value", SaleApplyCode.FIRST_TRIAL.getDesc());
		array.add(obj);
		obj = new JSONObject();
		obj.put("key", SaleApplyCode.APPLY_WAY_BILL.getCode());
		obj.put("value", SaleApplyCode.APPLY_WAY_BILL.getDesc());
		array.add(obj);
		obj = new JSONObject();
		obj.put("key", "all");
		obj.put("value", "所有审批件");
		array.add(obj);
		return array;
	}
}
