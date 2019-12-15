package com.lixuecheng.common;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

public class CmsUtils {
    
	/***
	 * 加盐加密
	 * @param src  眀文
	 * @param salt  盐
	 * @return
	 */
	
	public   static   String  encry(String  src,String salt) {
		
		byte[] md5 = DigestUtils.md5(src+src+salt);
		
	     try {
		 String  pwd =new  String(md5,"utf-8");
		 
		 return  pwd;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return  src+src+salt;
		}
	}
	
}
