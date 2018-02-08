package com.gubang.service;

import com.gubang.dto.query.CusProductQueryDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface CusProductService {

	ResultDTO getProductInfoByBarcode(UserInfo userInfo, CusProductQueryDto params);
}
