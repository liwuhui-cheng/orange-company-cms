package com.lixuecheng.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.lixuecheng.common.CmsContant;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Category;
import com.lixuecheng.entity.Channel;
import com.lixuecheng.entity.User;
import com.lixuecheng.service.ArtcleService;
import com.lixuecheng.service.UserService;
import com.lixuecheng.test.FileUtils;
import com.lixuecheng.test.HtmlUtiles;
import com.lixuecheng.test.StringUtils;

@Controller
@RequestMapping("user")
public class UserController {

	@Value("${upload.path}")
	String   picRootPath;
	
	@Value("pic.path")
	String picUrl;
	
	@Autowired
	 private   UserService   service;
	 
	 //文章的service
	 @Autowired
	 ArtcleService   artcleService;
	 
	 @RequestMapping("home")
	 public   String   home(){		 
		 
		 return  "user/home";		 
	 }
	 
	 /**
	  * 跳转注册页面 
	  * @param request
	  * @return
	  */
	
	 @RequestMapping(value="register",method=RequestMethod.GET)
	 public   String  register(HttpServletRequest  request) {
		 
		 User user = new  User();
		 
		 request.setAttribute("user", user);
		 
		 return   "user/register";
	 }
	 
	 @RequestMapping(value="register",method=RequestMethod.POST)
	 public   String    register(HttpServletRequest request,@Valid @ModelAttribute("user") User user,BindingResult result) {
		    
		 //如果有错误返回到注册页面
		 if(result.hasErrors()) { 
			 return  "user/register";
		 }
		 
		 //进行唯一验证
	      User  existUser =	 service.getUserByUsername(user.getUsername());
	      if(existUser!=null) {	    	  
	    	  result.rejectValue("username", "", "用户名重复或者输入错误！");
	    	  return  "user/register";
	      }
	      
	     //加一个手动的校验
	      if(StringUtils.isNumber(user.getPassword())) {
	    	  result.rejectValue("password", "", "密码不能全是数字");
	    	 return "user/register"; 
	      }
	      
	      //去注册
	      int  exregister=service.register(user);
	      
	      //注册失败
	      if(exregister<1) {
	    	 
	    	  request.setAttribute("err", "注册失败！");
	    	  return  "user/register";
	      }
	      
	      return  "redirect:login";
	      
	 }
	 /**
	  * 跳转到登陆页面
	  * @param request
	  * @return
	  */
	 @RequestMapping(value="login",method=RequestMethod.GET)
	 public   String   login(HttpServletRequest request) {
		 return  "/user/login";
	 }
	 
	 /**
	  * 登陆功能
	  * @param request
	  * @param user
	  * @return
	  */
	 @RequestMapping(value="login",method=RequestMethod.POST)
	 public  String    login2(HttpServletRequest request,User user) {
		 
		User  loginUser= service.login(user);
		
		if(loginUser==null) {	
			
			request.setAttribute("error", "账号或密码重复!!");
			return "user/login";
		}
		
		request.getSession().setAttribute(CmsContant.USER_KEY, loginUser);
		
		//进入管理界面
		if(loginUser.getRole()==CmsContant.USER_ROLE_ADMIN) {
			return  "redirect:/admin/index";	
		}
		
		//进入个人中心 
		return  "redirect:/user/home";
	 }
	 
	 
	 
	 /**
	  * 
	  * @param username
	  * @return
	  */
	 @RequestMapping("checkname")
	 @ResponseBody
	 public  boolean    checkedUserName(String  username) {
		 
		 User userByUsername = service.getUserByUsername(username);
		 
		 return  userByUsername==null ;
	 }
	 
	 /**
	  * 我的文章列表查询
	  * 
	  * @param request
	  * @param pageNum
	  * @return
	  */
	 @RequestMapping("/articles")
	 public    String   articles(HttpServletRequest request,@RequestParam(defaultValue="1") int pageNum) {
		 
		 User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		 
	     PageInfo<Acticle>  articlePage=	 artcleService.listByUser(loginUser.getId(),pageNum);
		 
	    // System.out.println("1111111111"+articlePage);
	     
	      // articlePage.getPages();
		 request.setAttribute("articlePage", articlePage);
		 return  "user/article/list";
		 
	 }
	 /**
	  * 删除我的文章
	  * @param id
	  * @return
	  */
	 
	 @RequestMapping("del")
	 @ResponseBody
	 public  boolean    deleteArticle(int id) {
		 
		 int  falg= service. deleteArticle(id);
		 
		 return  falg>0;
	 }
	 
