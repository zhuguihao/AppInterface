package com.gubang.service;

import com.gubang.dto.query.FirstTrialDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductApplySysService {

	ResultDTO firstTrial(UserInfo userInfo, FirstTrialDto params);
}