package com.gubang.service;

import com.gubang.dto.query.ProductSaleDelDto;
import com.gubang.dto.query.SoldDto;
import com.gubang.dto.query.StorageDto;
import com.gubang.dto.query.OutStorageDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductSaleService {
	
	ResultDTO storage(StorageDto params,UserInfo userInfo);
	
	ResultDTO outStorage(OutStorageDto params,UserInfo userInfo);

	ResultDTO delStorage(ProductSaleDelDto params, UserInfo userInfo);
	
	ResultDTO getStorageByUser(UserInfo userInfo);

	ResultDTO getOutStorageByUser(UserInfo userInfo);

	ResultDTO soldTime(UserInfo userInfo, SoldDto params);

	ResultDTO getSoldByUser(UserInfo userInfo);

	ResultDTO delOutStorage(ProductSaleDelDto params, UserInfo userInfo);

	ResultDTO delSold(ProductSaleDelDto params, UserInfo userInfo);
}
