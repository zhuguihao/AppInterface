package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.common.service.ProductSeriesService;
import com.gubang.config.PCUserInfoParam;
import com.gubang.entity.ProductSeriesInfo;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/pcProduct")
public class ProdouctController {
	@Autowired
	private ProductSeriesService productSeriesService;

	/**
	 * 获取产品系列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProductSeries", method = RequestMethod.POST)
	public ResultDTO getDictList(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductSeriesInfo params) {
		return productSeriesService.getProductSeries(userInfo, params);
	}
	
	/**
	 * 添加产品系列
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pcProductInsert", method = RequestMethod.POST)
	public ResultDTO pcProductInsert(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductSeriesInfo params) {
		return productSeriesService.insert(userInfo, params);
	}
	
	/**
	 * 修改产品系列
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pcProductUpdate", method = RequestMethod.POST)
	public ResultDTO pcProductUpdate(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductSeriesInfo params) {
		return productSeriesService.pcProductUpdate(userInfo, params);
	}
}
