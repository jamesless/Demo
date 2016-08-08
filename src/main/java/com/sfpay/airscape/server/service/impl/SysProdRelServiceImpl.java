package com.sfpay.airscape.server.service.impl;

import org.springframework.stereotype.Service;

import com.sfpay.airscape.server.service.SysProdRelService;
import com.sfpay.airscape.server.vo.auxiliary.SysProdRel;

/**
 * 
 * @author sfhq1588
 */
@Service
public class SysProdRelServiceImpl extends BaseServiceImpl<SysProdRel> implements SysProdRelService {


	@Override
	public void deleteBySystemid(String systemid) {
		sysProdRelDao.deleteBySystemid(systemid);
	}

	@Override
	public void delete(SysProdRel spr) {
		sysProdRelDao.delete(spr);
	}

}
