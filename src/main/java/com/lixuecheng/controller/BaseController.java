package com.lixuecheng.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.lixuecheng.test.FileUtils;

public class BaseController {

	@Value("${upload.path}")
	String   picRootPath;
	
	@Value("pic.path")
	String picUrl;
	 /**、
	  * 判断文章目录是否存在以及重新命名
	  * @param file
	  * @return
	  * @throws IllegalStateException
	  * @throws IOException
	  */
	 protected   String   processFile(MultipartFile  file ) throws IllegalStateException, IOException {
		 //判断当前目录是否存在
		 //picRootPath+""
		 
		 SimpleDateFormat sdf = new  SimpleDateFormat("yyyyMMdd");
		 
		 //当前日期
		 String format = sdf.format(new Date());
		 //图片存放的地址
		 File file2 = new  File(picRootPath+"/"+format);
		  //判断是否存在
		 if(!file2.exists()) {
			 //不存在创建
			 file2.mkdirs();
		 }
		 //新文件名称
		 String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
		 //随机生成文件名
	     String   fileName= UUID.randomUUID().toString() +suffixName;
		
	     //文件另存
	    file.transferTo(new File(picRootPath+"/"+format+"/"+fileName));
		 
	    
	    return  picUrl+ format+"/"+fileName;
	 }
	 
}
