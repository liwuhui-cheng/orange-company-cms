package com.lixuecheng.service;

import javax.validation.Valid;

import com.lixuecheng.entity.User;

public interface UserService {

	//唯一验证
	User getUserByUsername(String username);
    //注册
	int register(@Valid User user);
	//登陆
	User login(User user);
	//删除文章
	int deleteArticle(int id);

}
