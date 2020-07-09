package com.javaex.controller;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/deleteForm")
	public String deleteForm(@RequestParam("no") int no, Model model) {
		model.addAttribute("no", no);
		return "guestBook/deleteForm";
	}
	
	@RequestMapping("/deleteAction")
	public String deleteAction(@ModelAttribute GuestBookVo vo) {
		guestService.delete(vo);
		
		return "redirect:/guestBook/addList";
	}
}
