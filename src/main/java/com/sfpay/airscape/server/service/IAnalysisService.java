package com.sfpay.airscape.server.service;



/**
 * 依赖关系处理接口
 * @author sfhq917 2015-12-19
 *
 */
public interface IAnalysisService {
	
	/**
	 * 存储依赖关系
	 * @param str 依赖关系
	 */
	public void addRelations(String str);
	
	/**
	 * 删除依赖关系
	 */
	public void deleteRelations();
	
	/**
	 * 合并依赖关系
	 */
	public void mergeRelations();
	
}
