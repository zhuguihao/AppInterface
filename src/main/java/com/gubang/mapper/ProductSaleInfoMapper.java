package com.gubang.mapper;

import com.gubang.entity.ProductSaleInfo;

public interface ProductSaleInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductSaleInfo record);

    int insertSelective(ProductSaleInfo record);

    ProductSaleInfo selectByPrimaryKey(String id);
    
    ProductSaleInfo selectByProductSaleInfoParams(ProductSaleInfo productSaleInfo);

    int updateByPrimaryKeySelective(ProductSaleInfo record);

    int updateByPrimaryKey(ProductSaleInfo record);
    
    int selectProductSaleByBarcode(String barCode);
}