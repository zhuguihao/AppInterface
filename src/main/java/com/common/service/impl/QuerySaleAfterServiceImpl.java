package com.common.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.common.dto.SaleAfterDto;
import com.common.service.QuerySaleAfterService;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductSaleApplyQueryMapper;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleApplyVo;

@Service
public class QuerySaleAfterServiceImpl implements QuerySaleAfterService {
	@Autowired
	private ProductSaleApplyQueryMapper productSaleApplyQueryMapper;

	@Override
	public ResultDTO getSaleAfter(UserInfo userInfo, SaleAfterDto params) {
		ResultDTO result = new ResultDTO();
		try {
//			if (null == userInfo) {
//				return result.setNotLogin();
//			}
//			
			if(params.inValid()){
				return result.setParameterInvalid();
			}
			
			ProductSaleApplyVo record = new ProductSaleApplyVo();
			record.setApplyStatus(params.getApplyStatus());
			List<ProductSaleApplyVo> productSaleApplyVo = productSaleApplyQueryMapper.productSaleApplyByParams(record);
			
			return result.setSuccess(productSaleApplyVo);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
}