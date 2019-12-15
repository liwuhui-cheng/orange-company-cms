package com.lixuecheng.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Category;
import com.lixuecheng.entity.Channel;

/**
 * 
 * @author orang
 *
 */
public interface ArtcleService {

	/**
	 * 根据用户id列出文章
	 * @param id
	 * @param pageNum
	 * @return
	 */
	PageInfo<Acticle> listByUser(Integer id, int pageNum);

	/**
	 * 获取所有的栏目
	 * @return
	 */
	List<Channel> getChannel();

	/**
	 * 获取所有的分类
	 * @param cid
	 * @return
	 */
	List<Category> getCategorisByCid(int  cid);

	/**
	 * 发布文章
	 * @param article
	 * @return 
	 */
	int add(Acticle article);

	/**
	 * 回显
	 * @param id
	 * @return
	 */
	Acticle getById(int id);
}
