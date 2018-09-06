package com.common.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.common.dto.SaleAfterDto;
import com.common.service.QuerySaleAfterService;
import com.gubang.entity.TFile;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplyQueryMapper;
import com.gubang.mapper.TFileMapper;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleApplyVo;

@Service
public class QuerySaleAfterServiceImpl implements QuerySaleAfterService {
	@Autowired
	private ProductSaleApplyQueryMapper productSaleApplyQueryMapper;
	@Autowired
	private TFileMapper tFileMapper;

	@Override
	public ResultDTO getTelephoneAudit(UserInfo userInfo, SaleAfterDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
//			if(params.inValid()){
//				return result.setParameterInvalid();
//			}
			
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setApplyStatus(params.getApplyStatus());
			List<ProductSaleApplyVo> productSaleApplyVo = productSaleApplyQueryMapper.getTelephoneAudit(record);
			
			for(ProductSaleApplyVo item:productSaleApplyVo){
				TFile file = new TFile();
				file.setApplyId(item.getId());
				List<TFile> files = tFileMapper.selectByFileParams(file);
				JSONArray fileUrls = new JSONArray();
				for (TFile fileUrl : files) {
					if (!StringUtils.isEmpty(fileUrl.getDownLoadUrl())) {
						fileUrls.add(fileUrl.getDownLoadUrl());
					}
				}
				item.setFile(fileUrls);
			}
			
			return result.setSuccess(productSaleApplyVo);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getExpressDelivery(UserInfo userInfo, SaleAfterDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
//			if(params.inValid()){
//				return result.setParameterInvalid();
//			}
			
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setApplyStatus(params.getApplyStatus());
			List<ProductSaleApplyVo> productSaleApplyVo = productSaleApplyQueryMapper.getExpressDelivery(record);
			
			return result.setSuccess(productSaleApplyVo);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
}