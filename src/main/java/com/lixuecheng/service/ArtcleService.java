package com.lixuecheng.service;

import java.util.List;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Category;
import com.lixuecheng.entity.Channel;
import com.lixuecheng.entity.Comment;
import com.lixuecheng.entity.Commpan;
import com.lixuecheng.entity.Complain;
import com.lixuecheng.entity.Condtion;
import com.lixuecheng.entity.Slide;

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

	/**
	 * 修改文章
	 * @param article
	 * @param id
	 * @return
	 */
	int update(Acticle article, Integer id);

	/**
	 * 获取文章列表 管理员
	 * @param status
	 * @param pageNum
	 * @return
	 */
	PageInfo<Acticle> list(int status, int pageNum);
	
	
	/**
	 * 获取文章的简要信息   常常
	 * @param id
	 * @return
	 */
	Acticle getInfoById(int id);

	/**
	 * 设置热门
	 * @param id
	 * @param status
	 * @return
	 */
	int setHot(int id, int status);

	/**
	 * 设置审核状态
	 * @param id
	 * @param status
	 * @return
	 */
	int setCheckStatus(int id, int status);

	
	

	/**
	 * 获取热门文章
	 * @param page
	 * @return
	 */
	PageInfo<Acticle> hotList(int page);

	/**
	 * 获取最新文章
	 * @return
	 */
	List<Acticle> lastList();

	
	/**
	 * 获取轮播图
	 * @return
	 */
	List<Slide> getSlides();

	/**
	 * 获取栏目下的文章
	 * @param channleId
	 * @param catId
	 * @param page
	 * @return
	 */
	PageInfo<Acticle> getArticles(int channleId, int catId, int page);

	/**
	 * 获取栏目下的分类
	 * @param channleId
	 * @return
	 */
	List<Category> getCategorByChannelId(int channleId);

	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	int addComment(Comment comment);

	/**
	 * 根据文章id获取评论
	 * @param id
	 * @param page
	 * @return
	 */
	PageInfo<Comment> getComments(int artcleId, int page);

	int addComplian(Complain compain);

	
	//
	PageInfo<Complain> getComplains(int articleId, int page);

	
	//
	int addCopan(Commpan commpan);

	//查询投诉
	List<Commpan> listTs(Condtion con);

	 List<Commpan> comList(int id);

	List<Commpan> tsNum1();

	//查询投诉数量
	List<Commpan> listPan();

	List<Commpan> tsNum2();

	
	

	
	
	
	
}
