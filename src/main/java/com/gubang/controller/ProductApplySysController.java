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
import com.gubang.dto.apply.FirstTrialRejectDto;
import com.gubang.dto.apply.SignExpressDto;
import com.gubang.dto.query.FirstTrialDto;
import com.gubang.entity.UserInfo;
import com.gubang.service.ProductApplySysService;
import com.gubang.util.ResultDTO;

/**
 * 产品售后（客服）
 * 
 * @author Administrator
 */
@RestController
@RequestMapping("/proApplySys")
public class ProductApplySysController {

	@Autowired
	private ProductApplySysService productApplySysService;

	/**
	 * 初审
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "firstTrial", method = RequestMethod.POST)
	public ResultDTO firstTrial(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody FirstTrialDto params) throws ParseException, IOException {
		return productApplySysService.firstTrial(userInfo, params);
	}

	/**
	 * 初审拒绝
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "firstTrialReject", method = RequestMethod.POST)
	public ResultDTO firstTrialReject(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody FirstTrialRejectDto params)
			throws ParseException, IOException {
		return productApplySysService.firstTrialReject(userInfo, params);
	}

	/**
	 * 签收客户快递
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "signExpress", method = RequestMethod.POST)
	public ResultDTO firstTrialPass(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody SignExpressDto params)
			throws ParseException, IOException {
		return productApplySysService.signExpress(userInfo, params);
	}

	/**
	 * 驳回客户快递
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "rejExpress", method = RequestMethod.POST)
	public ResultDTO rejExpress(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody SignExpressDto params)
			throws ParseException, IOException {
		return productApplySysService.rejExpress(userInfo, params);
	}
	
	/**
	 * 售后部完成维修
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "afterDepartmentPass", method = RequestMethod.POST)
	public ResultDTO afterDepartmentPass(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody SignExpressDto params)
			throws ParseException, IOException {
		return productApplySysService.afterDepartmentPass(userInfo, params);
	}
	
	/**
	 * 售后部门寄件
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "courierDepartmentPass", method = RequestMethod.POST)
	public ResultDTO courierDepartmentPass(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody SignExpressDto params)
			throws ParseException, IOException {
		return productApplySysService.courierDepartmentPass(userInfo, params);
	}
}