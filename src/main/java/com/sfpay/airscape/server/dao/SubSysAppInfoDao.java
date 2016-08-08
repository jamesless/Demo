package com.sfpay.airscape.server.dao;

import java.util.List;
import java.util.Map;

import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.SubSysAppInfo;

/**
 * 
 * @author sfhq1576
 *
 */
public interface SubSysAppInfoDao extends BaseDao<SubSysAppInfo>{
	/**
	 * 
	 * @param page page
	 * @return List<SubSysAppInfo>
	 */
	List<SubSysAppInfo> queryByPage(Page page);
	
	/**
	 * 
	 * @param map map
	 * @return int
	 */
	int count(Map map);
}