package com.sfpay.airscape.server.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sfpay.airscape.server.dao.DepenAnalysisDao;
import com.sfpay.airscape.server.service.TaskWorkingRecService;
import com.sfpay.airscape.server.vo.AppDependences;
import com.sfpay.airscape.server.vo.DependencyAnalysis;
import com.sfpay.airscape.server.vo.TaskWorkingRec;
import com.sfpay.airscape.server.vo.auxiliary.DepenCircleRel;
import com.sfpay.airscape.server.vo.auxiliary.DepenGraphicNode;
import com.sfpay.airscape.server.vo.auxiliary.DepenSkeletonModule;


/**
 * 依赖关系分析 定时任务
 * @author sfhq1029 
 *
 */
public class DepenAnalysisTask {
	private static final Logger LOGGER =LoggerFactory.getLogger(DepenAnalysisTask.class);
	
	/**
	 * circle relations 
	 */
	//public  static List<String> circleRelList=null;
	
	@Resource
	private DepenAnalysisDao depenAnalysisDao;
	
	@Resource
	private TaskWorkingRecService taskWorkingRecService;
	
	/**任务运行记录*/
	public static final TaskWorkingRec TASK = new TaskWorkingRec();
	
/*	模块依赖关系分析
	分析模块A和模块B的依赖关系，仅针对WAR类型的模块
	遍历T_APPLICATION_INFO中所有war类型模块，查找对应的skeleton依赖关系模块（ROLE='C')，
	然后继续查找被依赖的skeleton模块是否在表T_DEPEN_ANALYSIS中被其他模块依赖（ROLE='P'，
	类型是war)， 如果存在B。 将A.war、B.war系统信息录入T_DEPEN_ANALYSIS中
	分析错误依赖
	war包循环依赖。遍历T_DEPEN_ANALYSIS中模块，递归查找循环依赖路径，并将各个节点入T_CIRCLE_DEPENDENCE表
	*/
	/**
	 * 
	 * 方法说明：<br>
	 *
	 */
	public void periodTaskqueryAll(){
		//1step 查找到所有 应用系统  独立模块  所依赖的skeleton 信息 
		/*
		select app.* from T_APPLICATION_INFO appinfo 
		left join  T_SUB_SYSTEM  sub on sub.sub_system_name=appinfo.sub_system_name
		left join  T_APPLICATION app on app.application_name=appinfo.application_name
		where app.packaging='war' and app.status='1'
		 */
		//T_APPLICATION_INFO
		List<DepenSkeletonModule> skeletons= depenAnalysisDao.queryAllSkeletenModual();
		LOGGER.info("SKELETON MODULES COUNT:"+skeletons.size());
		//System.out.println(skeletons.size());
		LOGGER.info("skeletons totals"+skeletons.size());
		
		TASK.setStartTime(new Date());
		TASK.setIsErr("0");
		TASK.setTaskType("DepenAnalysisTask");
		TASK.setStatus("1");
		taskWorkingRecService.add(TASK);
		
		//get oneside relations
		this.periodTaskUpdateAnalysisTable(skeletons);
		LOGGER.info("ANALYSIS FINISHED!");
		
		//get circle relations
		this.dealWithCircleRelations();
		
		//deal with error skeleton jar relation
		this.dealWithErrorJARRelations();
		LOGGER.info("skeleton依赖结束");
		
		TASK.setEndTime(new Date());
		TASK.setStatus("0");
		
		LOGGER.info("运行记录进库");
		taskWorkingRecService.update(TASK);
		
	}
	
