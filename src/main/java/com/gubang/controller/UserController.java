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
import com.gubang.dto.query.LoginQuery;
import com.gubang.dto.query.ModifyPwdDto;
import com.gubang.dto.query.RegisterQuery;
import com.gubang.entity.UserInfo;
import com.gubang.service.LoginService;
import com.gubang.util.ResultDTO;


/**
 * 注册&登录
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public ResultDTO register(HttpServletRequest request, HttpServletResponse response
			, @RequestBody RegisterQuery registerQuery) throws ParseException, IOException {
		ResultDTO res = new ResultDTO();
		if (registerQuery.inValid()) {
			return res.setParameterInvalid();
		}
		return loginService.register(registerQuery);
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResultDTO login(HttpServletRequest request, HttpServletResponse response
			, @RequestBody LoginQuery loginQuery) throws ParseException, IOException {
		ResultDTO res = new ResultDTO();
		if (loginQuery.inValid()) {
			return res.setParameterInvalid();
		}
		return loginService.login(loginQuery);
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.POST)
	public ResultDTO outLogin(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo) throws ParseException, IOException {
		ResultDTO res = new ResultDTO();
		//TODO @zgh
		if (null == userInfo) {
			return res.setNotLogin();
		}
		return loginService.logout(userInfo);
	}
	
	@RequestMapping(value="/modifyPwd",method = RequestMethod.POST)
	public ResultDTO modifyPwd(HttpServletRequest request, HttpServletResponse response
			, @RequestBody ModifyPwdDto params) throws ParseException, IOException {
		ResultDTO res = new ResultDTO();
		if (params.inValid()) {
			return res.setParameterInvalid();
		}
		return loginService.modifyPwd(params);
	}
}