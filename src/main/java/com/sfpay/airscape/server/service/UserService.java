package com.sfpay.airscape.server.service;
import java.util.List;

import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.User;

/**
 * 
 * @author sfhq1588
 */
public interface UserService extends BaseService<User>{
	/**
	 * 分页查询
	 * @param page Page
	 * @return List
	 */
	public List<User> queryByPage(Page page);
	
	/**
	 * 数据总数统计
	 * @return Integer
	 */
	public Integer count();
}
