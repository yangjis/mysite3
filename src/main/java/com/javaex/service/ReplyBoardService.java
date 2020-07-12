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
		pageMap.put("keyword", "");
		
		List<ReplyBoardVo> bList = boardDao.search(pageMap);

		return bList;
	}

	public List<ReplyBoardVo> search(int start, int end, String keyword) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		pageMap.put("keyword", keyword);
		
		List<ReplyBoardVo> bList = boardDao.search(pageMap);
		return bList;
	}
	
	public int allPage() {
		String keyword = "";
		return boardDao.keywordAllPage(keyword);
	}
	
	public int keywordAllPage(String keyword) {
		return boardDao.keywordAllPage(keyword);
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
		
		  if(boardDao.groupNoDelY(boardVo.getGroup_no()) == 1) {
			  //같은 그룹의 del이 자기자신 빼고 모두 y일 경우 같은 그룹 다 삭제;
			  return boardDao.groupAllDel(boardVo.getGroup_no());
			  
		  }else if(boardDao.childCount(boardVo.getNo()) == 0 ) {
			  //자식이 없을 경우 삭제;
			  return  boardDao.deleteBoard(boardVo); 
			  
		  }else {
			  //자식이 있음;
			  return boardDao.delUpdate(boardVo);
		  }
	}
	
	public int replyInsert(ReplyBoardVo vo) {
		vo.setOrder_no(vo.getOrder_no() + 1);
		vo.setDepth(vo.getDepth() + 1);
		
		return boardDao.replyInsert(vo);
	}
}
