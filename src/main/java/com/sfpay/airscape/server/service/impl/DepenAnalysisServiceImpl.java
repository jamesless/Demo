package com.sfpay.airscape.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.sfpay.airscape.server.service.DepenAnalysisService;
import com.sfpay.airscape.server.utils.DependPrint;
import com.sfpay.airscape.server.vo.DepenAnalysis;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationIncorrect;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationRelation;
import com.sfpay.airscape.server.vo.auxiliary.ApplicationsRelation;
import com.sfpay.airscape.server.vo.auxiliary.SubSystemAnalysis;

/**
 * 依赖关系分析接口
 * 
 * @author sfhq
 * 
 */
@Service
public class DepenAnalysisServiceImpl extends BaseServiceImpl<DepenAnalysis> implements DepenAnalysisService{
	private DependPrint dependPrint = new DependPrint();

	@Override
	public List<ApplicationRelation> getApplicationRelations(
			Map<String, String> map) {
		// 过滤自身依赖
		return depenAnalysisDao.getApplicationRelations(map);
	}

	@Override
	public List<SubSystemAnalysis> getSubsysRelations(Map<String, String> map) {
		return  depenAnalysisDao.getSubsysRelations(map);
	}

	/**
	 * 获取错误依赖关系
	 */
	@Override
	public Map<String,List<Map<String, String>>> getIncorrectRelations() {
		Map<String,List<Map<String, String>>> jsonInfo = new HashMap<>();	//json格式处理对象
		List<String> circleRelListJar = null;
		circleRelListJar = depenAnalysisDao.getcircleRelListJar();

		if (circleRelListJar != null) {
			jsonInfo = getNodeDepend(circleRelListJar);
		}
		return jsonInfo;
	}

	/**
	 * 获取节点依赖关系
	 * @param circleRelListJar
	 * @param appList
	 * @param nodeMap
	 * @return 
	 */
	private Map<String, List<Map<String, String>>> getNodeDepend(List<String> circleRelListJar) {
		Map<String,List<Map<String, String>>> resultMap = new HashMap<>();	//结果集
		List<Map<String, String>> mapArr = new ArrayList<>();	//节点名称列表
		List<Map<String,String>> appList = new ArrayList<>();	//节点依赖关系
		Map<String,String> nodeMap = new HashMap<String,String>();	//节点信息
		
		String sourceID;
		String targetID;
		String sourceLevel;
		String targetLevel;
		
		for (String circleRelImplJar : circleRelListJar) {
			List<ApplicationIncorrect> applicationIncorrectList = new ArrayList<ApplicationIncorrect>();
			if (!"".equals(circleRelImplJar)) {
				
				//获取错误依赖双边关系信息
				applicationIncorrectList = getApplicationIncorrectList(
						circleRelImplJar, "jar");
				if (applicationIncorrectList != null && applicationIncorrectList.size() == 2) {
					
					sourceID = applicationIncorrectList.get(0).getApplicationName();
					targetID = applicationIncorrectList.get(1).getApplicationName();
					sourceLevel = applicationIncorrectList.get(0).getLevel();       
					targetLevel = applicationIncorrectList.get(1).getLevel();       
					
					//获取错误依赖关系节点信息
					Map<String, String> depenMap = 
							dependPrint.getDependInfo(sourceID, targetID, sourceLevel, targetLevel);
					
					//去除重名节点
					nodeMap.put(depenMap.get("sourceID"),depenMap.get("sourceLevel"));
					nodeMap.put(depenMap.get("targetID"),depenMap.get("targetLevel"));
					
					//清除的不需要的节点
					depenMap.remove("sourceLevel");
					depenMap.remove("targetLevel");
					appList.add(depenMap);
				}
				
			}
			
		}
		//把节点信息添加到节点名称列表中
		mapArr = dependPrint.getNode(nodeMap);
		
		//整合节点信息与错误依赖关系
		resultMap.put("nodes", mapArr);
		resultMap.put("relation", appList);
		return resultMap;
	}
	

	//获取错误依赖双边关系信息
	private List<ApplicationIncorrect> getApplicationIncorrectList(
			String string, String packageInfo) {
		List<ApplicationIncorrect> applicationIncorrectList = new ArrayList<ApplicationIncorrect>();

		if (!"".equals(string) && string.contains(",")) {
			String[] circleRelImpl = string.split(",");
			for (int i = 0; i < circleRelImpl.length; i++) {
				String circleRel = circleRelImpl[i];
				if (!"".equals(circleRel) && circleRel.contains(":")) {
					if (circleRel.endsWith("@")) {
						String[] str = circleRel.split("@");
						circleRel = str[0];
					}
					ApplicationIncorrect applicationIncorrect = null;
					String[] gaID = circleRel.split(":");
					String groupId = gaID[0];
					String artifactId = gaID[1];
					Map<String, String> map = new HashMap<String, String>();
					map.put("groupId", groupId);
					map.put("artifactId", artifactId);
					map.put("packaging", packageInfo);
					// 查询数据库
					applicationIncorrect = depenAnalysisDao
							.getApplicationIncorrect(map);
					if (applicationIncorrect != null) {
						applicationIncorrectList.add(applicationIncorrect);
					}
				}
			}

		}
		return applicationIncorrectList;
	}

	@Override
	public List<ApplicationsRelation> getApplicationsRelations(
			Map<String, String> map) {
		// 过滤自身依赖
		return depenAnalysisDao.getApplicationsRelations(map);
	}

}
