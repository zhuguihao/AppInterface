package com.gubang.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gubang.service.ProductService;
import com.gubang.util.ResultDTO;


/**
 * 产品
 * @author Administrator
 */
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="getAllProductCascade",method = RequestMethod.POST)
	public ResultDTO getAllProductJson(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		return productService.getAllProductCascade();
	}
}