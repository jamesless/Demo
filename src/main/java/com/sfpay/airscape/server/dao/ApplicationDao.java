package com.sfpay.airscape.server.dao;

import java.util.List;
import java.util.Map;

import com.sfpay.airscape.server.vo.Application;
import com.sfpay.airscape.server.vo.Page;

/**
 * 
 * @author sfhq1576
 *
 */
public interface ApplicationDao extends BaseDao<Application>{

	/**
	 * 
	 */
	void updateCheckValid();
	
	/**
	 * 
	 * @param page page
	 * @return List<Application>
	 */
	List<Application> queryByPage(Page page);
	
	/**
	 * 
	 * @param map map
	 * @return int
	 */
	int count(Map map);
}