package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.ReplyBoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.ReplyBoardVo;

@Service
public class ReplyBoardService {
	
	@Autowired
	ReplyBoardDao boardDao;
	
	public List<ReplyBoardVo> list(int start, int end) {
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		
		List<ReplyBoardVo> bList = boardDao.list(pageMap);
		
		for(int i = 0; i < bList.size(); i++) {
			if("y".equals(bList.get(i).getDel())) {
				bList.get(i).setTitle("게시글이 삭제되었습니다.");
			}
		}
		
		System.out.println(bList.toString());
		return bList;
	}
	
	public int allPage() {
		return boardDao.allPage();
	}
	
	public ReplyBoardVo getBoard(int no) {
		boardDao.hitUpdate(no);
		return boardDao.getBoard(no);
	}
	
	public int updateBoard(ReplyBoardVo vo) {
		boardDao.updateBoard(vo);
		return vo.getNo();
	}
	
	public int insertBoard(ReplyBoardVo boardVo) {
		return boardDao.insertBoard(boardVo);
	}
	
	public int deleteBoard(ReplyBoardVo boardVo) {
		int count;
		
		  if(boardDao.group_noCount(boardVo) < 2) { 
			  count = boardDao.deleteBoard(boardVo); 
		  }else {
			  count = boardDao.delUpdate(boardVo);
		  }
		 
		
		System.out.println(boardVo.toString());
		return 0;
	}
	
	public List<ReplyBoardVo> search(int start, int end, String keyword) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		pageMap.put("keyword", keyword);
		
		List<ReplyBoardVo> bList = boardDao.search(pageMap);
		return bList;
	}
	
	public int keywordAllPage(String keyword) {
		return boardDao.keywordAllPage(keyword);
	}
	
	public int replyInsert(ReplyBoardVo vo) {
		vo.setOrder_no(vo.getOrder_no() + 1);
		vo.setDepth(vo.getDepth() + 1);
		
		return boardDao.replyInsert(vo);
	}
}
