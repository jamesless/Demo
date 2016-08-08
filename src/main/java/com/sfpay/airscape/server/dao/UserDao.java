package com.sfpay.airscape.server.dao;

import java.util.List;

import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.User;

/**
 * 
 * @author sfhq1576
 *
 */
public interface UserDao extends BaseDao<User>{
	/**
	 * 
	 * @param page page
	 * @return List<User>
	 */
	public List<User> queryByPage(Page page);
	
	/**
	 * 
	 * @return Integer
	 */
	public Integer count();
}