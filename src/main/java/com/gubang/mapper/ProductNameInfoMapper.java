package com.gubang.mapper;

import java.util.List;

import com.gubang.entity.ProductNameInfo;
import com.gubang.entity.ProductSeriesInfo;

public interface ProductNameInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductNameInfo record);

    int insertSelective(ProductNameInfo record);

    ProductNameInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductNameInfo record);

    int updateByPrimaryKey(ProductNameInfo record);
    
    List<ProductNameInfo> findAll();

	List<ProductSeriesInfo> get(ProductNameInfo params);
}