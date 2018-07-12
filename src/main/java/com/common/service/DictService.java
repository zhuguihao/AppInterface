package com.common.service;

import com.gubang.entity.Dict;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface DictService {

	ResultDTO getDict(UserInfo userInfo, Dict params);

	ResultDTO editDict(UserInfo userInfo, Dict params);

}
