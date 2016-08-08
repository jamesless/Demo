package com.sfpay.airscape.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ch.qos.logback.classic.Logger;

import com.sfpay.airscape.server.decorate.Dependences;
import com.sfpay.airscape.server.decorate.Skeleton;
import com.sfpay.airscape.server.decorate.War;
import com.sfpay.airscape.server.service.AppDependencesService;
import com.sfpay.airscape.server.vo.AppDependences;
import com.sfpay.airscape.server.vo.auxiliary.ErrorInfo;

/**
 * 
 * @author sfhq1576
 *
 */
@Controller
public class DependencesControl {
	@Resource
	private AppDependencesService appDependencesService;
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(DependencesControl.class);

	/**
	 * 
	 * @param appDependences appDependences
	 */
	@RequestMapping(value = "/dependences/skeleton", method = RequestMethod.PUT)
	@ResponseBody
	public void updateManualRole(@RequestBody AppDependences appDependences) {
		logger.info("手动更新role状态");
		appDependencesService.updateManualRole(appDependences);
	}
	
	/**
	 * 
	 * @param relation relation
	 * @param artifactId artifactId
	 * @return List<Dependences>
	 */
	@RequestMapping(value = "/dependences/skeleton/{relation}/{artifactId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Dependences> query(@PathVariable String relation,@PathVariable String artifactId) {
		List<Dependences> dependences = null;
		List<AppDependences> appDepends = new ArrayList<AppDependences>();
		
		String afId = "ALL".equals(artifactId)?"":artifactId;
		
		if("ALL".equals(relation)){
			logger.info("查询所有依赖关系");
			appDepends = appDependencesService.queryAllByArtifactId(afId);
		}else if("ONLY-COMSUMER".equals(relation)){
			logger.info("查询依赖");
			appDepends = appDependencesService.queryComsumer(afId);
		}else if("ONLY-PROVIDER".equals(relation)){
			logger.info("查询被依赖");
			appDepends = appDependencesService.queryProvider(afId);
		}
		dependences = new ArrayList<Dependences>(packagingDepends(appDepends));
		
		return dependences;
	}
	
	/**
	 * 
	 * @param appDepends appDepends
	 * @return List<Dependences>
	 */
	private List<Dependences> packagingDepends(List<AppDependences> appDepends){
		List<Dependences> dependencesList = new ArrayList<Dependences>();
		Skeleton skeleton;
		War war;
		Dependences dependences;
		
		for (AppDependences appDependences : appDepends) {
			skeleton = new Skeleton();
			war = new War();
			dependences = new Dependences();
			
			dependences.setDependenceId(appDependences.getDependenceId());
			skeleton.setDepenArtifactId(appDependences.getDepenArtifactId());
			skeleton.setDepenGroupId(appDependences.getDepenGroupId());
			skeleton.setDepenVersion(appDependences.getDepenVersion());
			war.setArtifactId(appDependences.getArtifactId());
			war.setGroupId(appDependences.getGroupId());
			war.setVersion(appDependences.getVersion());
			dependences.setRelation(appDependences.getRole());
			dependences.setSkeleton(skeleton);
			dependences.setWar(war);
			dependencesList.add(dependences);
		}
		return dependencesList;
	}
	
	
	/**
	 * 
	 * @param e exception
	 * @return ErrorInfo
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo exception(Exception e) {
		logger.error(e.toString());
		return new ErrorInfo("", e.getMessage());
	}
}