	 /**
	  * 跳转到发布文章的页面
	  * @param request
	  * @return
	  */
	 @RequestMapping("/postArticle")
	 public    String    postArticle(HttpServletRequest request) {
		 //查询所有的栏目
		 List<Channel>  channels = artcleService.getChannel();
		 
		 request.setAttribute("channels", channels);
		 return  "/user/article/post";
	 }
	 
	 /**
	  *  查询所有的栏目
	  * @param request
	  * @param cid
	  * @return
	  */
	 @RequestMapping("getCategoris")
	 @ResponseBody
	 public   List<Category>  getCategoris(HttpServletRequest request,int cid) {
		 
		 List<Category> categorisByCid = artcleService.getCategorisByCid(cid);
		// System.out.println("11111111111111111"+categorisByCid);
		 return  categorisByCid;
	 }
	 
	 
	 @RequestMapping("/comments")
	 public    String   comments() {
		 
		 return  "/user/comment/list";
		 
	 }
	 /**
	  * 发布文章
	  * @param request
	  * @param article
	  * @param file
	  * @return
	  */
	 @RequestMapping(value="postArticle",method=RequestMethod.POST)
	 @ResponseBody
	 public   boolean    postArticle(HttpServletRequest  request, Acticle  article,  MultipartFile  file ) {
		 
		    String  picUrl;
		     try {
				picUrl=processFile(file);
				
				article.setPicture(picUrl);
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		     
		     //获取当前登录用户
		     User attribute = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		      
		     article.setUserId(attribute.getId());
		    
		     //添加文章
		     int add = artcleService.add(article);
		     
		     return   add>0;
		     
	 }
	 
	 /**、
	  * 判断文章目录是否存在以及重新命名
	  * @param file
	  * @return
	  * @throws IllegalStateException
	  * @throws IOException
	  */
	 private   String   processFile(MultipartFile  file ) throws IllegalStateException, IOException {
		 //判断当前目录是否存在
		 //picRootPath+""
		 
		 SimpleDateFormat sdf = new  SimpleDateFormat("yyyyMMdd");
		 
		 //当前日期
		 String format = sdf.format(new Date());
		 //图片存放的地址
		 File file2 = new  File(picRootPath+"/"+format);
		  //判断是否存在
		 if(!file2.exists()) {
			 //不存在创建
			 file2.mkdirs();
		 }
		 //新文件名称
		 String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
		 //随机生成文件名
	     String   fileName= UUID.randomUUID().toString() +suffixName;
		
	     //文件另存
	    file.transferTo(new File(picRootPath+"/"+format+"/"+fileName));
		 
	    
	    return  picUrl+ format+"/"+fileName;
	 }
	 
	 /**
	  * 跳转到 修改文章的页面
	  */
	 @RequestMapping(value="updateArticle",method=RequestMethod.GET)
	 public    String   updateArticle(HttpServletRequest request,int id)     {
		 
		 //获取栏目
         List<Channel>  channels = artcleService.getChannel();
		 
		 request.setAttribute("channels", channels);
		 
		 //获取文章
		 Acticle  article = artcleService.getById(id);
		 
	     User attribute = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
          
	     if(attribute.getId()!=article.getUserId()) {
	    	 //to do 做异常处理
	     }
	     
	     request.setAttribute("article", article);
	     
	 	// request.setAttribute("content1",HtmlUtiles.htmlspecialchars(article.getContent()));
	 	 
		 return  "/user/article/update";
	 }
	 
	 
	 /**
	  * 接收修改文章的页面数据
	  */
	 @RequestMapping(value="updateArticle",method=RequestMethod.POST)
	 @ResponseBody
	 public    boolean   updateArticle(HttpServletRequest request,Acticle article,MultipartFile file)     {
		 
		 String picUrl;
			try {
				// 处理上传文件
				picUrl = processFile(file);
				article.setPicture(picUrl);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//当前用户是文章的作者
			User loginUser = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
			//article.setUserId(loginUser.getId());
			int updateREsult  = artcleService.update(article,loginUser.getId());
			
			
			return updateREsult>0;
	 }
	 
	 
	 @RequestMapping("logout")
	 public   String   home(HttpServletRequest request) {
		 
		 request.getSession().removeAttribute(CmsContant.USER_KEY);
		 
		 return  "redirect:/";
	 }
}
