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
import com.gubang.dto.apply.FirstTrialPassDto;
import com.gubang.dto.apply.FirstTrialRejectDto;
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
			@UserInfoParam UserInfo userInfo, @RequestBody FirstTrialDto params)
			throws ParseException, IOException {
		return productApplySysService.firstTrial(userInfo, params);
	}
	
	/**
	 * 初审拒绝
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
	 * 初审通过
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "firstTrialPass", method = RequestMethod.POST)
	public ResultDTO firstTrialPass(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody FirstTrialPassDto params)
			throws ParseException, IOException {
		return productApplySysService.firstTrialPass(userInfo, params);
	}
	
}