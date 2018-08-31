package com.common.service;

import com.common.dto.SaleAfterDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface QuerySaleAfterService {

	ResultDTO getSaleAfter(UserInfo userInfo, SaleAfterDto params);
}
