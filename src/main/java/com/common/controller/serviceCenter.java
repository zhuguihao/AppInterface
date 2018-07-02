package com.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceCenter")
public class serviceCenter {
	
	@RequestMapping
	public String helloWord() {
		return "12321321321";
	}
	
}
