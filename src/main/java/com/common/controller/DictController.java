package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.common.dto.DictDto;
import com.common.service.DictService;
import com.gubang.config.UserInfoParam;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/dict")
public class DictController {
	@Autowired
	private DictService dictService;

	/**
	 * 获取数据字典
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDictList", method = RequestMethod.POST)
	public ResultDTO getDictList(@UserInfoParam UserInfo userInfo, @RequestBody DictDto params) {
		return dictService.getDictList(userInfo, params);
	}
}
