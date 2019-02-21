package com.common.controller;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.common.dto.SaleAfterDto;
import com.common.service.QuerySaleAfterService;
import com.gubang.config.PCUserInfoParam;
import com.gubang.dto.apply.FirstTrialRejectDto;
import com.gubang.dto.apply.SignExpressDto;
import com.gubang.dto.query.FirstTrialDto;
import com.gubang.entity.UserInfo;
import com.gubang.service.ProductApplySysService;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/saleAfter")
public class SaleAfterCenterController {

	@Autowired
	private QuerySaleAfterService querySaleAfterService;
	@Autowired
	private ProductApplySysService productApplySysService;

	/**
	 * 查询电话审核步骤数据
	 * 
	 * @param userInfo
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/getTelephoneAudit", method = RequestMethod.POST)
	public ResultDTO getSaleAfter(@PCUserInfoParam UserInfo userInfo, @RequestBody SaleAfterDto params) {
		return querySaleAfterService.getTelephoneAudit(userInfo, params);
	}

	/**
	 * 电话审核
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @author zhuguihao
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "telephoneAudit", method = RequestMethod.POST)
	public ResultDTO telephoneAudit(HttpServletRequest request, HttpServletResponse response,
			@PCUserInfoParam UserInfo userInfo, @RequestBody FirstTrialDto params) throws ParseException, IOException {
		return productApplySysService.firstTrial(userInfo, params);
	}
	
	 /** 
	 * 电话审核-拒绝
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @author zhuguihao
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "telephoneAuditReject", method = RequestMethod.POST)
	public ResultDTO telephoneAuditReject(HttpServletRequest request, HttpServletResponse response,
			@PCUserInfoParam UserInfo userInfo, @RequestBody FirstTrialRejectDto params) throws ParseException, IOException {
		return productApplySysService.firstTrialReject(userInfo, params);
	}
	

	/**
	 * 查询快递签收步骤数据
	 * 
	 * @param userInfo
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/getExpressDelivery", method = RequestMethod.POST)
	public ResultDTO getExpressDelivery(@PCUserInfoParam UserInfo userInfo, @RequestBody SaleAfterDto params) {
		return querySaleAfterService.getExpressDelivery(userInfo, params);
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
			@PCUserInfoParam UserInfo userInfo, @RequestBody SignExpressDto params)
			throws ParseException, IOException {
		return productApplySysService.signExpress(userInfo, params);
	}
}
