package com.lixuecheng.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lixuecheng.common.CmsContant;
import com.lixuecheng.common.CmsUtils;
import com.lixuecheng.entity.User;
import com.lixuecheng.mapper.UserMapper;
import com.lixuecheng.service.UserService;

@Service
public class MyServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	/**
	 * 验证唯一
	 */
	@Override
	public User getUserByUsername(String username) {

		return mapper.getUserByUserName(username);
	}

	/**
	 * 注册
	 */
	@Override
	public int register(@Valid User user) {

		//计算密文
		String encry = CmsUtils.encry(user.getPassword(), user.getUsername());
		user.setPassword(encry);
		return mapper.register(user);
	}

	@Override
	public User login(User user) {
		
		user.setPassword(CmsUtils.encry(user.getPassword(), user.getUsername()));
		
		
		return mapper.findByPwd(user);
	}

	/**
	 * 删除文章，根据id
	 */
	@Override
	public int deleteArticle(int id) {
		
		return mapper.updateStatus(id,CmsContant.ARTICLE_STATUS_DELETE);
	}
}
