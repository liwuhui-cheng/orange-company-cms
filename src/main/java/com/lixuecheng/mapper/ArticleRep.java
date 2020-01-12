package com.lixuecheng.mapper;

import java.util.List;

import org.apache.ibatis.ognl.ElementsAccessor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.lixuecheng.entity.Acticle;

public interface ArticleRep extends ElasticsearchRepository<Acticle, Integer>{

	//根据标题查询
	List<Acticle> findByTitle(String key);

	
	
}
