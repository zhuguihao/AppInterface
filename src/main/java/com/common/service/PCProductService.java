package com.common.service;

import com.gubang.entity.ProductInfo;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface PCProductService {

	ResultDTO getProduct(UserInfo userInfo, ProductInfo params);

	ResultDTO insert(UserInfo userInfo, ProductInfo params);

	ResultDTO update(UserInfo userInfo, ProductInfo params);
}
