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
import com.gubang.dto.apply.ApplyDto;
import com.gubang.dto.apply.ApplyWayBillDto;
import com.gubang.dto.query.ApplyImageDto;
import com.gubang.dto.query.ProductApplyScanDto;
import com.gubang.entity.UserInfo;
import com.gubang.service.ProductApplyService;
import com.gubang.util.ResultDTO;

/**
 * 产品售后
 * 
 * @author Administrator
 */
@RestController
@RequestMapping("/productApply")
public class ProductApplyController {

	@Autowired
	ProductApplyService productApplyService;

	/**
	 * 根据产品编号查询售后信息
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "checkStatus", method = RequestMethod.POST)
	public ResultDTO checkStatus(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody ProductApplyScanDto params)
			throws ParseException, IOException {
		return productApplyService.checkStatus(userInfo, params);
	}
	
	/**
	 * 提交申请单
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "subApply", method = RequestMethod.POST)
	public ResultDTO subApply(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody ApplyDto params)
			throws ParseException, IOException {
		return productApplyService.subApply(userInfo, params);
	}
	
	/**
	 * 提交运单信息
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "applyWayBill", method = RequestMethod.POST)
	public ResultDTO applyWayBill(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody ApplyWayBillDto params)
			throws ParseException, IOException {
		return productApplyService.applyWayBill(userInfo, params);
	}
	
	/**
	 * 客户签收已维修完毕的产品
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "signTacking", method = RequestMethod.POST)
	public ResultDTO signTacking(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody ProductApplyScanDto params)
			throws ParseException, IOException {
		return productApplyService.signTacking(userInfo, params);
	}
	
	/**
	 * 上传故障图
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param params
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "applyImage", method = RequestMethod.POST)
	public ResultDTO applyImage(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, ApplyImageDto params)
			throws ParseException, IOException {
		return productApplyService.applyImage(userInfo, params);
	}
	
}