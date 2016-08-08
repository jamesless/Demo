package com.sfpay.airscape.server.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sfpay.airscape.server.service.SubSysAppInfoService;
import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.SubSysAppInfo;

/**
 * 
 * @author sfhq1588
 */
@Service(value = "subSysAppInfoService")
public class SubSysAppInfoServiceImpl extends BaseServiceImpl<SubSysAppInfo> implements SubSysAppInfoService {


	@Override
	public List<SubSysAppInfo> queryAllByPage(Page page) {
		return subSysAppInfoDao.queryByPage(page);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int count(Map map) {
		return subSysAppInfoDao.count(map);
	}

}
