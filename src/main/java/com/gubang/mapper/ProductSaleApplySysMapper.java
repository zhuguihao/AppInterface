package com.gubang.mapper;

import com.gubang.entity.ProductSaleApplySys;

public interface ProductSaleApplySysMapper {
    int insert(ProductSaleApplySys record);

    ProductSaleApplySys selectByProductSaleInfoParams(ProductSaleApplySys record);
    
    int updateByPrimaryKeySelective(ProductSaleApplySys record);
}