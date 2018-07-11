package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.common.service.DictService;
import com.gubang.util.ResultDTO;

@RestController
@RequestMapping("/serviceCenter")
public class ServiceCenter {
	@Autowired
	private DictService dictService;

	/**
	 * 获取数据字典
	 * @return
	 */
	@RequestMapping(value="/getDict",method = RequestMethod.POST)
	public ResultDTO getDict() {
		return dictService.getDict();
	}
	
}
