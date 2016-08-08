package com.sfpay.airscape.server.service;

import java.util.List;
import java.util.Map;

import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.SubSysAppInfo;

/**
 * 
 * @author sfhq1588
 */
public interface SubSysAppInfoService extends BaseService<SubSysAppInfo> {

	/**
	 * 分页查询
	 * @param page Page
	 * @return List
	 */
	List<SubSysAppInfo> queryAllByPage(Page page);
	
	/**
	 * 统计总数据量
	 * @param map map
	 * @return int
	 */
	@SuppressWarnings("rawtypes") 
	int count(Map map);
	
}
