/**
 * =================================================================
 * 版权所有 2011-2020 恒通支付服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * =================================================================
 */
package com.sfpay.airscape.server.controller;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ch.qos.logback.classic.Logger;

import com.alibaba.fastjson.JSONObject;
import com.sf.module.ldap.ILdapAuthentication;
import com.sf.module.ldap.LdapAuthentication;
import com.sfpay.airscape.server.service.LoginInfoService;
import com.sfpay.airscape.server.service.UserService;
import com.sfpay.airscape.server.vo.LoginInfo;
import com.sfpay.airscape.server.vo.User;
import com.sfpay.airscape.server.vo.auxiliary.ErrorInfo;
import com.sfpay.framework2.common.util.Md5Utils;

/**
 * 
 * @author sfhq1576
 *
 */
@Controller
public class LoginInfoControl {
	@Resource
	private LoginInfoService loginInfoService;
	@Resource
	private UserService userService;
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(LoginInfoControl.class);

	/**
	 * 
	 * @param loginInfo loginInfo
	 * @param session session
	 * @return User
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public User checkLoginInfo(@RequestBody String loginInfo,HttpSession session) {
		if("".equals(loginInfo)){
			return null;
		}
		
		JSONObject jsonObject = JSONObject.parseObject(loginInfo);
		User user = new User();
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		String ldappath = "ldap://10.116.56.96:389/";
		String ldapdomain = "sf";
		String ldapadminSuffix = ".asura";
		// 验证域用户
		ILdapAuthentication ldapAuthentication = new LdapAuthentication(
				ldappath, ldapdomain, ldapadminSuffix);
		
		if("".equals(username) || "".equals(password)){
			return null;
		}else if("admin".equals(username) && "admin123".equals(password)){
			user.setUserName(username);
			user.setRoleId(0);
			session.setAttribute("user", user);
		}else if (ldapAuthentication.authenticate(username, password)) {
			LoginInfo loginUserInfo = new LoginInfo();
			User userInfo = userService.query(username);
			user.setUserName(username);
			
			//存在用户则更新，不存在则新增，新增的默认权限为只读
			if(userInfo == null){
				user.setRoleId(2);
				user.setPassword(password);
				userService.add(user);
			}else{
				user = userInfo;
				userService.update(user);
			}
			
			loginUserInfo.setUserName(username);
			loginInfoService.add(loginUserInfo);
			session.setAttribute("user", user);
			
		//如果域账号登录不上的情况下，会进入查找本地数据库的user密码
		}else{
			User userInfo = userService.query(username);
			if(null!=userInfo)
			{
				try {
					password = Md5Utils.encryptMD5(password).toLowerCase();
					if(password.equals(userInfo.getPassword())){
						userService.update(userInfo);
						session.setAttribute("user", userInfo);
					}
				} catch (NoSuchAlgorithmException e) {
					logger.error(e.toString());
				}
			}
		}
		
		return user;
	}
	
	/**
	 * 
	 * @param username username
	 * @param httpSession httpSession
	 */
	@RequestMapping(value = "/logout/{username}",method = RequestMethod.GET)  
	@ResponseBody
    public void logout(@PathVariable String username,HttpSession httpSession){  
        User user = (User) httpSession.getAttribute("user");  
        String sessionUserName = user.getUserName();
        if(username.equals(sessionUserName)){
        	httpSession.removeAttribute("user");
        }
    }  
	
	
	
	/**
	 * 
	 * @param e
	 *            接收一个错误处理對象
	 * @return 返回一个错误的实体对象
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo exception(Exception e) {
		logger.error(e.toString());
		return new ErrorInfo("", e.getMessage());
	}
}
