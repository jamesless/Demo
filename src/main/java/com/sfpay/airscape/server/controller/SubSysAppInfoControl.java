package com.sfpay.airscape.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.sfpay.airscape.server.service.SubSysAppInfoService;
import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.SubSysAppInfo;
import com.sfpay.airscape.server.vo.auxiliary.ErrorInfo;
import com.sfpay.airscape.server.vo.auxiliary.PageExt;

/**
 * 
 * @author sfhq1576
 *
 */
@Controller
public class SubSysAppInfoControl {
	@Resource
	SubSysAppInfoService subSysAppInfoService;
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(SubSysAppInfoControl.class);

	/**
	 * 
	 * @param applicationId applicationId
	 * @return SubSysAppInfo
	 */
	@RequestMapping(value = "/appRelationship/{applicationId}", method = RequestMethod.GET)
	@ResponseBody
	public SubSysAppInfo query(@PathVariable String applicationId) {
		SubSysAppInfo subSysAppInfo;
		subSysAppInfo = subSysAppInfoService.query(applicationId);
		return subSysAppInfo;
	}

/*	@RequestMapping(value = "/appRelationship", method = RequestMethod.POST)
	@ResponseBody
	public int add(@RequestBody SubSysAppInfo subSysAppInfo) {
		SubSysAppInfo info = SubSysAppInfoService.query(subSysAppInfo.getApplicationId().toString());
		if (info == null) {
			subSysAppInfo.setCreateTime(new Date());
			SubSysAppInfoService.add(subSysAppInfo);
			return subSysAppInfo.getApplicationId();
		} else {
			throw new RuntimeException("应用模块名称冲突");
		}

	}*/

	/**
	 * 
	 * @param subSysAppInfo subSysAppInfo
	 * @param applicationId applicationId
	 */
	@RequestMapping(value = "/appRelationship/{applicationId}", method = RequestMethod.POST)
	@ResponseBody
	public void update(@RequestBody SubSysAppInfo subSysAppInfo,@PathVariable String applicationId) {
		if(null == subSysAppInfo.getId())
		{
			subSysAppInfo.setApplicationId(Integer.parseInt(applicationId));
			subSysAppInfoService.add(subSysAppInfo);
		}
		else
		{
			subSysAppInfoService.update(subSysAppInfo);
		}
	}
	
	/**
	 * 
	 * @param applicationId applicationId
	 */
	@RequestMapping(value = "/appRelationship/{applicationId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable String applicationId) {
		subSysAppInfoService.delete(applicationId);

	}

	/**
	 * 
	 * @param params params
	 * @return List<SubSysAppInfo>
	 */
	@RequestMapping(value = "/appRelationship", method = RequestMethod.POST)
	@ResponseBody
	public List<SubSysAppInfo> quaryAll(@RequestBody PageExt params) {
		List<SubSysAppInfo> applicationInfos;
		params.setLimitBegin((params.getPageIndex()-1)*params.getPageSize());
		applicationInfos = subSysAppInfoService.queryAllByPage(params);
		return applicationInfos;
	}

	/**
	 * 
	 * @param m map
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/appRelationship", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String,String> count(@RequestBody Map m) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("params", m);
		Map<String,String> map = new HashMap<String,String>();
		map.put("count", subSysAppInfoService.count(params)+"");
		return map;
	}
	
	/**
	 * 
	 * 针对每个controller做异常处理
	 * 
	 * @param e
	 *            e
	 * @return 返回一个错误的实体对象
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo exception(Exception e) {
		logger.error(e.toString());
		return new ErrorInfo("", e.getMessage());
	}
}
