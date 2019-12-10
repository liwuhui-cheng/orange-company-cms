package com.lixuecheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lixuecheng.service.MyService;

@Controller
@RequestMapping("user")
public class MyController {

	 @Autowired
	 private   MyService   service;
	 
	 
	 @RequestMapping("home")
	 public   String   home(){		 
		 
		 return  "user/home";		 
	 }
}
