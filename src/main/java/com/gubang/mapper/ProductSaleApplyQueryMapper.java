package com.gubang.mapper;

import java.util.List;

import com.gubang.vo.ProductSaleApplyVo;

public interface ProductSaleApplyQueryMapper {
	List<ProductSaleApplyVo> productSaleApplyByParams(ProductSaleApplyVo record);

}