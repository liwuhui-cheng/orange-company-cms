package com.lixuecheng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lixuecheng.entity.Slide;

/**
 * 轮播图管理
 * @author orang
 *
 */
public interface SlideMapper {

	//轮播图查询
	@Select("SELECT  id,title,picture,url   FROM   cms_slide  ORDER  BY  id")
	List<Slide> list();

}
