package com.gubang.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gubang.dto.query.LoginQuery;
import com.gubang.dto.query.RegisterQuery;
import com.gubang.service.LoginService;
import com.gubang.util.ResultDTO;


/**
 * 注册&登录
 * @author Administrator
 */
@RestController
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="register",method = RequestMethod.POST)
	public ResultDTO test(HttpServletRequest request, HttpServletResponse response
			, @RequestBody RegisterQuery registerQuery) throws ParseException, IOException {
		ResultDTO res = new ResultDTO();
		if (registerQuery.inValid()) {
			return res.setParameterInvalid();
		}
		return loginService.register(registerQuery);
	}
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public ResultDTO test(HttpServletRequest request, HttpServletResponse response
			, @RequestBody LoginQuery loginQuery) throws ParseException, IOException {
		ResultDTO res = new ResultDTO();
		if (loginQuery.inValid()) {
			return res.setParameterInvalid();
		}
		return loginService.login(loginQuery);
	}
}