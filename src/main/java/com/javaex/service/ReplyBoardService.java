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
		for(int i = 0; i < bList.size(); i++) {
			//그룹 번호가 같은 게시글 중 del값이 모두 y면 모두 삭제.
			if("y".equals(bList.get(i).getDel()) && boardDao.groupNoDelY(bList.get(i).getGroup_no()) == 0 ) {
				boardDao.deleteBoard(bList.get(i));
			}
		}		
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
		
		  if(boardDao.group_noCount(boardVo) == 1 || boardDao.groupNoDelY(boardVo.getGroup_no()) == 0) {	  
			  return  boardDao.deleteBoard(boardVo); 
		  }else {
			  return boardDao.delUpdate(boardVo);
		  }
	}
	
	public int replyInsert(ReplyBoardVo vo) {
		int lastOrderNo = boardDao.lastOrderNo(vo.getGroup_no());
		vo.setOrder_no(vo.getOrder_no() + 1);
		vo.setDepth(vo.getDepth() + 1);
		
		return boardDao.replyInsert(vo);
	}
}
