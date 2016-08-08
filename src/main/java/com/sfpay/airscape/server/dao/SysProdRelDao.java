package com.sfpay.airscape.server.dao;

import com.sfpay.airscape.server.vo.auxiliary.SysProdRel;

/**
 * 
 * @author sfhq1576
 *
 */
public interface SysProdRelDao extends BaseDao<SysProdRel>{
	
	/**
	 * 
	 * @param spr spr
	 */
	void delete(SysProdRel spr);
	
	/**
	 * 
	 * @param systemid systemid
	 */
	void deleteBySystemid(String systemid);
}
