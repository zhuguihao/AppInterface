package com.gubang.service;

import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import com.gubang.dto.query.DownloadDto;
import com.gubang.dto.query.UploadDto;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface UtilService {

	ResultDTO upload(UserInfo userInfo, UploadDto params);

	URL downloadUrl(DownloadDto params);

	void downLoadStream(HttpServletResponse response,DownloadDto params);

}
