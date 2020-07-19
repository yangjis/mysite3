package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("api/guestbook")
public class ApiGuestbookController {
	@Autowired
	private GuestBookService guestBookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestBookVo> list(){
		System.out.println("ajaxList");
		List<GuestBookVo> list = guestBookService.addList();
		return list;
	}
}