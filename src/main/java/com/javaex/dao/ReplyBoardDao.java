package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;
import com.javaex.vo.ReplyBoardVo;

@Repository
public class ReplyBoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public ReplyBoardVo getBoard(int no) {
		ReplyBoardVo vo = sqlSession.selectOne("replyBoard.getBoard", no);
		
		return vo;
	}
	
	public int updateBoard(ReplyBoardVo boardVo) {
		return sqlSession.update("replyBoard.updateBoard",boardVo);
	}
	
	public int hitUpdate(int no) {
		return sqlSession.update("replyBoard.hitUpdate", no);
	}
	
	public int insertBoard(ReplyBoardVo boardVo) {
		return sqlSession.insert("replyBoard.insertBoard", boardVo);
	}
	
	public int deleteBoard(ReplyBoardVo boardVo) {
		return sqlSession.delete("replyBoard.deleteBoard", boardVo);
	}
	
	public List<ReplyBoardVo> search(Map pageMap){
		List<ReplyBoardVo> bList = sqlSession.selectList("replyBoard.search", pageMap);
		
		return bList;
	}
	public int keywordAllPage(String keyword) {
		return sqlSession.selectOne("replyBoard.keywordAllPage", keyword);
	}
	
	public int replyInsert(ReplyBoardVo vo) {
		return sqlSession.insert("replyBoard.replyInsert", vo);
	}
	
	public int group_noCount(ReplyBoardVo vo) {
		return sqlSession.selectOne("replyBoard.group_noCount",vo);
	}
	
	public int delUpdate(ReplyBoardVo vo) {
		return sqlSession.update("replyBoard.delUpdate", vo);
	}
	
	public int groupNoDelY(int group_no) {
		return sqlSession.selectOne("replyBoard.groupNoDelY", group_no);
	}
	
	public int lastOrderNo(int group_no) {
		return sqlSession.selectOne("replyBoard.lastOrderNo", group_no);
	}
}
