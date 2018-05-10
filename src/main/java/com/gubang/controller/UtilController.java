package com.gubang.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gubang.dto.query.DownloadDto;
import com.gubang.service.UtilService;

/**
 * 工具类
 * @author Administrator
 */
@RestController
@RequestMapping("/util")
public class UtilController {
	@Autowired
	private UtilService utilService;

	/**
	 * 获取OSS下载地址
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadUrl", method = RequestMethod.GET)
	public URL downloadUrl(HttpServletRequest request, HttpServletResponse response,DownloadDto params) throws ParseException, IOException {
		return utilService.downloadUrl(params);
	}
	
	@RequestMapping(value = "/downLoadStream", method = RequestMethod.GET)
	public String downLoadStream(HttpServletRequest request, HttpServletResponse response,DownloadDto params) throws ParseException, IOException {
		return utilService.downLoadStream(response,params);
	}
}