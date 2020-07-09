package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	GuestBookDao guestDao;
	
	public List<GuestBookVo> addList(){
		System.out.println("guestService.addList");
		List<GuestBookVo> gList = guestDao.addList();
		
		return gList;
	}
}
