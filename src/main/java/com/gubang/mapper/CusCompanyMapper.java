package com.gubang.mapper;

import java.util.List;

import com.gubang.entity.CusCompany;

public interface CusCompanyMapper {
    int deleteByPrimaryKey(String id);

    int insert(CusCompany record);

    int insertSelective(CusCompany record);

    CusCompany selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CusCompany record);

    int updateByPrimaryKey(CusCompany record);
    
    List<CusCompany> findAll();
}