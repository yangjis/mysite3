package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<BoardVo> bList = boardService.list();
		model.addAttribute("bList", bList);
		return "board/list";
	}
}
