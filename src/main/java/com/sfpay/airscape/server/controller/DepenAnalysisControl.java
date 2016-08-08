package com.sfpay.airscape.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;














import com.sfpay.airscape.server.service.DepenAnalysisService;
import com.sfpay.airscape.server.utils.DependPrint;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationIncorrect;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationRelation;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationsRelation;
import com.sfpay.airscape.server.vo.auxiliary.SubSystemAnalysis;

/**
 * @author sfhq
 */

@RequestMapping("/dependence")
@Controller
public class DepenAnalysisControl {

	@Resource
	private DepenAnalysisService depenAnalysisService;
	
	private DependPrint dependPrint = new DependPrint();

	/**
	 * 
	 * @param subSystemName subSystemName
	 * @param relationship relationship
	 * @return List<SubSystemAnalysis>
	 */
	@RequestMapping(value = "/subsystem/{subSystemName}/{relationship}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,List> getSubsysRelations(
			@PathVariable("subSystemName") String subSystemName,
			@PathVariable("relationship") String relationship) {
		HashMap<String, List> resultMap = new HashMap<>();	//结果集
		HashMap<String, String> nodeMap = new HashMap<>();	//节点信息
		List<Map<String, String>> arrList = new ArrayList<>();	//节点列表
		
		String sourceID;
		String targetID;
		String sourceLevel;
		String targetLevel;
		
		if (!"1".equals(relationship) && !"2".equals(relationship)
				&& !"0".equals(relationship) && !"".equals(subSystemName)) {
			return null;
		}

		List<SubSystemAnalysis> relationList = getRelation(subSystemName, relationship);
		
		for (SubSystemAnalysis subSystemAnalysis : relationList) {
			
			sourceID = subSystemAnalysis.getSubSystemEname();     
			targetID = subSystemAnalysis.getDepSubSystemEname();     
			sourceLevel = subSystemAnalysis.getSubSystemLevel();  
			targetLevel = subSystemAnalysis.getDepSubSystemLevel();  
			
			Map<String, String> depenMap = dependPrint.getDependInfo(sourceID, targetID, sourceLevel, targetLevel);
			
			nodeMap.put(depenMap.get("sourceID"),depenMap.get("sourceLevel"));
			nodeMap.put(depenMap.get("targetID"),depenMap.get("targetLevel"));
			
			//清除的不需要的节点
			depenMap.remove("sourceLevel");
			depenMap.remove("targetLevel");
			arrList.add(depenMap);
		}

		//把节点信息添加到节点名称列表中
		List<Map<String, String>> nodeList = dependPrint.getNode(nodeMap);
		
		//整合节点信息
		resultMap.put("list", relationList);
		resultMap.put("nodes", nodeList);
		resultMap.put("relation", arrList);
		return resultMap;

	}

	/**
	 * 获取依赖与被依赖关系
	 * @param subSystemName
	 * @param relationship
	 * @return
	 */
	private List<SubSystemAnalysis> getRelation(String subSystemName, String relationship) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("subSystemName", subSystemName);
		map.put("relationship", relationship);
		return depenAnalysisService.getSubsysRelations(map);
	}

	/**
	 * 应用模块依赖
	 * @param map map
	 * @return List<ApplicationRelation>
	 */
	@RequestMapping(value = "/application", method = RequestMethod.PUT)
	@ResponseBody
	public HashMap<String, List> getApplicationRelations(@RequestBody Map map) {
		HashMap<String, List> resultMap = new HashMap<>();	//结果集
		HashMap<String, String> nodeMap = new HashMap<>();	//节点信息
		List<Map<String, String>> arrList = new ArrayList<>();	//节点列表
		
		String sourceID;
		String targetID;
		String sourceLevel;
		String targetLevel;
		
		String subSystemName = (String) map.get("subSystemName");
		String relationship = (String) map.get("relationship");
		String applicationName = (String) map.get("applicationName");
		
		if (!"1".equals(relationship) && !"2".equals(relationship)
				&& !"0".equals(relationship) && !"".equals(subSystemName)) {
			return null;
		}
		
		List<ApplicationRelation> applicationRelationList =  getApplicationRelation(subSystemName,
				relationship, applicationName);
		

		for (ApplicationRelation appRelation : applicationRelationList) {
			
			sourceID = appRelation.getApplicationName();
			targetID = appRelation.getDepApplicationName();
			sourceLevel = appRelation.getLevel();
			targetLevel = appRelation.getDepLevel();
			
			Map<String, String> depenMap = dependPrint.getDependInfo(sourceID, targetID, sourceLevel, targetLevel);
			
			nodeMap.put(depenMap.get("sourceID"),depenMap.get("sourceLevel"));
			nodeMap.put(depenMap.get("targetID"),depenMap.get("targetLevel"));
			
			//清除的不需要的节点
			depenMap.remove("sourceLevel");
			depenMap.remove("targetLevel");
			arrList.add(depenMap);
		}

		//把节点信息添加到节点名称列表中
		List<Map<String, String>> nodeList = dependPrint.getNode(nodeMap);
		
		//整合节点信息
		resultMap.put("list", applicationRelationList);
		resultMap.put("nodes", nodeList);
		resultMap.put("relation", arrList);
		return resultMap;

	}

	/**
	 * 获取应用模块依赖关系
	 * @param subSystemName
	 * @param relationship
	 * @param applicationName
	 * @return
	 */
	private List<ApplicationRelation> getApplicationRelation(String subSystemName,
			String relationship, String applicationName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("subSystemName", subSystemName);
		map.put("relationship", relationship);
		map.put("applicationName", applicationName);
		return depenAnalysisService.getApplicationRelations(map);
	}

	/**
	 * 
	 * @return List<List<ApplicationIncorrect>>
	 */
	@RequestMapping(value = "/incorrect", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<Map<String, String>>> getIncorrectRelations() {
		return depenAnalysisService.getIncorrectRelations();

	}

	/**
	 * 
	 * @param applicationsRelation applicationsRelation
	 * @param depApplicationsRelation depApplicationsRelation
	 * @param relationship relationship
	 * @return List<ApplicationsRelation>
	 */
	public List<ApplicationsRelation> getApplicationRelations(
			@PathVariable("applicationsRelation") ApplicationsRelation applicationsRelation,
			@PathVariable("depApplicationsRelation") ApplicationsRelation depApplicationsRelation,
			@PathVariable("relationship") String relationship) {
		if (!"1".equals(relationship) && !"2".equals(relationship)
				&& !"3".equals(relationship)) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("relationship", relationship);
		map.put("groupId", applicationsRelation.getGroupId());
		map.put("artifactId", applicationsRelation.getArtifactId());
		map.put("depGroupId", applicationsRelation.getDepGroupId());
		map.put("depArtifactId", applicationsRelation.getDepArtifactId());

		return depenAnalysisService.getApplicationsRelations(map);

	}

}
