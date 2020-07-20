package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		List<GuestBookVo> list = guestBookService.addList();
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public GuestBookVo write(@RequestBody GuestBookVo guestBookVo) {
		System.out.println(guestBookVo.toString());
		
		GuestBookVo vo = guestBookService.ajaxAddGuest(guestBookVo);
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@ModelAttribute GuestBookVo guestBookVo) {
		int count = guestBookService.delete(guestBookVo);
		return count;
	}
}