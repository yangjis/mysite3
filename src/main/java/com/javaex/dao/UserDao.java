package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	SqlSession sqlSession;

	public int insert(UserVo userVo) {
		System.out.println("UserDao/join");
		
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("userDao.login");
		UserVo vo =sqlSession.selectOne("user.login", userVo);
		System.out.println(vo.toString());
		
		return vo;
	}
	
	public int updateUser(UserVo userVo) {
		System.out.println("userDao.updateUser");
		
		return sqlSession.update("user.updateUser", userVo);
	}
	
	public UserVo getUser(int no) {
		System.out.println("userDao.getUser");
		
		return sqlSession.selectOne("user.getUser", no);
	}

}
