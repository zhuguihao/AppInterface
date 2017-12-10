package com.gubang.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gubang.dto.query.GetProductInfoByBarcodeQuery;
import com.gubang.dto.result.GetProductInfoByBarcodeResult;
import com.gubang.entity.ProductDetailInfo;
import com.gubang.service.ProductService;
import com.gubang.util.ResultDTO;


/**
 * 资金明细对账
 * 
 * @author Administrator
 *
 */
@RestController
public class ProductController {

	private final static Logger log = LoggerFactory.getLogger("Admin");
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="getProductInfoByBarcode",method = RequestMethod.POST)
	public ResultDTO test(HttpServletRequest request, HttpServletResponse response
			, @RequestBody GetProductInfoByBarcodeQuery getProductInfoByBarcodeQuery) throws ParseException, IOException {
		log.info("getProductInfoByBarcode comes request");
		ResultDTO res = new ResultDTO();
		if (getProductInfoByBarcodeQuery.inValid()) {
			return res.setParameterInvalid();
		}
		ProductDetailInfo info = productService.selectByProductBarcode(getProductInfoByBarcodeQuery.getBarcode());
		if (info == null) {
			return res.setNotFound();
		} else {
			GetProductInfoByBarcodeResult target = new GetProductInfoByBarcodeResult();
			BeanUtils.copyProperties(info, target);
			return res.setSuccess(target);
		}
	}
}