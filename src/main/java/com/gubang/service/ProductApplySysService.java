package com.gubang.service;

import com.gubang.dto.apply.FirstTrialRejectDto;
import com.gubang.dto.apply.SignExpressDto;
import com.gubang.dto.query.FirstTrialDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductApplySysService {

	ResultDTO firstTrial(UserInfo userInfo, FirstTrialDto params);

	ResultDTO firstTrialReject(UserInfo userInfo, FirstTrialRejectDto params);

	ResultDTO signExpress(UserInfo userInfo, SignExpressDto params);

	ResultDTO rejExpress(UserInfo userInfo, SignExpressDto params);

	ResultDTO afterDepartmentPass(UserInfo userInfo, SignExpressDto params);

	ResultDTO courierDepartmentPass(UserInfo userInfo, SignExpressDto params);
}
