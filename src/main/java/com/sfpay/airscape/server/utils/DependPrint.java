package com.sfpay.airscape.server.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sfpay.airscape.server.vo.auxiliary.ApplicationIncorrect;

/** 
 * @author  sfhq1588 
 * @date 创建时间：2016年7月15日 上午10:43:56 
 * @description 依赖于被依赖json格式封装
 */
public class DependPrint {
	
	/**
	 * 获取节点列表
	 * @param nodeMap
	 * @return
	 */
	public List<Map<String, String>> getNode(Map<String,String> nodeMap) {
		
		List<Map<String, String>> mapArr = new ArrayList<>();
		//把节点信息添加到节点名称列表中
		for (String name : nodeMap.keySet()) {
			Map<String, String> tempMap = new HashMap<>();
			tempMap.put("label", name);
			tempMap.put("id", name);
			tempMap.put("level", nodeMap.get(name));
			mapArr.add(tempMap);
		}
		
		return mapArr;
	}
	
	/**
	 * 获取节点依赖关系
	 * @param sourceID
	 * @param targetID
	 * @param sourceLevel
	 * @param targetLevel
	 * @return
	 */
	public Map<String, String> getDependInfo(String sourceID,String targetID,
			String sourceLevel,String targetLevel){
		Map<String,String> dependInfo = new HashMap<String,String>();
		dependInfo.put("sourceID",sourceID);
		dependInfo.put("targetID",targetID);
		dependInfo.put("sourceLevel",sourceLevel);
		dependInfo.put("targetLevel",targetLevel);
		return dependInfo;
	}

}
