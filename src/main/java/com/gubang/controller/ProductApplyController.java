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

	@RequestMapping(value = "checkStatus", method = RequestMethod.POST)
	public ResultDTO checkStatus(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody ProductApplyScanDto params)
			throws ParseException, IOException {
		return productApplyService.checkStatus(userInfo, params);
	}
}