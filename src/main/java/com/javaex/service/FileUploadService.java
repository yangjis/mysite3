package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	public String restore(MultipartFile file) {
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
		
		
		//파일 --> 필요한 정보추출 --> DB에 저장
		//no:
		//orgName:LeeHoRi.jpg
		//saveName:1595320436848f8e03aa2-ff27-42ac-854c-5fb99dc828a6.jpg
		//filePath:c\\javaStudy\\upload\\1595320436848f8e03aa2-ff27-42ac-854c-5fb99dc828a6.jpg
		//fileSize: 21865
		
		return saveName;
	}

}