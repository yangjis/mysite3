package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;
import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("api/gallery")
public class ApiGalleryController {
	
	@Autowired
	GalleryService galleryService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GalleryVo> list(){
		System.out.println("api/gallery/list");
		List<GalleryVo> list = galleryService.list();
		return list;
	}
	
	@RequestMapping("/addGallery")
	public String addGallery(@RequestParam("file") MultipartFile file,
						  @ModelAttribute GalleryVo galleryVo,
						  Model model) {
		System.out.println("api/gallery/addGallery");
		
		galleryService.insert(galleryVo, file);
		return "redirect:/gallery/list";
	}
	
	@ResponseBody
	@RequestMapping("/getSaveName")
	public GalleryVo getSaveName(@RequestBody int no) {
		GalleryVo vo = galleryService.getSaveName(no); 
		
		return vo;
	}
	
}
