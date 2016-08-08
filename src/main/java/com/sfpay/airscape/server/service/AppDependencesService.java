package com.sfpay.airscape.server.service;

import java.util.List;

import com.sfpay.airscape.server.vo.AppDependences;

/**
 * @author sfhq593 2016年1月9日12:50:01
 */
public interface AppDependencesService extends BaseService<AppDependences> {


	/**
	 * TODO 更新STATUS状态,当依赖记录没有进行更新时,表明该记录已经被弃用了
	 */
	void updateCheckValid();
	
	/**
	 * 更新手动更新字段manualRole
	 * @param appDependences appDependences
	 */
	void updateManualRole(AppDependences appDependences);
	
	/**
	 * 模糊查询provider的相关的数据
	 * @param artifact artifact
	 * @return List
	 */
	List<AppDependences> queryProvider(String artifact);
	
	/**
	 * 模糊查询consumer的相关字数据
	 * @param artifact artifact
	 * @return List
	 */
	List<AppDependences> queryComsumer(String artifact);
	
	/**
	 * 模糊查询所有包含artifactId的数据
	 * @param artifact artifact
	 * @return 	List
	 */
	List<AppDependences> queryAllByArtifactId(String artifact);

}
