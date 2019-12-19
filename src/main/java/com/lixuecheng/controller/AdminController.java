package com.lixuecheng.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lixuecheng.common.CmsError;
import com.lixuecheng.common.CmsMessage;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.service.ArtcleService;

@RequestMapping("admin")
@Controller
public class AdminController {

	@Autowired
	ArtcleService  artcleService;
	/**
	 * 跳转管理员页面
	 * @return
	 */
	@RequestMapping("index")
	public String index() {

		return "admin/index";
	}
	
	/**
	 * 页面查询
	 * @param request
	 * @param status
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("article")
	public   String   article(HttpServletRequest request,@RequestParam(defaultValue="0")int status,@RequestParam(defaultValue="1")int pageNum) {
		
	PageInfo<Acticle>  articlePage=	artcleService.list(status, pageNum);
	
	request.setAttribute("status",status);
	request.setAttribute("articlePage", articlePage);
	 
	return  "/admin/article/list";
	
	}
	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("setArticeHot")
	@ResponseBody
	public   CmsMessage   setArticeHot(int id,int status) {
		 
		/**
		 * 数据合法性校验
		 */
		if(status!=0 && status!=1) {
			
			
		}
		
		if(id<0) {
			

		}
		
		Acticle acticle = artcleService.getInfoById(id);
		
		if(acticle==null) {
			

		}
		
		if(acticle.getStatus()==status) {
			
		}
		//设置热门
	    int result=artcleService.setHot(id,status);
	    if(result<1) {
		  return   new  CmsMessage(CmsError.FAILED_UPDATE_DB,"设置失败,请稍后再试!", null);
	    }
	  
		  return   new  CmsMessage(CmsError.SUCCESS,"成功!", null);

	}
	
	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
 
	@RequestMapping("setArticeStatus")
	@ResponseBody
	public   CmsMessage   setArticeStatus(int id,int status) {
		
		System.out.println("1111111"+id+"22222222"+status);
		/**
		 * 数据合法性校验
		 */
		if(status!=1 && status!=2) {
			return  new CmsMessage(CmsError.NOT_VALIDTED, "status参数不合法", null);
		}
		
		if(id<0) {
			return  new CmsMessage(CmsError.NOT_VALIDTED, "id参数不合法", null);
		}
		
		Acticle acticle = artcleService.getInfoById(id);
		
		if(acticle==null) {
			return  new CmsMessage(CmsError.NOT_EXIST, "数据不存在", null);
		}
		
		if(acticle.getStatus()==status) {
			return  new CmsMessage(CmsError.NEEDNT_UPDATE, "数据不需要更改", null);
			
		}
		//设置状态
	    int result=artcleService.setCheckStatus(id,status);
	    if(result<1) {
		  return   new  CmsMessage(CmsError.FAILED_UPDATE_DB,"设置失败,请稍后再试!", null);
	    }
	  
		  return   new  CmsMessage(CmsError.SUCCESS,"成功!", null);

		
	}
	
	
}
