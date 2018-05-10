package com.gubang.mapper;

import java.util.List;

import com.gubang.entity.TFile;

public interface TFileMapper {
    int insert(TFile record);

    List<TFile> selectByFileParams(TFile record);
    
    int updateByPrimaryKeySelective(TFile record);
}