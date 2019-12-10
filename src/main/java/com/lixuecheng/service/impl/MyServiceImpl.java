package com.lixuecheng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lixuecheng.mapper.MyMapper;
import com.lixuecheng.service.MyService;

@Service
public class MyServiceImpl implements MyService {

	 @Autowired
	 private   MyMapper   mapper;
}
