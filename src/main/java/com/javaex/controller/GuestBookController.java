package com.javaex.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("guestBook")
public class GuestBookController {
	
	@Autowired
	GuestBookService guestService;
	
	@RequestMapping("/addList")
	public String addList(Model model) {
		System.out.println("guestController.addList");
		List<GuestBookVo> gList = guestService.addList();
		
		model.addAttribute("gList", gList);
		
		return "guestBook/addList";
	}
	
	@RequestMapping("/addGuestBook")
	public String addGuestBook(@ModelAttribute GuestBookVo vo) {
		System.out.println("gController.addGuestBook");
		System.out.println(vo.toString());
		
		guestService.addGuestBook(vo);
		
		return "redirect:/guestBook/addList";
	}
}
