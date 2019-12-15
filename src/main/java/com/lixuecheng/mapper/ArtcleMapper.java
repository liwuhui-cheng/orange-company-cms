package com.lixuecheng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Category;
import com.lixuecheng.entity.Channel;

public interface ArtcleMapper {

	//根据用户获取文章的列表
	
	List<Acticle> listByUser(Integer id);

	/**
	 * //获取所有的栏目的方法
	 * @return
	 */
	@Select("select  id,name  from   cms_channel")
	List<Channel> getAllChannel();

	/**
	 *  根据栏目id，获取分类
	 * @param cid
	 * @return
	 */
	@Select("select  id,name  from  cms_category  where  channel_id=#{cid}")
	List<Category> getCategorisByCid(int  cid);
    /**
     * 发布文章
     * @param article
     * @return
     */
	@Insert("INSERT INTO cms_article(title,content,picture,channel_id,category_id,user_id,hits,hot,status,deleted,created,updated,commentCnt,articleType)"
			+ " VALUES(#{title},#{content},#{picture},#{channelId},#{categoryId},#{userId},0,0,0,0,now(),now(),0,#{articleType})")
	int add(Acticle article);
}
