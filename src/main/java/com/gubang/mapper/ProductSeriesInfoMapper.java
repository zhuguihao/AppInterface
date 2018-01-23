package com.gubang.mapper;

import java.util.List;

import com.gubang.entity.ProductSeriesInfo;

public interface ProductSeriesInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductSeriesInfo record);

    int insertSelective(ProductSeriesInfo record);

    ProductSeriesInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductSeriesInfo record);

    int updateByPrimaryKey(ProductSeriesInfo record);
    
    List<ProductSeriesInfo> findAll();
}