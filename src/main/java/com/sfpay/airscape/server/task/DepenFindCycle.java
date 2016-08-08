/**
 * =================================================================
 * 版权所有 2011-2020 泰海网络支付服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；
 * 不允许对程序代码以任何形式任何目的的再发布
 * =================================================================
 */
package com.sfpay.airscape.server.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sfpay.airscape.server.task.DepenAnalysisTask;
import com.sfpay.airscape.server.vo.auxiliary.DepenGraphicNode;

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
 * @author sfhq1029
 * 
 * CreateDate: 2016-1-12
 */
public class DepenFindCycle {
	private static final Logger LOGGER =LoggerFactory.getLogger(DepenAnalysisTask.class);
	/**
	 * APPLIST 应用节点
	 */
	public static final List<String> APPLIST = new ArrayList<String>();
	/**
	 * APPRELLIST 节点关系 
	 */
	public static final List<AppRel> APPRELLIST = new ArrayList<AppRel>();
	/**
	 * CIRCLERELLIST cycle relation
	 */
	public static final List<String> CIRCLERELLIST = new ArrayList<String>();

	/**
	 * 
	 * 方法说明：<br>
	 * obtain all  next level nodes
	 * @param appGA group&artifact 
	 * @param appRelList list
	 * @return ref relations
	 */
	public List<String> findRefServ(String appGA, List<AppRel> appRelList) {
		List<String> refGAList = new ArrayList<String>();
		for (int i = 0; i < appRelList.size(); i++) {
			AppRel rel = (AppRel) appRelList.get(i);
			if (appGA.equals(rel.getAppGA())) {
				refGAList.add(rel.getProviderGA());
			}
		}
		return refGAList;
	}
	
	
	/**
	 * 
	 * 方法说明：<br>
	 * Recall method
	 * @param appGA   group&artifact
	 * @param cyclePath  access path
	 * @param appRelList list
	 */
	public void findCycleServ(String appGA, String cyclePath, List<AppRel> appRelList) {
		String id = appGA;
		List<String> refGAList = findRefServ(appGA, appRelList);
		if (!(refGAList.size() > 0)) {
			return;
		} else if (cyclePath.indexOf(appGA) > 0) {
			LOGGER.error("#####################1:"+cyclePath + "," + id);
			String temp=cyclePath + "," + id;
			LOGGER.error("#####################2:"+temp);
			if(temp.length()>1){
				temp=temp.substring(1);
			}
			LOGGER.error("#####################3:"+temp);
			DepenAnalysisTask.TASK.setIsErr("1");
			CIRCLERELLIST.add(temp);
			return;
		} else {
			String cyclePathtemp = cyclePath + "," + id;
			for (int j = 0; j < refGAList.size(); j++) {
				String childGAId = (String) refGAList.get(j);
				findCycleServ(childGAId, cyclePathtemp, appRelList);
			}
		}
	}
	
	/**
	 * 
	 * 方法说明：<br>
	 * START POINT
	 */
	public  void delWith() {
		//check every node
		for (int i = 0; i < APPLIST.size(); i++) {
			String appGA = (String) APPLIST.get(i);
			findCycleServ(appGA, "", APPRELLIST);
		}
	/*	
		for (int i = 0; i < appList.size(); i++) {
			String appGA = (String) appList.get(i);
			findCycleServ(appGA, "", appRelList);
		}
		*/
	}
	
	/**
	 * 
	 * 方法说明：<br>
	 * 初始化节点和关系
	 * @param nodes graphic
	 * @param relations list
	 */
	public  static  void init(List<DepenGraphicNode> nodes,List<DepenGraphicNode> relations){
		DepenGraphicNode node=null;
		String tempG="";
		String tempA="";
		APPLIST.clear();
		APPRELLIST.clear();
		CIRCLERELLIST.clear();
		/**
		 * 初始化节点
		 */
		LOGGER.info("初始化节点开始");
		for(int i=0;i<nodes.size();i++){
			node=nodes.get(i);
			tempG=node.getGroupId();
			tempA=node.getArtifactId();
			if(tempG!=null&&tempA!=null){
				APPLIST.add(tempG+":"+tempA+"@");
			}
		}
		String tempdepG="";
		String tempdepA="";
		AppRel relation=null;
		/**
		 * 初始化关系
		 */
		LOGGER.info("初始化关系开始");
		for(int j=0;j<relations.size();j++){
			node=relations.get(j);
			tempG=node.getGroupId();
			tempA=node.getArtifactId();
			tempdepG=node.getDepenGroupId();
			tempdepA=node.getDepenArtifactId();
			if(tempG!=null&&tempA!=null&&tempdepG!=null&&tempdepA!=null){
				relation=new AppRel(tempG+":"+tempA+"@",tempdepG+":"+tempdepA+"@");
				APPRELLIST.add(relation);
			}
		}
	}
}

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
 * @author sfhq1029
 * 
 * CreateDate: 2016-1-12
 */
class AppRel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String appGA;
	private  String providerGA;

	public AppRel(){
		
	}
	/**
	 * @param appGA
	 * @param providerGA
	 */
	public AppRel(String appGA, String providerGA) {
		super();
		this.appGA = appGA;
		this.providerGA = providerGA;
	}
	public String getAppGA() {
		return appGA;
	}
	public void setAppGA(String appGA) {
		this.appGA = appGA;
	}
	public String getProviderGA() {
		return providerGA;
	}
	public void setProviderGA(String providerGA) {
		this.providerGA = providerGA;
	}
	
	
}



