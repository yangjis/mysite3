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
	
	public UserVo getUser(UserVo userVo) {
		System.out.println("userDao.login");
		
		return sqlSession.selectOne("user.getUser", userVo);
	}

}
