package com.gubang.mapper;

import com.gubang.entity.ProductSaleApply;

public interface ProductSaleApplyMapper {
    int insert(ProductSaleApply record);

    int insertSelective(ProductSaleApply record);

    ProductSaleApply selectByProductSaleInfoParams(ProductSaleApply record);
    
    int updateByPrimaryKeySelective(ProductSaleApply record);
}