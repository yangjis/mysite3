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
		List<GuestBookVo> gList = guestDao.addList();
		
		return gList;
	}
	
	public int addGuestBook(GuestBookVo vo) {
		
		return guestDao.addGuestBook(vo);
	}
	
	public int delete(GuestBookVo vo) {
		return guestDao.delete(vo);
	}
	
	public GuestBookVo ajaxAddGuest(GuestBookVo vo) {
		guestDao.ajaxAddGuest(vo);
		return guestDao.selectByNo(vo.getNo());
	}
}
