package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.common.service.DictService;
import com.gubang.config.UserInfoParam;
import com.gubang.entity.Dict;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/serviceCenter")
public class ServiceCenter {
	@Autowired
	private DictService dictService;

	/**
	 * 获取数据字典
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDict", method = RequestMethod.POST)
	public ResultDTO getDict(@UserInfoParam UserInfo userInfo, @RequestBody Dict params) {
		return dictService.getDict(userInfo, params);
	}
	
	/**
	 * 修改数据字典
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editDict", method = RequestMethod.POST)
	public ResultDTO editDict(@UserInfoParam UserInfo userInfo, @RequestBody Dict params) {
		return dictService.editDict(userInfo, params);
	}

}
