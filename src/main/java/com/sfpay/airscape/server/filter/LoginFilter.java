package com.sfpay.airscape.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sfpay.airscape.server.vo.User;

/**
 * 
 * @author  sfhq1588
 *
 */
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String msg = "";
		int i = 401;
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		User user = (User) req.getSession().getAttribute("user");
		String requestURI = req.getRequestURI();
		//不存在当前用户session，并且当前页面不是登录页面，则报出会话过期
		if(user == null){
			if("/airscape-server/login".equals(requestURI)){
				chain.doFilter(request, response);
			}else{
				msg = "{\"code\":\"0003\",\"message\":\"会话已过期，请重新登录\"}";
				res.setStatus(i);
				res.getOutputStream().print(new String(msg.getBytes("UTF-8"),"ISO-8859-1"));
			}
		}else{
			chain.doFilter(request, response);
//			msg = "{'code':'0002','message':'未授权'}";
//			res.setStatus(401);
//			res.getOutputStream().print(new String(msg.getBytes("UTF-8"),"ISO-8859-1"));
		}
		
	}
	
	@Override
	public void destroy() {
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
