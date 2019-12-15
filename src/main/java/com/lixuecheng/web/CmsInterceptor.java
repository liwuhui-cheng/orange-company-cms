package com.lixuecheng.web;

import java.security.interfaces.RSAKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.lixuecheng.common.CmsContant;
import com.lixuecheng.entity.User;

/**
 * 拦截器，判断用户是否已经登陆
 * @author orang
 *
 */
public class CmsInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User attribute = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		
		if(attribute==null) {
			response.sendRedirect("/user/login");
			
			//request.getRequestDispatcher("/user/login").forward(request, response);
			
			return  false;
		}
		
		//放行
		return true;
		
		
	}
}
