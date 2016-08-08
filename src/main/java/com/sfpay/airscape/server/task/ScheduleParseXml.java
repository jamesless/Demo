package com.sfpay.airscape.server.task;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import ch.qos.logback.classic.Logger;

import com.sfpay.airscape.server.service.AppDependencesService;
import com.sfpay.airscape.server.service.ApplicationService;
import com.sfpay.airscape.server.service.ErrorMessageService;
import com.sfpay.airscape.server.service.SvnRepositoryService;
import com.sfpay.airscape.server.service.TaskWorkingRecService;
import com.sfpay.airscape.server.utils.ParseXml;
import com.sfpay.airscape.server.vo.AppDependences;
import com.sfpay.airscape.server.vo.Application;
import com.sfpay.airscape.server.vo.ErrorMessage;
import com.sfpay.airscape.server.vo.SvnRepository;
import com.sfpay.airscape.server.vo.TaskWorkingRec;

/**
 * @author sfhq593 2016年1月5日13:52:23 定时调度解析pom文件程序
 */

public class ScheduleParseXml {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ScheduleParseXml.class);
	
	@Value("${pomRootPath}")
	private String path;
	@Resource
	private ApplicationService applicationService;

	@Resource
	private SvnRepositoryService svnRepositoryService;

	@Resource
	private AppDependencesService appDependencesService;

	@Resource
	private ErrorMessageService errorMessageService;

	@Resource
	private TaskWorkingRecService taskWorkingRecService;
	
	/**任务运行记录*/
	public static final TaskWorkingRec TASK = new TaskWorkingRec();

	/**
	 * 分析pom文件
	 */
	@SuppressWarnings("unchecked")
	public void schedule(){
		LOGGER.info("schedule任务开始执行");
		TASK.setStartTime(new Date());
		TASK.setIsErr("0");
		TASK.setTaskType("ScheduleParseXml");
		TASK.setStatus("1");
		
		taskWorkingRecService.add(TASK);
		
		try {
			List<SvnRepository> svnRepositories;
			Map<String, List<?>> parseSvnDirectory;
			List<Application> applications = null;
			List<AppDependences> appDependences = null;
			List<ErrorMessage> errorMessages = null;
			svnRepositories = svnRepositoryService.queryFilterStatus();
			ParseXml.init(svnRepositories, path);
			parseSvnDirectory = ParseXml.parseSvnDirectory();
			applications = (List<Application>) parseSvnDirectory.get("Application");
			appDependences = (List<AppDependences>) parseSvnDirectory
					.get("AppDependences");
			errorMessages = (List<ErrorMessage>) parseSvnDirectory.get("Error");
			
			if(errorMessages.size() > 0){
				TASK.setIsErr("1");
			}

			/**
			 * 根據解析的pom文件，對應用進行入庫，如果應用存在則進行更新,否則執行插入操作
			 * 
			 */

			for (Application application : applications) {
				List<Application> applicationBeanList = applicationService
						.query(application);
				if (applicationBeanList.size() == 0) {
					LOGGER.info("application添加入库");
					applicationService.add(application);
				} else {
					Application applicationBean = applicationBeanList.get(0);
					if(applicationBean.getOriginVersion() != null){
						application.setOriginVersion(applicationBean.getOriginVersion());
					}
					LOGGER.info("application更新入库");
					applicationService.update(application);
				}
			}

			/**
			 * 對更新時間進行判斷 ,如果小于當前時間,表示以棄用
			 */
			applicationService.updateCheckValid();

			/**
			 * 解析POM依赖关系,对依赖表进行入库操作,如果依赖的包,存在则进行更新操作,否则进行插入操作
			 * 
			 */

			for (AppDependences appDependence : appDependences) {
				List<AppDependences> appDependencesBeanList = appDependencesService
						.query(appDependence);
				if (appDependencesBeanList.size() == 0) {
					LOGGER.info("appDependence添加入库");
					appDependencesService.add(appDependence);
				} else {
					AppDependences appDependencesBean = appDependencesBeanList.get(0);
					if(appDependencesBean.getManualRole() != null){
						appDependence.setManualRole(appDependencesBean.getManualRole());
					}
					LOGGER.info("appDependence更新入库");
					appDependencesService.update(appDependence);
				}
			}

			/**
			 * 對更新時間進行判斷 ,如果小于當前時間,表示以棄用
			 */

			appDependencesService.updateCheckValid();

			/**
			 * 记录pom文件解析过程中,所遇到的错误,并入库,如果发现错误存在,则进行更新操作,否则进行插入操作
			 * 
			 */
			for (ErrorMessage errorMessage : errorMessages) {
				List<ErrorMessage> errorMesageList = errorMessageService.query(errorMessage);
				if (errorMesageList.size() == 0) {
					LOGGER.info("错误信息添加入库");
					errorMessageService.add(errorMessage);
				} else {
					LOGGER.info("错误信息更新入库");
					errorMessageService.update(errorMessage);
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.toString());
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		
		TASK.setEndTime(new Date());
		TASK.setStatus("0");
		
		LOGGER.info("运行记录进库");
		taskWorkingRecService.update(TASK);
	}
}
