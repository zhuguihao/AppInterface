package com.gubang.service;

import com.gubang.dto.apply.ApplyDto;
import com.gubang.dto.apply.ApplyWayBillDto;
import com.gubang.dto.query.ApplyImageDto;
import com.gubang.dto.query.ProductApplyScanDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductApplyService {
	
	ResultDTO checkStatus(UserInfo userInfo, ProductApplyScanDto params);

	ResultDTO subApply(UserInfo userInfo, ApplyDto params);

	ResultDTO applyWayBill(UserInfo userInfo, ApplyWayBillDto params);

	ResultDTO signTacking(UserInfo userInfo, ProductApplyScanDto params);

	ResultDTO applyImage(UserInfo userInfo, ApplyImageDto params);

}
