package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
	FileUploadService fileUploadService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GalleryVo> list(){
		System.out.println("api/gallery/list");
		List<GalleryVo> list = galleryService.list();
		
		return list;
	}
	
	@RequestMapping(value = "/addGallery", method=RequestMethod.GET)
	public String addGallery(@RequestParam("file") MultipartFile file,
						  @RequestParam("comments") String comments,
						  Model model) {
		System.out.println("api/gallery/addGallery");
		
		String saveName = fileUploadService.restore(file);
		model.addAttribute("saveName", saveName);
		
		return "redirect:gallery/list";
	}
	

}
