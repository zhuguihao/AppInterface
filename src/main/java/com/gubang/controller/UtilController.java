package com.gubang.controller;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gubang.config.UserInfoParam;
import com.gubang.dto.query.DownloadDto;
import com.gubang.dto.query.UploadDto;
import com.gubang.entity.UserInfo;
import com.gubang.service.UtilService;
import com.gubang.util.ResultDTO;

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
	 * 上传到OSS文件服务器
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResultDTO upload(HttpServletRequest request, HttpServletResponse response,@UserInfoParam UserInfo userInfo,UploadDto params) throws ParseException, IOException {
		return utilService.upload(userInfo,params);
	}
	
	/**
	 * 下载OSS文件
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public Object download(HttpServletRequest request, HttpServletResponse response,DownloadDto params) throws ParseException, IOException {
		return utilService.download(params);
	}
}