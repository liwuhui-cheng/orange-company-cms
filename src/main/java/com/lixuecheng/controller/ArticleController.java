package com.lixuecheng.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.lixuecheng.common.CmsContant;
import com.lixuecheng.common.CmsError;
import com.lixuecheng.common.CmsMessage;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Comment;
import com.lixuecheng.entity.Complain;
import com.lixuecheng.entity.User;
import com.lixuecheng.service.ArtcleService;
import com.lixuecheng.test.FileUtils;

@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {

	@Autowired
	ArtcleService artcleService;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("getDetail")
	@ResponseBody
	public CmsMessage getDetail(int id) {
		if (id <= 0) {

		}

		// 获取文章详情
		Acticle acticle = artcleService.getById(id);
		// 不存在
		if (acticle == null) {

			return new CmsMessage(CmsError.NOT_EXIST, "文章不存在", null);
		}
		// 文章返回数据
		return new CmsMessage(CmsError.SUCCESS, "", acticle);

	}

	/**
	 * 获取最新文章
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("detail")
	public String detail(HttpServletRequest request, int id) {

		Acticle acticle = artcleService.getById(id);

		request.setAttribute("acticle", acticle);

		return "detail";
	}

	/**
	 * 评论
	 * 
	 * @param request
	 * @param articleId
	 * @param content
	 * @return
	 */
	@RequestMapping("postcomment")
	@ResponseBody
	public CmsMessage postcomment(HttpServletRequest request, int articleId, String content) {

		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if (loginUser == null) {

			return new CmsMessage(CmsError.NOT_LOGIN, "没有登陆！", null);
		}

		Comment comment = new Comment();
		comment.setUserId(loginUser.getId());
		comment.setContent(content);
		comment.setArticleId(articleId);

		int result = artcleService.addComment(comment);

		if (result > 0) {

			return new CmsMessage(CmsError.SUCCESS, "成功发表", null);
		}
		return new CmsMessage(CmsError.FAILED_UPDATE_DB, "评论失败！", null);
	}

	@RequestMapping("comments")
	public String comments(HttpServletRequest request, int id, int page) {

		PageInfo<Comment> commentPage = artcleService.getComments(id, page);

		System.out.println(commentPage + "111111111111111111");

		request.setAttribute("articlePage", commentPage);

		return "comments";
	}

	/**
	 * 跳转投诉页面
	 * 
	 * @param request
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value = "complain", method = RequestMethod.GET)
	public String complain(HttpServletRequest request, int articleId) {

		Acticle acticle = artcleService.getById(articleId);

		request.setAttribute("article", acticle);

		request.setAttribute("complain", new Complain());

		return "article/complain";
	}

	/**
	 * 接收投诉页面的数据
	 * 
	 * @param request
	 * @param articleId
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "complain", method = RequestMethod.POST)
	public String complain(HttpServletRequest request, @ModelAttribute("complain") @Valid Complain compain,
			BindingResult result, MultipartFile file) throws IllegalStateException, IOException {

		if (!FileUtils.isHttpUrl(compain.getSrcUrl())) {

			result.rejectValue("srcUrl", "", "不是合法的url地址");

		}

		if (result.hasErrors()) {

			return "article/complain";

		}

		// 加上投诉人
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);

		String processFile = this.processFile(file);
		compain.setPicture(processFile);

		if (loginUser != null) {
			compain.setUserId(loginUser.getId());
		} else {

			compain.setUserId(0);
		}

		int i = artcleService.addComplian(compain);

		return "article/complain";
	}
	
	@RequestMapping("complains")
	public   String    complains(HttpServletRequest request,int articleId,@RequestParam(defaultValue="1") int page) {
	  
		PageInfo<Complain>  complianPage=artcleService.getComplains(articleId,page);
		System.out.println("-------------------------"+complianPage);
	    request.setAttribute("complianPage", complianPage);
	  
	  return "article/complainslist";
	}
	
	
	
}
