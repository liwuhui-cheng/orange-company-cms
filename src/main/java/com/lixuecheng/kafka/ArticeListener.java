package com.lixuecheng.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.lixuecheng.entity.Acticle;
import com.lixuecheng.service.ArtcleService;

public class ArticeListener implements MessageListener<String, String> {

	@Autowired
	private  ArtcleService  artcleService;
	
	//这个方法就是监听消息的方法
	public void onMessage(ConsumerRecord<String, String> date) {
		
		//接收消息
		String value = date.value();
		
		System.err.println("收到了消息!!!!");
		
		//把json对象转成artice对象
		Acticle acticle = JSON.parseObject(value, Acticle.class);
		//大标题id
		int  random = (int) (Math.random()*(8-1)+1);
		
		acticle.setChannelId(random);  
		//查询大标题对应的小标题
	    int[]  artcleid=artcleService.selectCategoryId(random);
	    //分类id
		int  categoryId=(int)(Math.random()*artcleid.length);
		acticle.setCategoryId(artcleid[categoryId]);
		//System.out.println(acticle);
		//System.out.println("==========="+categoryId);
		//System.out.println("++++++++++++++"+artcleid[categoryId]);
		//保存到mysql中
		artcleService.add(acticle);
		
		
		System.out.println(value);
	}

}
