package com.gubang.service;

import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import com.gubang.dto.query.DownloadDto;
import com.gubang.dto.query.UploadDto;
import com.gubang.entity.UserInfo;

public interface UtilService {

	void upload(UserInfo userInfo, UploadDto params) throws Exception;

	URL downloadUrl(DownloadDto params);

	String downLoadStream(HttpServletResponse response, DownloadDto params);

	String uploadBase64(HttpServletResponse response, DownloadDto base64);

}
