package com.sfpay.airscape.server.controller;

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

import com.sfpay.airscape.server.service.SvnRepositoryService;
import com.sfpay.airscape.server.vo.SvnRepository;
import com.sfpay.airscape.server.vo.auxiliary.ErrorInfo;



/**
 * @author sfhq593 2015年12月24日15:55:48
 */
@Controller
public class SvnRepositoryControl {

	@Resource
	private SvnRepositoryService svnRepositoryService;
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SvnRepositoryControl.class);

	/**
	 * 
	 * @param svnUrl
	 *            svnUrl
	 * @return svn库信息
	 */
	@RequestMapping(value = "/svnsource", method = RequestMethod.POST)
	@ResponseBody
	public SvnRepository add(@RequestBody SvnRepository svnUrl) {
		LOGGER.info("SvnRepository添加入库");
		svnRepositoryService.add(svnUrl);
		return svnUrl;
	}

	/**
	 * 
	 * @param svnUrl
	 *            svnUrl
	 * @param repositoryId
	 *            repositoryId
	 */
	@RequestMapping(value = "/svnsource/{repositoryId}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody SvnRepository svnUrl, @PathVariable("repositoryId") String repositoryId) {
//		svnUrl.setRepositoryId(Integer.parseInt(repositoryId));
		LOGGER.info("SvnRepository更新"+repositoryId+"入库");
		svnUrl.setId(Integer.parseInt(repositoryId));
		svnRepositoryService.update(svnUrl);
	}

	/**
	 * 
	 * @param repositoryId
	 *            repositoryId
	 * @return svn库信息
	 */
	@RequestMapping(value = "/svnsource/{repositoryId}", method = RequestMethod.GET)
	@ResponseBody
	public SvnRepository query(@PathVariable("repositoryId") String repositoryId) {
		return svnRepositoryService.query(repositoryId);

	}

	/**
	 * 
	 * @return 所有svn库信息
	 */
	@RequestMapping(value = "/svnsource", method = RequestMethod.GET)
	@ResponseBody
	public List<SvnRepository> queryAll() {
		return svnRepositoryService.queryAll();
	}

	/**
	 * 
	 * @param repositoryId
	 *            repositoryId
	 */
	@RequestMapping(value = "/svnsource/{repositoryId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("repositoryId") String repositoryId) {
		LOGGER.info("删除SvnRepository"+repositoryId+"记录");
		svnRepositoryService.delete(repositoryId);
	}

	/**
	 * 
	 * @param e
	 *            e
	 * @return 异常对象
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo exception(Throwable e) {
		LOGGER.error(e.toString());
		return new ErrorInfo("", e.getLocalizedMessage());
	}
}
