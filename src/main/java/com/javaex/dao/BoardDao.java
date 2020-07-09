package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVo> list(Map pageMap){
		List<BoardVo> bList = sqlSession.selectList("board.list", pageMap);
		
		return bList;
	}
	
	public BoardVo getBoard(int no) {
		return sqlSession.selectOne("board.getBoard", no);
	}
	
	public int updateBoard(BoardVo boardVo) {
		return sqlSession.update("board.updateBoard",boardVo);
	}
	
	public int insertBoard(BoardVo boardVo) {
		return sqlSession.insert("board.insertBoard", boardVo);
	}
	
	public int deleteBoard(BoardVo boardVo) {
		return sqlSession.delete("board.deleteBoard", boardVo);
	}
	
	public int allPage() {
		return sqlSession.selectOne("board.allPage");
	}
	
	public List<BoardVo> search(Map pageMap){
		List<BoardVo> bList = sqlSession.selectList("board.search", pageMap);
		
		return bList;
	}
	public int keywordAllPage(String keyword) {
		return sqlSession.selectOne("board.keywordAllPage", keyword);
	}
}
