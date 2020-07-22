package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	@RequestMapping("list")
	public String list() {
		System.out.println("gallery/list");
		return "gallery/list";
	}
	

}
