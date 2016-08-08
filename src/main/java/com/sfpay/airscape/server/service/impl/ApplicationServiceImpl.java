package com.sfpay.airscape.server.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sfpay.airscape.server.service.ApplicationService;
import com.sfpay.airscape.server.vo.Application;
import com.sfpay.airscape.server.vo.Page;


/**
 * @author sfhq593 2015年12月28日14:12:21
 */

@Service
public class ApplicationServiceImpl extends BaseServiceImpl<Application> implements ApplicationService {

	@Override
	public void updateCheckValid() {
		applicationDao.updateCheckValid();
	}

	@Override
	public List<Application> queryAllByPage(Page page) {
		return applicationDao.queryByPage(page);
	}

	@Override
	public int count(Map map) {
		return applicationDao.count(map);
	}
}
