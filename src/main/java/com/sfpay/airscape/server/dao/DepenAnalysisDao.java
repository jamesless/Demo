package com.sfpay.airscape.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sfpay.airscape.server.vo.AppDependences;
import com.sfpay.airscape.server.vo.DepenAnalysis;
import com.sfpay.airscape.server.vo.DependencyAnalysis;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationIncorrect;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationRelation;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationsRelation;
import com.sfpay.airscape.server.vo.auxiliary.DepenCircleRel;
import com.sfpay.airscape.server.vo.auxiliary.DepenGraphicNode;
import com.sfpay.airscape.server.vo.auxiliary.DepenSkeletonModule;
import com.sfpay.airscape.server.vo.auxiliary.SubSystemAnalysis;

/**
 * 
 * @author sfhq1576
 *
 */
public interface DepenAnalysisDao extends BaseDao<DepenAnalysis>{
	
	/**
	 * 查询所有含有skeleton的模块
	 * 方法说明：<br>
	 * 
	 * @return List
	 */
	List<DepenSkeletonModule> queryAllSkeletenModual();

	/**
	 * 方法说明：<br>
	 * 
	 * @param paramname NAME
	 */
	void trunkcatetable(@Param("paramname")String paramname);
	
	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @param parmmap PARM
	 * @return LIST
	 */
	List<AppDependences> queryDependedByGAV(Map<String,String> parmmap);

	/**
	 * 方法说明：<br>
	 * 
	 * @param depAnalysisElement ELEMENT
	 */
	void addDependencyRelation(DependencyAnalysis depAnalysisElement);


	/**
	 * 方法说明：<br>
	 * 
	 * @param name
	 * @return
	 */
/*	List<Zfappdep> queryAbc(@Param("paramname") String paramname);*/

	/**
	 * 方法说明：<br>
	 * 
	 * @return LIST
	 */
	List<DepenGraphicNode> queryAllGraphicNode();
	
	/**
	 * 方法说明：<br>
	 * 
	 * @return LIST
	 */
	List<DepenGraphicNode> queryAllGraphicNodeRelation();
	
	
	/**
	 * 方法说明：<br>
	 * @param circle  ELEMENT
	 * 
	 */
	void insertCircleTable(DepenCircleRel circle);
	
	/**
	 * 方法说明：<br>
	 * all errror jar dependency relations
	 * @return LIST
	 */
	List<DepenSkeletonModule> queryAllErrorJARRelations();

	
	/**
	---------------------------------------------------------
	*/
	/**
	 * 方法说明：<br>
	 * 
	 * @param map PARM
	 * @return LIST
	 */
	List<ApplicationRelation> getApplicationRelations(Map<String, String> map);

	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @param map PARM
	 * @return    LIST
	 */
	List<SubSystemAnalysis> getSubsysRelations(Map<String, String> map);
	/**
	 * 方法说明：<br>
	 * 
	 * @param map PARM
	 * @return ELEMENT
	 */
	ApplicationIncorrect getApplicationIncorrect(Map<String, String> map);

	/**
	 * 方法说明：<br>
	 * 
	 * @return LIST
	 */
	List<String> getcircleRelListWar();
	/**
	 * 方法说明：<br>
	 * 
	 * @return LIST
	 */
	List<String> getcircleRelListJar();
	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @param map PARM
	 * @return LIST
	 */
	List<ApplicationsRelation> getApplicationsRelations(Map<String, String> map);
	
}