package com.gubang.controller;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gubang.config.UserInfoParam;
import com.gubang.entity.UserInfo;
import com.gubang.service.MenuService;
import com.gubang.util.ResultDTO;


/**
 * 菜单
 * @author Administrator
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/getMenu",method = RequestMethod.POST)
	public ResultDTO selectMenuByGroup(HttpServletRequest request, HttpServletResponse response,
			@UserInfoParam UserInfo userInfo) throws ParseException, IOException {
		return menuService.selectMenuByGroup(userInfo);
	}
}