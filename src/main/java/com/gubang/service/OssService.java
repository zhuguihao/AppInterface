package com.gubang.service;

import java.io.IOException;
import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import com.gubang.dto.query.OssDto;

public interface OssService {

	public void ossUpload(OssDto params);

	public URL downLoadUrl(OssDto params);
	
	public String downLoadStream(HttpServletResponse response, OssDto params)  throws IOException ;
}
