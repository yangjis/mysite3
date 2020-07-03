package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {
	
	@RequestMapping("main")
	public String main() {
		return "main/index";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}
