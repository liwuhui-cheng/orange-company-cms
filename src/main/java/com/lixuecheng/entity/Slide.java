package com.lixuecheng.entity;

import java.io.Serializable;

/**
 * 轮播图
 * @author orang
 *
 */
public class Slide implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2607842938531674452L;
	private Integer id;
	private String title;
	private String picture;
	private String url;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Slide [id=" + id + ", title=" + title + ", picture=" + picture + ", url=" + url + "]";
	}

	
	
}
