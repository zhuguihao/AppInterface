package com.gubang.mapper;

import com.gubang.entity.TFile;

public interface TFileMapper {
    int insert(TFile record);

    TFile selectByFileParams(TFile record);
    
    int updateByPrimaryKeySelective(TFile record);
}