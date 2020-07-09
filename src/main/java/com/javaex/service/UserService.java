package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int join(UserVo vo) {
		System.out.println("UserService.insert");
		
		int count = userDao.insert(vo);
		return count;
	}
	
	public UserVo login(UserVo vo ) {
		System.out.println("UserService.getUser");
		
		return userDao.login(vo);
	}
	
	public UserVo updateUser(UserVo vo) {
		System.out.println("UserService.update");
		
		userDao.updateUser(vo);
		
		return userDao.getUser(vo.getNo());
	}
	public UserVo getUser(int no) {
		System.out.println("userService.getUser");
		return userDao.getUser(no);
	}

}
