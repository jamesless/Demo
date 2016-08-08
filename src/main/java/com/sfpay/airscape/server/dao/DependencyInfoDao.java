package com.sfpay.airscape.server.dao;

import java.util.List;

import com.sfpay.airscape.server.vo.auxiliary.DependencyInfo;

/**
 * 依赖关系入库接口
 * @author sfhq917 2016-01-04
 *
 */
public interface DependencyInfoDao {
	/**
	 * 依赖关系入库
	 * @param dependencyInfoList 依赖实体集合
	 */
	void addDependencyInfoBatch(List<DependencyInfo> dependencyInfoList);
	
	/**
	 * 删除依赖关系
	 */
	void deleleDependencyInfoList();
	
	/**
	 * 
	 * @return List<DependencyInfo>
	 */
	List<DependencyInfo> queryMvnanalysis();
}
