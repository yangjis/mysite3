package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.util.Paging;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam("pg") int pg) {
		
		Paging pgVo = new Paging(5, 5, boardService.allPage(),pg);
		
		List<BoardVo> bList = boardService.list(pgVo.getWriting_Start(), pgVo.getWriting_End());
		
		model.addAttribute("pg", pgVo);
		model.addAttribute("bList", bList);
		return "board/list";
	}
	
	@RequestMapping("/read")
	public String read(@RequestParam("no") int no, Model model) {
		
		model.addAttribute("getBoard", boardService.getBoard(no));
		return "board/read";
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(@RequestParam("no") int no, Model model) {
		model.addAttribute("getBoard", boardService.getBoard(no));
		return "board/modifyForm";
	}
	
	@RequestMapping("/modifyAction")
	public String modifyAction(@ModelAttribute BoardVo boardVo, Model model) {
		
		model.addAttribute("no", boardService.updateBoard(boardVo));
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@RequestMapping("/writeAction")
	public String writeAction(@ModelAttribute BoardVo boardVo) {
		boardService.insertBoard(boardVo);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute BoardVo boardVo) {
		boardService.deleteBoard(boardVo);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("keyword") String keyword,
						 @RequestParam("pg") int pg,
						 Model model) {
		Paging pgVo = new Paging(5, 5, boardService.keywordAllPage(keyword),pg);
		
		List<BoardVo> bList = boardService.search(pgVo.getWriting_Start(), pgVo.getWriting_End(),keyword);
		
		model.addAttribute("pg", pgVo);
		model.addAttribute("bList", bList);
		return "board/list";
	}
}
