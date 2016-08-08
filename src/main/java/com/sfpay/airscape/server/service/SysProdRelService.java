package com.sfpay.airscape.server.service;

import com.sfpay.airscape.server.vo.auxiliary.SysProdRel;

/**
 * 
 * @author sfhq1588
 */
public interface SysProdRelService extends BaseService<SysProdRel>{
	/**
	 * 根据sysProdRel实体删除数据
	 * @param spr SysProdRel
	 */
	public void delete(SysProdRel spr);
	
	/**
	 * 根据systemid删除数据
	 * @param systemid systemid
	 */
	public void deleteBySystemid(String systemid);
}