	/**
	 * 
	 * 方法说明：<br>
	 * update into dependency
	 * @param skeletons  list
	 */
	public void periodTaskUpdateAnalysisTable(List<DepenSkeletonModule> skeletons){
		//1step 查找到所有应用系统  独立模块的信息 
		/*
		 */
		Map<String, String> parmmap=new HashMap<String, String>();
		
		this.doTruncateTable("T_DEPEN_ANALYSIS");
		DependencyAnalysis depAnalysisElement=null;
		
		
		for(int i=0;i<skeletons.size();i++){
			
			DepenSkeletonModule element=skeletons.get(i);
			parmmap.put("group",element.getDepenGroupId());
			parmmap.put("artifact",element.getDepenArtifactId());
			parmmap.put("version",element.getDepenVersion());
			List<AppDependences> appdeps=depenAnalysisDao.queryDependedByGAV(parmmap);
			if(appdeps.size()>0){
				AppDependences appdep= appdeps.get(0);
				depAnalysisElement=new DependencyAnalysis();
				//set war role="c"
				depAnalysisElement.setArtifactId(element.getArtifactId());
				depAnalysisElement.setGroupId(element.getGroupId());
				depAnalysisElement.setVersion(element.getVersion());
				//set jar 
				depAnalysisElement.setDepenOnGroupId(element.getDepenGroupId());
				depAnalysisElement.setDepenOnArtifactId(element.getDepenArtifactId());
				depAnalysisElement.setDepenOnVersion(element.getDepenVersion());
				//set war role="p"
				depAnalysisElement.setDepenGroupId(appdep.getGroupId());
				depAnalysisElement.setDepenArtifactId(appdep.getArtifactId());
				depAnalysisElement.setDepenVersion(appdep.getVersion());
				depAnalysisElement.setStatus("0");
				depenAnalysisDao.addDependencyRelation(depAnalysisElement);
			}
		}
		
		
	}
	
	/**
	 * 
	 * 方法说明：<br>
	 * clear table when ayalysis task begin
	 * @param name table name
	 */
	public void doTruncateTable(String name){
		//String name="ERROR_MESSAGE";
		depenAnalysisDao.trunkcatetable(name);
		
	}
	
	
	
	/**
	 * 
	 * 方法说明：<br>
	 * clear table when ayalysis task begin
	 * @param name
	 */
	public void dealWithCircleRelations(){
		//String name="ERROR_MESSAGE";
		List<DepenGraphicNode> allNodes=depenAnalysisDao.queryAllGraphicNode();
		List<DepenGraphicNode> allNodesrel=depenAnalysisDao.queryAllGraphicNodeRelation();
		DepenFindCycle.init(allNodes, allNodesrel);
		new DepenFindCycle().delWith();
		List<String> circleRelList = DepenFindCycle.CIRCLERELLIST;
		//insert circle relation table
		DepenCircleRel circle=null;
		String element="";
		LOGGER.info("CICLE DEPENDENCY COUNT:"+circleRelList.size());
		
		this.doTruncateTable("t_circle_dependence");
		for(int i=0;i<circleRelList.size();i++){
			element=circleRelList.get(i);
			if(!"".equals(element)){
				circle=new DepenCircleRel();
				circle.setDependenceId(element);
				circle.setType("war");
				circle.setStatus("0");
				circle.setCreateTime(new Date());
				circle.setUpdateTime(new Date());
				depenAnalysisDao.insertCircleTable(circle);
			}
		}
		
		
	}
	
	/**
	 * 
	 * 方法说明：<br>
	 * ERROR SKELETON JAR DPENDENCY RELATIONS  
	 */
	public void dealWithErrorJARRelations(){
		//String name="ERROR_MESSAGE";
		List<DepenSkeletonModule> allErrorRels=depenAnalysisDao.queryAllErrorJARRelations();
		//insert circle relation table
		DepenSkeletonModule element=null;
		DepenCircleRel circle=null;
		StringBuffer sb=new StringBuffer();
		sb.append("");
		//String procStr="";
		LOGGER.info("ERROR SKELETON JAR COUNTS:"+allErrorRels.size());
		for(int i=0;i<allErrorRels.size();i++){
			element=allErrorRels.get(i);
			if(element!=null){
				sb.setLength(0);
				sb.append(element.getGroupId()).append(":").append(element.getArtifactId()).append("@,").
				append(element.getDepenGroupId()).append(":").append(element.getDepenArtifactId()).append("@");
				circle=new DepenCircleRel();
				circle.setDependenceId(sb.toString());
				circle.setType("jar");
				circle.setStatus("0");
				circle.setCreateTime(new Date());
				circle.setUpdateTime(new Date());
				depenAnalysisDao.insertCircleTable(circle);
			}
		}
	}
	
	
	//just for test
	/*	
	public List<Zfappdep> queryAbc(String name){
		List<Zfappdep> alist=depenAnalysisDao.queryAbc(name);
		return  alist;
		
	}
	*/
}
