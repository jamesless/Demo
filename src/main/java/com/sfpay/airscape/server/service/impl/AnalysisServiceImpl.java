package com.sfpay.airscape.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.sfpay.airscape.server.dao.AppDependencesDao;
import com.sfpay.airscape.server.dao.DependencyInfoDao;
import com.sfpay.airscape.server.service.IAnalysisService;
import com.sfpay.airscape.server.vo.AppDependences;
import com.sfpay.airscape.server.vo.auxiliary.DependencyInfo;

/**
 * 依赖关系处理接口处理类
 * @author sfhq917 2015-12-19
 *
 */
@Service
public class AnalysisServiceImpl implements IAnalysisService{
	
	private static final Logger LOGGER =LoggerFactory.getLogger(AnalysisServiceImpl.class);
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	
	
	
	
	
	
	@Resource
	private DependencyInfoDao dependencyInfoDao;
	
	@Resource
	private AppDependencesDao appDependencesDao;
	
	/* (non-Javadoc)
	 * @see com.sfpay.airscape.server.service.IAnalysisService#createRelations(java.lang.String)
	 */
	@Override
	public void addRelations(String str) {
		
		if(null == str  || "".equals(str)){
			return ;
		}
		
		List<DependencyInfo> dependencyInfoList = new ArrayList<DependencyInfo>();
		
		String[] strArrayList = str.split(";");

		for(int i=0; i<strArrayList.length; i++){
			
			String[] tempStrArray = strArrayList[i].split(":");
			DependencyInfo dependencyInfo = new DependencyInfo();
			
			dependencyInfo.setCurrentProjectGroupId(tempStrArray[ZERO]);
			dependencyInfo.setCurrentProjectArtifact(tempStrArray[ONE]);
			dependencyInfo.setCurrentProjectVersion(tempStrArray[TWO]);
			
			dependencyInfo.setCurrentNodeGroupId(tempStrArray[THREE]);
			dependencyInfo.setCurrentNodeArtifact(tempStrArray[FOUR]);
			dependencyInfo.setCurrentNodeVersion(tempStrArray[FIVE]);
			
			dependencyInfo.setCurrentNodeParentGroupId(tempStrArray[SIX]);
			dependencyInfo.setCurrentNodeParentArtifact(tempStrArray[SEVEN]);
			dependencyInfo.setCurrentNodeParentVersion(tempStrArray[EIGHT]);
			
			dependencyInfoList.add(dependencyInfo);
		}
		
		// 依赖关系入库
		dependencyInfoDao.addDependencyInfoBatch(dependencyInfoList);
		LOGGER.info("AnalysisServiceImpl 依赖关系入库");
	}
	
	/* (non-Javadoc)
	 * @see com.sfpay.airscape.server.service.IAnalysisService#deleteRelations()
	 */
	@Override
	public void deleteRelations() {
		dependencyInfoDao.deleleDependencyInfoList();
	}

	/**
	 * 合并M_DEPEN_ANALYSIS(调用maven命令得到的依赖关系)信息到T_APP_DEPENDENCES(pom文件的依赖关系)中
	 */
	public void mergeRelations()
	{
		List<DependencyInfo> mlist = dependencyInfoDao.queryMvnanalysis();
		List<AppDependences> alist = appDependencesDao.queryAll();
		List<DependencyInfo> temp = new ArrayList<DependencyInfo>();
		List<AppDependences> list = new ArrayList<AppDependences>();
		if(CollectionUtils.isNotEmpty(mlist) && CollectionUtils.isNotEmpty(alist))
		{
			for(DependencyInfo dd : mlist)
			{
				boolean flag = false;
				for(AppDependences ad:alist)
				{
					if(dd.getCurrentProjectGroupId().equals(ad.getGroupId())
						&& dd.getCurrentProjectArtifact().equals(ad.getArtifactId())
						&& dd.getCurrentProjectVersion().equals(ad.getVersion())
						&& dd.getCurrentNodeGroupId().equals(ad.getDepenGroupId())
						&& dd.getCurrentNodeArtifact().equals(ad.getDepenArtifactId())
						&& dd.getCurrentNodeVersion().equals(ad.getDepenVersion()))
					{
						flag = true;
						break;
					}
				}
				if(!flag)
				{
					temp.add(dd);
				}
			}
		}
		if(CollectionUtils.isNotEmpty(temp))
		{
			for(DependencyInfo depen:temp)
			{
				AppDependences ade = new AppDependences();
				ade.setGroupId(depen.getCurrentProjectGroupId());
				ade.setArtifactId(depen.getCurrentProjectArtifact());
				ade.setVersion(depen.getCurrentProjectVersion());
				ade.setDepenGroupId(depen.getCurrentNodeGroupId());
				ade.setDepenArtifactId(depen.getCurrentNodeArtifact());
				ade.setDepenVersion(depen.getCurrentNodeVersion());
				ade.setPackaging("jar");
				ade.setRole("P");
				ade.setStatus("0");
				list.add(ade);
			}
		}
		if(CollectionUtils.isNotEmpty(list))
		{
			appDependencesDao.generateBatchInfoInIsolate(list);
		}
		
		LOGGER.info("AnalysisServiceImpl 合并依赖关系");
	}
	
}
