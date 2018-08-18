package com.common.service;

import com.gubang.entity.ProductSeriesInfo;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductSeriesService {

	ResultDTO getProductSeries(UserInfo userInfo, ProductSeriesInfo params);

	ResultDTO insert(UserInfo userInfo, ProductSeriesInfo params);

	ResultDTO pcProductUpdate(UserInfo userInfo, ProductSeriesInfo params);
}
