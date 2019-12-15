package com.lixuecheng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixuecheng.common.CmsContant;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Category;
import com.lixuecheng.entity.Channel;
import com.lixuecheng.mapper.ArtcleMapper;
import com.lixuecheng.service.ArtcleService;
/**
 * 文章查询的业务层
 * @author orang
 *
 */

@Service
public class ArtcleServiceImpl implements ArtcleService {

	@Autowired
	ArtcleMapper  artcleMapper;
	
	
	@Override
	public PageInfo<Acticle> listByUser(Integer id, int pageNum) {
		
		PageHelper.startPage(pageNum,CmsContant.PAGE_SIZE);
		
		PageInfo<Acticle> pageInfo = new  PageInfo<Acticle>(artcleMapper.listByUser(id));
		
		
		return pageInfo;
	}


	//获取所有的栏目
	@Override
	public List<Channel> getChannel() {
		
		return  artcleMapper.getAllChannel();
	}


	@Override
	public List<Category> getCategorisByCid(int cid) {
		
		return   artcleMapper.getCategorisByCid(cid);
	}


	//发布文章
	@Override
	public int add(Acticle article) {
		
	  return	artcleMapper.add(article);
	}


	/**
	 * 回显
	 */
	@Override
	public Acticle getById(int id) {
		
		return null;
	}

	
}
