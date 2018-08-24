package com.gubang.mapper;

import java.util.List;
import com.gubang.entity.ProductInfo;
import com.gubang.vo.ProductVo;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
    
    List<ProductInfo> findAll();
    
    ProductVo getProduct(ProductVo productVo);

	List<ProductInfo> get(ProductInfo params);
}