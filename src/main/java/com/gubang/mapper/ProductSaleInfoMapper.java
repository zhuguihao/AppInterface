package com.gubang.mapper;

import java.util.List;

import com.gubang.entity.ProductSaleInfo;
import com.gubang.vo.GetProductSaleVo;
import com.gubang.vo.ProductSaleVo;

public interface ProductSaleInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductSaleInfo record);

    int insertSelective(ProductSaleInfo record);

    ProductSaleInfo selectByPrimaryKey(String id);
    
    ProductSaleInfo selectByProductSaleInfoParams(ProductSaleInfo productSaleInfo);

    int updateByPrimaryKeySelective(ProductSaleInfo record);

    int updateByPrimaryKey(ProductSaleInfo record);
    
    int selectProductSaleByBarcode(String barCode);
    
    GetProductSaleVo getProductSaleInfo(ProductSaleVo params);
    
    List<GetProductSaleVo> productSaleInfo(ProductSaleVo params);
}