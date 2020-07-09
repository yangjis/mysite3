package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVo> list(){
		List<BoardVo> bList = sqlSession.selectList("board.list");
		
		return bList;
	}
}
