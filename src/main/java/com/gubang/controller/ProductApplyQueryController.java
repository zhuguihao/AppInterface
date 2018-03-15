package com.gubang.controller;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gubang.config.UserInfoParam;
import com.gubang.dto.query.ProductApplyQueryDto;
import com.gubang.entity.UserInfo;
import com.gubang.service.ProductApplyQueryService;
import com.gubang.util.ResultDTO;

/**
 * 查询产品售后
 * 
 * @author Administrator
 */
@RestController
@RequestMapping("/productApplyQuery")
public class ProductApplyQueryController {

	@Autowired
	private ProductApplyQueryService productApplyQueryService;
	
	/**
	 * 售后人员管理平台售后单查询
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "sysApply", method = RequestMethod.POST)
	public ResultDTO sysApply(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody ProductApplyQueryDto params)
			throws ParseException, IOException {
		return productApplyQueryService.sysApply(userInfo, params);
	}
	
	@RequestMapping(value = "getCommpanyAddress", method = RequestMethod.POST)
	public ResultDTO getCommpanyAddress(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo)
			throws ParseException, IOException {
		return productApplyQueryService.getCommpanyAddress(userInfo);
	}
}