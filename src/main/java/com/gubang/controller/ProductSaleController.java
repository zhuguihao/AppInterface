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
import com.gubang.dto.query.ProductSaleDelDto;
import com.gubang.dto.query.StorageDto;
import com.gubang.dto.query.OutStorageDto;
import com.gubang.entity.UserInfo;
import com.gubang.service.ProductSaleService;
import com.gubang.util.ResultDTO;

/**
 * 产品
 * 
 * @author Administrator
 */
@RestController
@RequestMapping("/productSale")
public class ProductSaleController {

	@Autowired
	ProductSaleService productSaleService;

	/**
	 * 根据产品ID判断是否存在 存在则保存当前的出库单信息
	 * 
	 * @param request
	 * @param response
	 * @param params
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "storage", method = RequestMethod.POST)
	public ResultDTO storage(HttpServletRequest request, HttpServletResponse response,
			@RequestBody StorageDto params, @UserInfoParam UserInfo userInfo)
			throws ParseException, IOException {
		return productSaleService.storage(params, userInfo);
	}
	
	/**
	 * 根据产品码查询库中是否存在该产品
	 * 存在则进行出库
	 * @param request
	 * @param response
	 * @param params
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "outStorage", method = RequestMethod.POST)
	public ResultDTO outStorage(HttpServletRequest request, HttpServletResponse response,
			@RequestBody OutStorageDto params, @UserInfoParam UserInfo userInfo)
			throws ParseException, IOException {
		return productSaleService.outStorage(params, userInfo);
	}
	
	/**
	 * 根据出库单ID和出库状态删除出库产品
	 * @param request
	 * @param response
	 * @param params
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "delStorage", method = RequestMethod.POST)
	public ResultDTO delStorage(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ProductSaleDelDto params, @UserInfoParam UserInfo userInfo)
			throws ParseException, IOException {
		return productSaleService.delStorage(params, userInfo);
	}
	
	@RequestMapping(value = "getStorageByUser", method = RequestMethod.POST)
	public ResultDTO getStorageByUser(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo)
			throws ParseException, IOException {
		return productSaleService.getStorageByUser(userInfo);
	}
	
}