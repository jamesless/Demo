package com.sfpay.airscape.server.service;

import java.util.List;

import com.sfpay.airscape.server.vo.SvnRepository;

/**
 * @author sfhq593 2015年12月24日15:44:06
 */
public interface SvnRepositoryService extends BaseService<SvnRepository> {
	/**
	 * 查询文件状态
	 * @return List
	 */
	public List<SvnRepository> queryFilterStatus();

}
