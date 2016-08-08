package com.sfpay.airscape.server.service;

import java.util.List;
import java.util.Map;

import com.sfpay.airscape.server.vo.DepenAnalysis;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationIncorrect;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationRelation;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationsRelation;
import com.sfpay.airscape.server.vo.auxiliary.SubSystemAnalysis;

/**
 * 
 * 类说明：<br>
 * 
 * 
 * <p>
 * 详细描述：<br>
 * 
 * 
 * </p>
 * 
 * @author sfhq
 * 
 * CreateDate: 2016-1-12
 */
public interface DepenAnalysisService extends BaseService<DepenAnalysis>{
	/**
	 * 
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
	 * @return LIST
	 */
	List<SubSystemAnalysis> getSubsysRelations(Map<String, String> map);
	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @return LIST
	 */
	Map<String, List<Map<String, String>>> getIncorrectRelations();
	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @param map PARM 
	 * @return LIST 
	 */
	List<ApplicationsRelation> getApplicationsRelations(Map<String, String> map);
	
}
