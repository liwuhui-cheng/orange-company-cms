package com.lixuecheng.mapper;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lixuecheng.entity.User;

public interface UserMapper {

	
	//验证唯一
	@Select("select   id,username,password   from    cms_user   where    username=#{username}  limit 1")
	User getUserByUserName(String username);

	//注册
	@Insert("INSERT   into   cms_user   (username,password,locked,create_time,score,role) values  (#{username},#{password},0,now(),0,0)")	
	int register(@Valid User user);

	
    //登陆
	@Select("SELECT id,username,password,nickname,birthday,"
			+ "gender,locked,create_time createTime,update_time updateTime,url,"
			+ "role FROM cms_user WHERE username=#{username}  AND password = #{password} "
			+ " LIMIT 1")
	User findByPwd(User user);

	//删除文章的状态
	@Update("update   cms_article   set  deleted=#{status}  where  id=#{id}")
	int updateStatus(@Param("id")int id,@Param("status")int status);

}
