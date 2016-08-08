package com.sfpay.airscape.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sfpay.airscape.server.service.SvnRepositoryService;
import com.sfpay.airscape.server.vo.SvnRepository;

/**
 * @author sfhq593 2015年12月24日15:52:48
 * 
 */
@Service
public class SvnRepositoryServiceImpl extends BaseServiceImpl<SvnRepository> implements SvnRepositoryService {
	

	@Override
	public List<SvnRepository> queryFilterStatus() {
		List<SvnRepository> repositoryList = new ArrayList<>();
		List<SvnRepository> repositorys = svnRepositoryDao.queryAll();
		for (SvnRepository svnRepository : repositorys) {
			if(!"0".equals(svnRepository.getStatus())){
				repositoryList.add(svnRepository);
			}
		}
		return repositoryList;
	}

}
