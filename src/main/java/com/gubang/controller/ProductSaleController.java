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
import com.gubang.dto.query.SoldDto;
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
	public ResultDTO storage(HttpServletRequest request, HttpServletResponse response, @RequestBody StorageDto params,
			@UserInfoParam UserInfo userInfo) throws ParseException, IOException {
		return productSaleService.storage(params, userInfo);
	}

	/**
	 * 根据产品码查询库中是否存在该产品 存在则进行出库
	 * 
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
			@RequestBody OutStorageDto params, @UserInfoParam UserInfo userInfo) throws ParseException, IOException {
		return productSaleService.outStorage(params, userInfo);
	}

	/**
	 * 根据出库单ID和出库状态删除出库产品
	 * 
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

	/**
	 * 入库人员查询当天当前操作的入库记录
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "getStorageByUser", method = RequestMethod.POST)
	public ResultDTO getStorageByUser(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo) throws ParseException, IOException {
		return productSaleService.getStorageByUser(userInfo);
	}

	/**
	 * 出库人员查询当天当前操作的入库记录
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "getOutStorageByUser", method = RequestMethod.POST)
	public ResultDTO getOutStorageByUser(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo) throws ParseException, IOException {
		return productSaleService.getOutStorageByUser(userInfo);
	}
	
	/**
	 * 删除出库状态接口
	 * 将当前的出库状态驳回到入库状态
	 * @param request
	 * @param response
	 * @param params
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "delOutStorage", method = RequestMethod.POST)
	public ResultDTO delOutStorage(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ProductSaleDelDto params, @UserInfoParam UserInfo userInfo)
			throws ParseException, IOException {
		return productSaleService.delOutStorage(params, userInfo);
	}

	/**
	 * 销售人员根据产品编号和出库状态的产品进行生成售后时间
	 * @param request
	 * @param response
	 * @param params
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "soldTime", method = RequestMethod.POST)
	public ResultDTO soldTime(HttpServletRequest request, HttpServletResponse response, @RequestBody SoldDto params,
			@UserInfoParam UserInfo userInfo) throws ParseException, IOException {
		return productSaleService.soldTime(userInfo, params);
	}
	
	/**
	 * 获取当前操作者当前的售出产品的列表
	 * @param request
	 * @param response
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "getSoldByUser", method = RequestMethod.POST)
	public ResultDTO getSoldByUser(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo) throws ParseException, IOException {
		return productSaleService.getSoldByUser(userInfo);
	}
	
	/**
	 * 删除售出状态接口
	 * 将当前的售出状态驳回到出库状态
	 * @param request
	 * @param response
	 * @param params
	 * @param userInfo
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "delSold", method = RequestMethod.POST)
	public ResultDTO delSold(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ProductSaleDelDto params, @UserInfoParam UserInfo userInfo)
			throws ParseException, IOException {
		return productSaleService.delSold(params, userInfo);
	}
}