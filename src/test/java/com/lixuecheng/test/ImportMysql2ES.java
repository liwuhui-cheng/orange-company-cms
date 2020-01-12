package com.lixuecheng.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lixuecheng.entity.Acticle;
import com.lixuecheng.mapper.ArtcleMapper;
import com.lixuecheng.mapper.ArticleRep;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ImportMysql2ES {

	@Autowired
	ArtcleMapper artcleMapper;
	
	@Autowired
	ArticleRep articleRep;
	
	@Test
	
	public   void   importMySql2es() {
		
		//1.从mysql中查询已经审核通过的文案
		 List<Acticle> findAllArticleWithStatus = artcleMapper.findAllArticleWithStatus(1);
		
		 //2.查询出来的文章保存到es的索引库
		 articleRep.saveAll(findAllArticleWithStatus);
	}
}
