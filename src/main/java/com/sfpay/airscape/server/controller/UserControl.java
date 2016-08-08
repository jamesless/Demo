package com.sfpay.airscape.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

import com.sfpay.airscape.server.service.UserService;
import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.User;

/**
 * 
 * @author sfhq1576
 *
 */
@Controller
public class UserControl {

	@Resource
	private UserService userService;
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(UserControl.class);
	
	/**
	 * 
	 * @param page page
	 * @return List<User>
	 */
	@RequestMapping( value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public List<User> queryUser(@RequestBody Page page){
		logger.info("用户分页查询");
		return userService.queryByPage(page);
	}
	
	/**
	 * 
	 * @param userName userName
	 * @return List<User>
	 */
	@RequestMapping( value = "/user/{userName}", method = RequestMethod.GET)
	@ResponseBody
	public List<User> queryByUserName(@PathVariable String userName){
		logger.info("查询用户对象");
		List<User> userList = new ArrayList<>();
		if("ALL".equals(userName)){
			return userService.queryAll();
		}else{
			userList.add(userService.query(userName));
			return userList;
		}
	}
	
	/**
	 * 
	 * @param user user
	 */
	@RequestMapping( value = "/user", method = RequestMethod.PUT)
	@ResponseBody
	public void updateUser(@RequestBody User user){
		logger.info("更新用户对象");
		userService.update(user);
	}
	
	
	/**
	 * 
	 * @return Map<String,String>
	 */
	@RequestMapping( value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> getCount(){
		logger.info("查询用户数量");
		Map<String,String> map = new HashMap<String,String>();
		map.put("count", userService.count()+"");
		return map;
	}
	
}
