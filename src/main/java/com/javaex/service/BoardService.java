package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	public List<BoardVo> list(int start, int end) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		
		List<BoardVo> bList = boardDao.list(pageMap);
		return bList;
	}
	
	public BoardVo getBoard(int no) {
		return boardDao.getBoard(no);
	}
	
	public int updateBoard(BoardVo vo) {
		boardDao.updateBoard(vo);
		return vo.getNo();
	}
	
	public int insertBoard(BoardVo boardVo) {
		return boardDao.insertBoard(boardVo);
	}
	
	public int deleteBoard(BoardVo boardVo) {
		return boardDao.deleteBoard(boardVo);
	}
	
	public int allPage() {
		return boardDao.allPage();
	}
	public List<BoardVo> search(int start, int end, String keyword) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		pageMap.put("keyword", keyword);
		
		List<BoardVo> bList = boardDao.search(pageMap);
		return bList;
	}
	
}
