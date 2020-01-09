package com.lixuecheng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Category;
import com.lixuecheng.entity.Channel;
import com.lixuecheng.entity.Slide;
import com.lixuecheng.service.ArtcleService;

@Controller
public class IndexController {

	@Autowired
	ArtcleService artcleService;
   
	@Autowired
	RedisTemplate   redisTemplate;
	 
	
	@RequestMapping(value= {"index","/"})
	public String index(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) throws Exception {

		Thread t1 = new Thread() {

			@Override
			public void run() {
				//获取所有的栏目
				List<Channel> channels = artcleService.getChannel();
				request.setAttribute("channels", channels);
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {

				// 获取最热文章
				PageInfo<Acticle> articlePage = artcleService.hotList(page);

				request.setAttribute("articlePage", articlePage);
			}
		};

		Thread t3 = new Thread() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {

				// 获取最新文章
				
				//0:redis作为缓存优化最新文章
                     
				
				//1:从redis查询最新文章
				List<Acticle> range = redisTemplate.opsForList().range("new_articles", 0, -1);
				//2：判断redis中查询的是否为空
				if(range==null || range.size()==0) {
			    	 
					//3:如果为空,从mysql中查询最新文章
					List<Acticle> lastArticle = artcleService.lastList();
					System.out.println("从mysql中查询了...........");
					redisTemplate.opsForList().leftPushAll("new_articles", lastArticle.toArray());
					request.setAttribute("lastArticle", lastArticle);

					//4:如果不为空,直接返回前台
			     }else {
			    	 System.out.println("从redis查询了...............");
						request.setAttribute("lastArticle", range);

			     }
			}
		};

		Thread t4 = new Thread() {

			@Override
			public void run() {

				// 轮播图
				List<Slide> slides = artcleService.getSlides();
				request.setAttribute("slides", slides);
			}
		};
		
		t1.start();
		
		t2.start();
		
		t3.start();
		
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		

		return  "index";
	}

	
	/**
	 * 
	 * @param request  请求
	 * @param channleId  栏目的id
	 * @param catId    分类的id
	 * @param page   页码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("channel")
	public   String   channel(HttpServletRequest request,int  channelId,@RequestParam(defaultValue="0")int catId,@RequestParam(defaultValue="1")int  page) throws Exception {
		
		
		
		Thread t1 = new Thread() {

			@Override
			public void run() {
				//获取所有的栏目
				List<Channel> channels = artcleService.getChannel();
				request.setAttribute("channels", channels);
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {

				// 获取当前栏目下，当前分类下的文章
				PageInfo<Acticle> articlePage = artcleService.getArticles(channelId,catId,page);

				request.setAttribute("articlePage", articlePage);
			}
		};

		Thread t3 = new Thread() {

			@Override
			public void run() {

				// 获取最新文章
				List<Acticle> lastArticle = artcleService.lastList();
				request.setAttribute("lastArticle", lastArticle);

			}
		};

		Thread t4 = new Thread() {

			@Override
			public void run() {

				// 轮播图
				List<Slide> slides = artcleService.getSlides();
				request.setAttribute("slides", slides);
			}
		};
		
		//获取当前栏目下的所有的分类
	   Thread   t5=new  Thread() {
			
			@Override
			public void run() {
				//获取当前栏目下的所有的分类
				List<Category> catgoris = artcleService.getCategorByChannelId(channelId);
				request.setAttribute("catgoris", catgoris);
				
			}
			
			
		};
		
		t1.start();
		
		t2.start();
		
		t3.start();
		
		t4.start();
		
		t5.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		
		//参数回传
		request.setAttribute("catId", catId);
		request.setAttribute("channelId", channelId);
		
		return  "channel";
		
		
		
	}
	
}
