package com.sfpay.airscape.server.controller;

import java.util.Date;
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

import com.sfpay.airscape.server.service.SubSysAppInfoService;
import com.sfpay.airscape.server.service.SubSystemService;
import com.sfpay.airscape.server.vo.SubSystem;
import com.sfpay.airscape.server.vo.auxiliary.ErrorInfo;

/**
 * 
 * @author sfhq1576
 *
 */
@Controller
public class SubSystemControl {

	@Resource
	private SubSystemService subSystemService;
	@Resource
	SubSysAppInfoService subSysAppInfoService;
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(SubSystemControl.class);

	/**
	 * 
	 * @param subSystem subSystem
	 */
	@RequestMapping(value = "/subSystem", method = RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody SubSystem subSystem) {
		logger.info("SubSystem添加入库");	
		subSystemService.add(subSystem);
	}

	/**
	 * 
	 * @param subSystemId subSystemId
	 */
	@RequestMapping(value = "/subSystem/{subSystemId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("subSystemId") String subSystemId) {
		logger.info("删除SubSystem"+subSystemId+"记录");
		subSystemService.delete(subSystemId);

	}

	/**
	 * 
	 * @param subSystemId subSystemId
	 * @return SubSystem
	 */
	@RequestMapping(value = "/subSystem/{subSystemId}", method = RequestMethod.GET)
	@ResponseBody
	public SubSystem query(@PathVariable String subSystemId) {
		return subSystemService.query(subSystemId);
	}

	/**
	 * 
	 * @return List<SubSystem>
	 */
	@RequestMapping(value = "/subSystem", method = RequestMethod.GET)
	@ResponseBody
	public List<SubSystem> queryAll() {
		return subSystemService.queryAll();

	}

	/**
	 * 
	 * @param subSystem subSystem
	 * @param subSystemId subSystemId
	 */
	@RequestMapping(value = "/subSystem/{subSystemId}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody SubSystem subSystem, @PathVariable String subSystemId) {
		logger.info("SubSystem更新"+subSystemId+"入库");
		subSystem.setUpdateTime(new Date());
//		subSystem.setOldSubSystemName(subSystemName);
		subSystemService.update(subSystem);
//		subSysAppInfoService.updateForSubSystemName(subSystem);

	}
	

	/**
	 * 
	 * @param e
	 *            e
	 * @return 异常对 象
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo exception(Throwable e) {
		logger.error(e.toString());
		return new ErrorInfo("", e.getMessage());
	}
}
