package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<GalleryVo> list(){
		return sqlSession.selectList("gallery.list");
	}
	
	public void insert(GalleryVo galleryVo) {
		sqlSession.insert("gallery.insert",galleryVo);
	}
	
	public GalleryVo getSaveName(int no) {
		return sqlSession.selectOne("gallery.getSaveName", no);
	}
	
	public int delGallery(GalleryVo galleryVo) {
		return sqlSession.delete("gallery.delete", galleryVo);
	}
}
