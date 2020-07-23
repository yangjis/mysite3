package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	GalleryDao galleryDao;
	
	public List<GalleryVo> list() {
		List<GalleryVo> list = galleryDao.list();
		for(GalleryVo vo: list) {
		}
		return list;
	}
	
	public void insert(GalleryVo galleryVo, MultipartFile file) {
		///// 데이터 추출 //////////////////////////////////
		String saveDir = "C:\\javaStudy\\upload";
			
		//원파일이름
		String orgName =  file.getOriginalFilename();
		System.out.println("orgName:" + orgName);
			
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		//저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;    // 영문과 숫자가 겹친 임의의 문자를 줌
		System.out.println("saveName:" + saveName);
			
		//파일경로
		String filePath = saveDir + "\\" + saveName;
			
		//파일사이즈
		long fileSize = file.getSize();
			
			
		///// 파일 서버에 복사 ///////////////////////////////////////////////
		try{
			byte[]  fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
				
			bout.write(fileData);
			bout.close();
				
		} catch (IOException e) {
				e.printStackTrace();
		}
		galleryVo.setSaveName(saveName);
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		galleryVo.setOrgName(orgName);
		galleryDao.insert(galleryVo);
	}
	
	public GalleryVo getSaveName(int no) {
		GalleryVo vo = galleryDao.getSaveName(no);
		return vo;
	}
	
	public void delGallery(GalleryVo galleryVo) {
		System.out.println(galleryVo.toString());
		int count = galleryDao.delGallery(galleryVo);
		System.out.println("서비스 삭제: " + count);
	}
	
}
