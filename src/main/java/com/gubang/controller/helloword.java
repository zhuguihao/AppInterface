package com.gubang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloword {
	
	@RequestMapping
	public String helloWord() {
		return "helloWord";
	}
	
}
