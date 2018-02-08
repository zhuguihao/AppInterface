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
import com.gubang.dto.query.CusProductQueryDto;
import com.gubang.entity.UserInfo;
import com.gubang.service.CusProductService;
import com.gubang.util.ResultDTO;

/**
 * 售后客户查询
 * 
 * @author Administrator
 */
@RestController
@RequestMapping("/cusProuctQuery")
public class CusProductQueryController {

	@Autowired
	CusProductService cusProductService;

	/**
	 * 获取当前的流水号对应的出售状态或者出库状态的产品信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "getProductInfoByBarcode", method = RequestMethod.POST)
	public ResultDTO getProductInfoByBarcode(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo, @RequestBody CusProductQueryDto params)
			throws ParseException, IOException {
		return cusProductService.getProductInfoByBarcode(userInfo, params);
	}
}