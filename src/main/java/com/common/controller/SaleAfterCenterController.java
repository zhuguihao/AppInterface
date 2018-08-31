package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.common.dto.SaleAfterDto;
import com.common.service.QuerySaleAfterService;
import com.gubang.config.PCUserInfoParam;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/saleAfter")
public class SaleAfterCenterController {
	
	@Autowired
	private QuerySaleAfterService querySaleAfterService;

	/**
	 * 查询售后单通过状态
	 * @param userInfo
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/getSaleAfter", method = RequestMethod.POST)
	public ResultDTO getSaleAfter(@PCUserInfoParam UserInfo userInfo, @RequestBody SaleAfterDto params) {
		return querySaleAfterService.getSaleAfter(userInfo,params);
	}
}
