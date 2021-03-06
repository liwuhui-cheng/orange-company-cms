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
import com.lixuecheng.entity.Comment;
import com.lixuecheng.entity.Commpan;
import com.lixuecheng.entity.Complain;
import com.lixuecheng.entity.Condtion;
import com.lixuecheng.entity.Slide;
import com.lixuecheng.mapper.ArtcleMapper;
import com.lixuecheng.mapper.ArticleRep;
import com.lixuecheng.mapper.SlideMapper;
import com.lixuecheng.service.ArtcleService;

/**
 * 文章查询的业务层
 * 
 * @author orang
 *
 */

@Service
public class ArtcleServiceImpl implements ArtcleService {

	@Autowired
	ArtcleMapper artcleMapper;

	@Autowired
	SlideMapper slideMapper;

	@Autowired
	ArticleRep articleRep;

	@Override
	public PageInfo<Acticle> listByUser(Integer id, int pageNum) {

		PageHelper.startPage(pageNum, CmsContant.PAGE_SIZE);

		PageInfo<Acticle> pageInfo = new PageInfo<Acticle>(artcleMapper.listByUser(id));

		return pageInfo;
	}

	// 获取所有的栏目
	@Override
	public List<Channel> getChannel() {

		return artcleMapper.getAllChannel();
	}

	@Override
	public List<Category> getCategorisByCid(int cid) {

		return artcleMapper.getCategorisByCid(cid);
	}

	// 发布文章
	@Override
	public int add(Acticle article) {

		return artcleMapper.add(article);
	}

	/**
	 * 回显
	 */
	@Override
	public Acticle getById(int id) {

		return artcleMapper.getById(id);
	}

	/**
	 * 修改文章
	 */
	@Override
	public int update(Acticle article, Integer id) {

		Acticle byId = this.getById(article.getId());

		if (byId.getUserId() != id) {
			// todo 如果不是自己的抛異常打印
		}

		return artcleMapper.update(article);
	}

	/**
	 * 文章列表 ，管理员
	 */
	@Override
	public PageInfo<Acticle> list(int status, int pageNum) {

		PageHelper.startPage(pageNum, CmsContant.PAGE_SIZE);

		return new PageInfo<Acticle>(artcleMapper.list(status));
	}

	/**
	 * 获取状态参数值是否合法
	 */
	@Override
	public Acticle getInfoById(int id) {

		return artcleMapper.getInfoById(id);
	}

	/**
	 * 设置审核状态
	 */

	@Override
	public int setCheckStatus(int id, int status) {

		return artcleMapper.setCheckStatus(id, status);
	}

	/**
	 * 设置热门
	 */
	@Override
	public int setHot(int id, int status) {

		return artcleMapper.setHot(id, status);
	}

	/**
	 * 获取最热文章
	 */
	@Override
	public PageInfo<Acticle> hotList(int page) {

		PageHelper.startPage(page, CmsContant.PAGE_SIZE);

		return new PageInfo<>(artcleMapper.hotList(page));
	}

	/**
	 * 获取最新文章
	 */
	@Override
	public List<Acticle> lastList() {

		return artcleMapper.lastList(CmsContant.PAGE_SIZE);
	}

	@Override
	public List<Slide> getSlides() {

		return slideMapper.list();
	}

	// 获取栏目下的文章
	@Override
	public PageInfo<Acticle> getArticles(int channleId, int catId, int page) {

		PageHelper.startPage(page, CmsContant.PAGE_SIZE);

		return new PageInfo<Acticle>(artcleMapper.getArticles(channleId, catId));
	}

	// 获取栏目下的分类
	@Override
	public List<Category> getCategorByChannelId(int channleId) {

		return artcleMapper.getCategorByChannelId(channleId);
	}

	// 发表评论
	@Override
	public int addComment(Comment comment) {

		int result = artcleMapper.addComment(comment);

		if (result > 0) {
			// 文章评论数目自增
			artcleMapper.increaseCommentCnt(comment.getArticleId());
		}

		return result;
	}

	@Override
	public PageInfo<Comment> getComments(int artcleId, int page) {

		PageHelper.startPage(page, CmsContant.PAGE_SIZE);

		return new PageInfo<Comment>(artcleMapper.getComments(artcleId));

	}

	//
	@Override
	public int addComplian(Complain compain) {

		//
		int i = artcleMapper.addCoplain(compain);
		//
		if (i > 0) {
			artcleMapper.increaseComplainCat(compain.getArticleId());
		}

		return i;
	}

	@Override
	public PageInfo<Complain> getComplains(int articleId, int page) {

		PageHelper.startPage(page, CmsContant.PAGE_SIZE);

		return new PageInfo<Complain>(artcleMapper.getComplains(articleId));
	}

	@Override
	public int addCopan(Commpan commpan) {

		int i = artcleMapper.addCopan(commpan);

		if (i > 0) {
			artcleMapper.increaseComplainCat(commpan.getArticle_id());
		}
		return i;
	}

	@Override
	public List<Commpan> listTs(Condtion con) {
		List<Commpan> list = artcleMapper.listTs(con);

		return list;
	}

	@Override
	public List<Commpan> comList(int id) {

		return artcleMapper.comList(id);
	}

	@Override
	public List<Commpan> tsNum1() {
		// TODO Auto-generated method stub
		return artcleMapper.tsNum1();

	}

	@Override
	public List<Commpan> listPan() {

		return artcleMapper.listPan();
	}

	@Override
	public List<Commpan> tsNum2() {

		return artcleMapper.tsNum2();
	}

	@Override
	public int[] selectCategoryId(int random) {

		return artcleMapper.selectCategoryId(random);
	}

	@Override
	public List<Acticle> esFind(String estitle) {

		List<Acticle> esfind = articleRep.findByTitle(estitle);

		return esfind;
	}

}
