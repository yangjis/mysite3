package com.javaex.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("guestBook")
public class GuestBookController {
	
	@Autowired
	GuestBookService gusetService;
	
	@RequestMapping("addList")
	public String addList(Model model) {
		System.out.println("guestController.addList");
		List<GuestBookVo> gList = gusetService.addList();
		
		model.addAttribute("gList", gList);
		
		return "guestBook/addList";
	}
}
