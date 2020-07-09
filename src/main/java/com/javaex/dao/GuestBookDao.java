package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<GuestBookVo> addList(){
		System.out.println("guestDao.addList");
		List<GuestBookVo> gList = sqlSession.selectList("guest.addList");
		
		return gList;
	}
	
	public int addGuestBook(GuestBookVo vo) {
		System.out.println("guestDao.addGuestBook");
		
		return sqlSession.insert("guest.addGuestBook", vo);
	}
	
	public int delete(GuestBookVo vo) {
		return sqlSession.delete("guest.delete", vo);
	}
}
