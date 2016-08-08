/**
 * =================================================================
 * 版权所有 2011-2020 恒通支付服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * =================================================================
 */
package com.sfpay.airscape.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ch.qos.logback.classic.Logger;

import com.sfpay.airscape.server.service.ApplicationService;
import com.sfpay.airscape.server.vo.Application;
import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.auxiliary.ErrorInfo;

/**
 * 类说明：<br>
 * 应用模块信息处理 查询 返回JSON对象
 * <p>
 * 详细描述：<br>
 * 业务模块 增删查找操作
 * 
 * @author sfhq593
 * @author sfhq593 CreateDate: 2015年12月28日14:04:24
 */
@Controller
public class ApplicationControl {
	@Resource
	private ApplicationService applicationService;
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(ApplicationControl.class);

	/**
	 * 
	 * @param params params
	 * @return List<Application>
	 */
	@RequestMapping(value = "/applist", method = RequestMethod.POST)
	@ResponseBody
	public List<Application> queryAll(@RequestBody Page params) {
		params.setLimitBegin((params.getPageIndex()-1)*params.getPageSize());
		return applicationService.queryAllByPage(params);

	}
	
	/**
	 * 
	 * @param m map
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/applist", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String,String> count(@RequestBody Map m) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("params", m);
		Map<String,String> map = new HashMap<String,String>();
		map.put("count", applicationService.count(params)+"");
		return map;
	}
	
	/**
	 * 
	 * @param e
	 *            接收一个错误处理對象
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
