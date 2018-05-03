package com.gubang.service;

import com.gubang.dto.query.DownloadDto;
import com.gubang.dto.query.UploadDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface UtilService {

	ResultDTO upload(UserInfo userInfo, UploadDto params);

	Object download(DownloadDto params);

}
