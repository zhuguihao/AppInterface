package com.gubang.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gubang.util.ResultDTO;


/**
 * 资金明细对账
 * 
 * @author Administrator
 *
 */
@RestController
public class TestController {

	private final static Logger log = LoggerFactory.getLogger("Admin");
	
	@RequestMapping("test")
	public ResultDTO test(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		log.info("test comes request");
		ResultDTO res = new ResultDTO();
		return res.setSuccess("ok---2");
	}
}