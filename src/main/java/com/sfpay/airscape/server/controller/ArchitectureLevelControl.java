package com.sfpay.airscape.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ch.qos.logback.classic.Logger;

import com.sfpay.airscape.server.service.ArchitectureLevelService;
import com.sfpay.airscape.server.vo.ArchitectureLevel;
import com.sfpay.airscape.server.vo.auxiliary.ErrorInfo;

/**
 * @author sfhq593 2016年1月13日18:08:51
 * 
 */
@Controller
public class ArchitectureLevelControl {
	@Resource
	ArchitectureLevelService architectureLevelService;
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(ArchitectureLevelControl.class);

	/**
	 * 
	 * @return 返回架构层级List集合
	 */
	@RequestMapping(value = "/levellist", method = RequestMethod.GET)
	@ResponseBody
	public List<ArchitectureLevel> queryAll() {

		return architectureLevelService.queryAll();

	}

	/**
	 * 针对每个controller做异常处理
	 * 
	 * @param e
	 *            e
	 * @return 异常信息对象
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo exception(Throwable e) {
		logger.error(e.toString());
		return new ErrorInfo("", e.getMessage());
	}
}
