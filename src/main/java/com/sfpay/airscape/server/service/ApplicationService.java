package com.sfpay.airscape.server.service;
import java.util.List;
import java.util.Map;

import com.sfpay.airscape.server.vo.Application;
import com.sfpay.airscape.server.vo.Page;

/**
 * @author sfhq593 2015年12月28日14:05:50
 * 
 */
public interface ApplicationService extends BaseService<Application>{

	/**
	 * TODO 更新STATUS状态,当依赖记录没有进行更新时,表明该记录已经被弃用了
	 */
	void updateCheckValid();
	
	/**
	 * 分页查询
	 * @param page Page
	 * @return List
	 */
	List<Application> queryAllByPage(Page page);
	
	/**
	 * 统计总数据量
	 * @param map map
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	int count(Map map);
}
