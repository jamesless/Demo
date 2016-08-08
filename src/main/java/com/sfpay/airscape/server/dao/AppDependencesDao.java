package com.sfpay.airscape.server.dao;

import java.util.List;

import com.sfpay.airscape.server.vo.AppDependences;

/**
 * 
 * @author sfhq1576
 *
 */
public interface AppDependencesDao extends BaseDao<AppDependences>{


	/**
	 * 
	 */
	void updateCheckValid();
	
	/**
	 * 
	 * @param appDependences appDependences
	 */
	void updateManualRole(AppDependences appDependences);
	
	/**
	 * 
	 * @param artifact artifact
	 * @return List<AppDependences>
	 */
	List<AppDependences> queryProvider(String artifact);
	
	/**
	 * 
	 * @param artifact artifact
	 * @return List<AppDependences>
	 */
	List<AppDependences> queryComsumer(String artifact);
	
	/**
	 * 
	 * @param artifact artifact
	 * @return List<AppDependences>
	 */
	List<AppDependences> queryAllByArtifactId(String artifact);
	
	/**
	 * 
	 * @param list list
	 */
	void generateBatchInfoInIsolate(List<AppDependences> list);
}