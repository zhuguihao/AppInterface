package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gubang.constant.ApplyCode;
import com.gubang.dto.query.CusProductQueryDto;
import com.gubang.dto.result.SoldProductResult;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleInfoMapper;
import com.gubang.service.CusProductService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;
import com.gubang.vo.GetProductSaleVo;
import com.gubang.vo.ProductSaleVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CusProductServiceImpl implements CusProductService {

	@Autowired
	ProductSaleInfoMapper productSaleInfoMapper;

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Override
	public ResultDTO getProductInfoByBarcode(UserInfo userInfo, CusProductQueryDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			/**
			 * 查询当前产品编号对应的产品信息
			 */
			ProductSaleVo psVo = new ProductSaleVo();
			psVo.setProductStatus(ApplyCode.APPLY_SOLD_PRODUCT.getCode());
			psVo.setBarCode(params.getBarCode());
			GetProductSaleVo getProductSale = productSaleInfoMapper.getProductSaleInfo(psVo);
			
			/**
			 * 未查询到相关在售产品信息
			 */
			if(null == getProductSale){
				return result.setNotSaleProduct();
			}

			/**
			 * 组装返回报文
			 */
			SoldProductResult soldProductResult = new SoldProductResult();
			soldProductResult.setId(getProductSale.getId());
			soldProductResult.setBarCode(getProductSale.getBarCode());
			soldProductResult
					.setAfterSaleTime(CommonUtil.getFormatDate(getProductSale.getAfterSaleTime(), "yyyy-MM-dd"));
			soldProductResult.setProDesc(getProductSale.getProDesc());
			soldProductResult.setProModel(getProductSale.getProModel());
			soldProductResult.setProName(getProductSale.getProName());
			soldProductResult.setSeries(getProductSale.getSeries());
			soldProductResult.setVoltageRange(getProductSale.getVoltageRange());
			soldProductResult.setPolicy(getProductSale.getPolicy());
			soldProductResult.setPolicyReplace(getProductSale.getPolicyReplace());
			return result.setSuccess(soldProductResult);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo==null?"":userInfo.getAccount() + "查询售后产品信息：" + e.getMessage());
			return result.setSystemError();
		}
	}

}
