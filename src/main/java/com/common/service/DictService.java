package com.common.service;

import com.common.dto.DictDto;
import com.gubang.entity.Dict;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface DictService {

	ResultDTO getDict(UserInfo userInfo, Dict params);

	ResultDTO editDict(UserInfo userInfo, Dict params);

	ResultDTO addDict(UserInfo userInfo, Dict params);

	ResultDTO getDictList(UserInfo userInfo, DictDto params);
}
