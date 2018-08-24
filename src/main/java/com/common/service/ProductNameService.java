package com.common.service;

import com.gubang.entity.ProductNameInfo;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductNameService {
	ResultDTO getProductName(UserInfo userInfo, ProductNameInfo params);

	ResultDTO insert(UserInfo userInfo, ProductNameInfo params);

	ResultDTO update(UserInfo userInfo, ProductNameInfo params);
}
