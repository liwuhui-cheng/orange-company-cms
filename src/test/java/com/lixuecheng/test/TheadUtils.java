package com.lixuecheng.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.lixuecheng.service.ArtcleService;

public class TheadUtils {

	@Autowired
	private   ArtcleService  artcleService;
	
	public static void main(String[] args) {
		
		Thread t1 =new  Thread() {
		
			@Override
			public void run() {
				
				
			}
			
		};
		Thread t2 =new  Thread() {
			
			@Override
			public void run() {
				
				
			}
			
		};
		Thread t3 =new  Thread() {
			
			@Override
			public void run() {
				
				
			}
			
		};
		
		
		
	}
}
