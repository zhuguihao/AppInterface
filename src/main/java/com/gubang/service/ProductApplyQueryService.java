package com.gubang.service;

import com.gubang.dto.query.ProductApplyQueryDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductApplyQueryService {
	
	ResultDTO sysApply(UserInfo userInfo, ProductApplyQueryDto params);
}
