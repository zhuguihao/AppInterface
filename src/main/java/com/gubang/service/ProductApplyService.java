package com.gubang.service;

import com.gubang.dto.apply.ApplyDto;
import com.gubang.dto.query.ProductApplyScanDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductApplyService {
	
	ResultDTO checkStatus(UserInfo userInfo, ProductApplyScanDto params);

	ResultDTO subApply(UserInfo userInfo, ApplyDto params);
}
