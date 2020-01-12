package com.lixuecheng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lixuecheng.entity.Acticle;
import com.lixuecheng.entity.Category;
import com.lixuecheng.entity.Channel;
import com.lixuecheng.entity.Comment;
import com.lixuecheng.entity.Commpan;
import com.lixuecheng.entity.Complain;
import com.lixuecheng.entity.Condtion;
import com.lixuecheng.entity.Slide;

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

	/**
	 * 回显文章
	 * @param id
	 * @return
	 */
	
	Acticle getById(int id);

	/**
	 * 修改文章
	 * @param article
	 * @return
	 */

	
	@Update("UPDATE cms_article SET title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},"
			+ " category_id=#{categoryId},status=0,"
			+ "updated=now() WHERE id=#{id} ")
	int update(Acticle article);

	/**
	 * 文章列表
	 * @param status
	 * @return 
	 */
	List<Acticle> list(@Param("status")int status);

	/**
	 * 获取状态参数值是否合法
	 * @param id
	 * @return
	 */
	@Select(" select  id,title,channel_id  channelId,category_id  categoryId   "
			+ "from  cms_article  where  id =#{value} ")
	Acticle getInfoById(int id);

	//设置状态
	@Update("UPDATE  cms_article   SET  status=#{mystatus}  WHERE  id=#{id}")
	int setCheckStatus(@Param("id")int id,@Param("mystatus")int status);
	
     //设置热门
	@Update("UPDATE  cms_article  SET  hot=#{hot}  WHERE id=#{myid}")
	int setHot(@Param("myid")int id,@Param("hot")int status);

	//最热文章
	List<Acticle> hotList(int page);

	//最新文章
	List<Acticle> lastList(int pageSize);
	
    //获取栏目，分类下的文章
	List<Acticle> getArticles(@Param("channelId")int channleId, @Param("catId")int catId);

	
	//获取栏目下的分类
	@Select("SELECT     *    from     cms_category      where   channel_id=#{value}")
	@ResultType(Category.class)
	List<Category> getCategorByChannelId(int channleId);

	//发表评论
	@Insert("INSERT INTO cms_comment(articleId,userId,content,created)"
			+ " VALUES(#{articleId},#{userId},#{content},NOW())")
	int addComment(Comment comment);
	
	//增加评论数
	@Update("UPDATE  cms_article   SET  commentCnt=commentCnt+1  where  id=#{value}")
	int  increaseCommentCnt(int id);

	
	@Select("SELECT c.id,c.articleId,c.userId,u.username as userName,c.content,c.created FROM cms_comment as c "
			+ " LEFT JOIN cms_user as u ON u.id=c.userId "
			+ " WHERE articleId=#{value} ORDER BY c.created DESC")
	List<Comment> getComments(int artcleId);

	//
	@Insert("INSERT  into  cms_complain    values(null,#{articleId},#{userId},#{complainType},#{compainOption},#{srcUrl},#{picture},#{content},#{email},#{mobile},now())")
	int addCoplain(Complain compain);

	//


	List<Complain> getComplains(int articleId);

	@Insert("insert   into   cms2__complain (id,article_id,user_id,complaintype,urlip)   values(null,#{article_id},#{user_id},#{complaintype},#{urlip}) ")
	int addCopan(Commpan commpan);

	
	@Update("UPDATE  cms_article   SET   complainnum=complainnum+1,STATUS=IF(complainnum>10,2,STATUS)  WHERE  id=#{value}")
	void increaseComplainCat(Integer articleId);

	
	List<Commpan> listTs(Condtion con);
   // @Select("SELECT   *   FROM    cms2__complain	WHERE  id=#{id}")
	 List<Commpan> comList(int id);

	List<Commpan> tsNum1();

	//查询投诉数
	List<Commpan> listPan();

	List<Commpan> tsNum2();

	
	//
	@Select("SELECT id   from cms_category  where channel_id=#{random}")
	int[] selectCategoryId(int random);

	
	@Select("SELECT  *   FROM  cms_article  where  status=#{i}")
	List<Acticle> findAllArticleWithStatus(int i);
	
	
}
