package com.sfpay.airscape.server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sfpay.airscape.server.service.AppDependencesService;
import com.sfpay.airscape.server.vo.AppDependences;

/**
 * @author sfhq593 2016年1月9日12:51:58
 *
 * 
 **/
@Service
public class AppDependencesServiceImpl extends BaseServiceImpl<AppDependences> implements AppDependencesService {

	@Override
	public void updateCheckValid() {
		appDependencesDao.updateCheckValid();
	}

	@Override
	public void updateManualRole(AppDependences appDependences) {
		appDependencesDao.updateManualRole(appDependences);
	}

	@Override
	public List<AppDependences> queryProvider(String artifact) {
		return appDependencesDao.queryProvider(artifact);
	}

	@Override
	public List<AppDependences> queryComsumer(String artifact) {
		return appDependencesDao.queryComsumer(artifact);
	}

	@Override
	public List<AppDependences> queryAllByArtifactId(String artifact) {
		return appDependencesDao.queryAllByArtifactId(artifact);
	}


}
