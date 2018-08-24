package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.service.PCProductService;
import com.common.service.ProductNameService;
import com.common.service.ProductSeriesService;
import com.gubang.config.PCUserInfoParam;
import com.gubang.entity.ProductInfo;
import com.gubang.entity.ProductNameInfo;
import com.gubang.entity.ProductSeriesInfo;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/pcProduct")
public class ProdouctController {
	@Autowired
	private ProductSeriesService productSeriesService;
	@Autowired
	private ProductNameService productNameService;
	@Autowired
	private PCProductService pCProductService;

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
	
	/**
	 * 获取产品名称表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProductName", method = RequestMethod.POST)
	public ResultDTO getProductName(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductNameInfo params) {
		return productNameService.getProductName(userInfo, params);
	}
	
	/**
	 * 修改产品名称表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productNameUpdate", method = RequestMethod.POST)
	public ResultDTO productNameUpdate(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductNameInfo params) {
		return productNameService.update(userInfo, params);
	}
	
	/**
	 * 新增产品名称表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productNameInsert", method = RequestMethod.POST)
	public ResultDTO productNameInsert(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductNameInfo params) {
		return productNameService.insert(userInfo, params);
	}
	
	/**
	 * 获取产品名称表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProduct", method = RequestMethod.POST)
	public ResultDTO getProduct(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductInfo params) {
		return pCProductService.getProduct(userInfo, params);
	}
	
	/**
	 * 修改产品名称表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productUpdate", method = RequestMethod.POST)
	public ResultDTO productUpdate(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductInfo params) {
		return pCProductService.update(userInfo, params);
	}

	/**
	 * 新增产品名称表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productInsert", method = RequestMethod.POST)
	public ResultDTO productInsert(@PCUserInfoParam UserInfo userInfo, @RequestBody ProductInfo params) {
		return pCProductService.insert(userInfo, params);
	}
}
