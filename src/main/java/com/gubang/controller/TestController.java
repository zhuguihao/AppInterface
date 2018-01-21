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
import com.gubang.entity.UserInfo;
import com.gubang.service.LoginService;
import com.gubang.service.UserCacheService;
import com.gubang.util.ResultDTO;


/**
 * 注册&登录
 * @author Administrator
 */
@RestController
public class TestController {

	@Autowired
	UserCacheService userCacheService;
	
	@RequestMapping(value="test",method = RequestMethod.GET)
	public ResultDTO register(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		ResultDTO res = new ResultDTO();
		UserInfo ui = userCacheService.get(request.getParameter("key"));
		return res.setSuccess(ui);
	}
}