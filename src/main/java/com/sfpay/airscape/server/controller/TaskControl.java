package com.sfpay.airscape.server.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfpay.airscape.server.task.DepenAnalysisTask;
import com.sfpay.airscape.server.task.PomAnalysisTaskJob;
import com.sfpay.airscape.server.task.ScheduleParseXml;

/**
 * 
 * @author sfhq1576
 *
 */
@Controller
public class TaskControl {
	@Resource
	PomAnalysisTaskJob pomAnalysisTaskJob;
	@Resource
	ScheduleParseXml scheduleParseXml;
	@Resource
	DepenAnalysisTask depenAnalysisTask;
	
	/**
	 * 分析pom.xml，生存结果文件，并解析入库
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/task/analysis", method = RequestMethod.GET)
	@ResponseBody
	public void pomAnalysisTaskJobStart() throws IOException, InterruptedException, URISyntaxException {
			pomAnalysisTaskJob.analysisDependency();
	}
	
	/**
	 * 解析pom文件程序
	 */
	@RequestMapping(value = "/task/schedule", method = RequestMethod.GET)
	@ResponseBody
	public void scheduleParseXmlStart() {
		scheduleParseXml.schedule();
	}
	
	/**
	 * 依赖关系分析
	 */
	@RequestMapping(value = "/task/depen", method = RequestMethod.GET)
	@ResponseBody
	public void depenAnalysisTaskStart() {
		depenAnalysisTask.periodTaskqueryAll();
	}
	
}
