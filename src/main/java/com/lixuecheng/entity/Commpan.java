package com.lixuecheng.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 举报类
 * @author orang
 *
 */
public class Commpan {

	private Integer id;
	private Integer article_id;
	private Integer user_id;
	private String complaintype;
	private String urlip;
	
	//文章
	private  String   title;
	private  String   complainnum;
	private   String  content;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private  Date  created;
	
	
	
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComplainnum() {
		return complainnum;
	}
	public void setComplainnum(String complainnum) {
		this.complainnum = complainnum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getComplaintype() {
		return complaintype;
	}
	public void setComplaintype(String complaintype) {
		this.complaintype = complaintype;
	}
	public String getUrlip() {
		return urlip;
	}
	public void setUrlip(String urlip) {
		this.urlip = urlip;
	}
	@Override
	public String toString() {
		return "Commpan [id=" + id + ", article_id=" + article_id + ", user_id=" + user_id + ", complaintype="
				+ complaintype + ", urlip=" + urlip + "]";
	}
	public Commpan() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}
