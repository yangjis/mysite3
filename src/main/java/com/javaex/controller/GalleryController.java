package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	@Autowired
	GalleryService gallerService;
	
	@RequestMapping("list")
	public String list() {
		System.out.println("gallery/list");
		return "gallery/list";
	}
	
	@RequestMapping("delGallery")
	public String delGallery(@ModelAttribute GalleryVo galleryVo) {
		System.out.println("gallery/delGallery");
		
		gallerService.delGallery(galleryVo);
		
		return "redirect:/gallery/list";
	}
	

}
