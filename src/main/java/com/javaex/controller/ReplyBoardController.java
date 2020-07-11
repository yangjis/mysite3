package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.ReplyBoardService;
import com.javaex.util.Paging;
import com.javaex.vo.ReplyBoardVo;

import oracle.jdbc.proxy.annotation.GetDelegate;

@Controller
@RequestMapping("board")
public class ReplyBoardController {
	
	@Autowired
	ReplyBoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam("pg") int pg) {
		
		Paging pgVo = new Paging(10, 10, boardService.allPage(),pg);
		
		List<ReplyBoardVo> bList = boardService.list(pgVo.getWriting_Start(), pgVo.getWriting_End());
		System.out.println("controller " + bList.toString());
		
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
	public String modifyAction(@ModelAttribute ReplyBoardVo boardVo, Model model) {
		
		model.addAttribute("no", boardService.updateBoard(boardVo));
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@RequestMapping("/writeAction")
	public String writeAction(@ModelAttribute ReplyBoardVo boardVo) {
		boardService.insertBoard(boardVo);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute ReplyBoardVo boardVo) {
		boardService.deleteBoard(boardVo);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/search") 
	 public String search(@RequestParam("keyword")String keyword,
			  			   @RequestParam("pg") int pg, 
			  			   Model model) { 
		Paging pgVo = new Paging(5, 5, boardService.keywordAllPage(keyword),pg);
		  
		List<ReplyBoardVo> bList = boardService.search(pgVo.getWriting_Start(), pgVo.getWriting_End(),keyword);
		  
		model.addAttribute("pg", pgVo); 
		model.addAttribute("bList", bList); 
		return "board/list"; 
	}
		
	@RequestMapping("/replyWriteForm") 
	public String replyWriteForm(@RequestParam("group_no")int group_no,
								 @RequestParam("boardType") String boardType, 
								 Model model) {
	  
	  Map<String, Object> noTypeMap= new HashMap<String, Object>();
	  noTypeMap.put("group_no", group_no); 
	  noTypeMap.put("boardType", boardType);
	  model.addAttribute("board", noTypeMap);
	  
	  return "board/writeForm"; 
	}
	  
	@RequestMapping("replyWriteAction") 
	public String replyWriteAction(@ModelAttribute ReplyBoardVo replyVo) {
		boardService.replyInsert(replyVo); 
		return "redirect:/board/list?pg=1"; 
	 }
	 
	
}
