package com.lixuecheng.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lixuecheng.common.CmsContant;
import com.lixuecheng.common.CmsError;
import com.lixuecheng.common.CmsMessage;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Comment;
import com.lixuecheng.entity.User;
import com.lixuecheng.service.ArtcleService;

@Controller
@RequestMapping("article")
public class ArticleController {

     @Autowired
     ArtcleService   artcleService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
     @RequestMapping("getDetail")
     @ResponseBody
	 public   CmsMessage   getDetail(int id) {
		 if(id<=0) {
			 
		 }
		 
		 //获取文章详情
		 Acticle acticle = artcleService.getById(id);
	     //不存在
		 if(acticle==null) {
			 
			 return  new  CmsMessage(CmsError.NOT_EXIST,"文章不存在",null);
		 }
	     //文章返回数据
		return  new   CmsMessage(CmsError.SUCCESS,"",acticle); 
	 
	 }
     
     /**
      * 获取最新文章
      * @param request
      * @param id
      * @return
      */
     @RequestMapping("detail")
     public   String    detail(HttpServletRequest request,int id) {
    	 
    	 Acticle acticle = artcleService.getById(id);
    	 
    	 
    	 request.setAttribute("acticle", acticle);
    	 
    	 return  "detail";
     }
     
     /**
      * 评论
      * @param request
      * @param articleId
      * @param content
      * @return
      */
     @RequestMapping("postcomment")
     @ResponseBody
     public   CmsMessage   postcomment(HttpServletRequest request,int articleId,String  content) {
    	
    	User   loginUser=(User)request.getSession().getAttribute(CmsContant.USER_KEY);
    	 if(loginUser==null) {
    		 
    		 return   new   CmsMessage(CmsError.NOT_LOGIN, "没有登陆！", null);
    	 }
    	   
    	 Comment   comment =	 new   Comment();
    	 comment.setUserId(loginUser.getId()); 
    	 comment.setContent(content);
    	 comment.setArticleId(articleId);	
    	 
       int result=artcleService.addComment(comment); 
    	 
       if(result>0) {
    	   
    	   return   new CmsMessage(CmsError.SUCCESS,"成功发表",null);
       }
       return   new   CmsMessage(CmsError.FAILED_UPDATE_DB, "评论失败！", null);
     }
     
     
     @RequestMapping("comments")
     public  String     comments(HttpServletRequest  request,int id,int page) {
    	 
          PageInfo<Comment>  commentPage =artcleService.getComments(id,page);
          
          System.out.println(commentPage+"111111111111111111");
           
          request.setAttribute("articlePage", commentPage);
          
    	 return "comments";
     }
	
}
