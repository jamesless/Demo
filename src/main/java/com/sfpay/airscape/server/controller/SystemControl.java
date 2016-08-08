package com.sfpay.airscape.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




import ch.qos.logback.classic.Logger;




import com.sfpay.airscape.server.service.SysProdRelService;
import com.sfpay.airscape.server.service.SystemService;
import com.sfpay.airscape.server.vo.System;
import com.sfpay.airscape.server.vo.auxiliary.SysProdRel;

/**
 * 
 * @author sfhq1576
 *
 */
@Controller
public class SystemControl {
	
	@Resource
	private SystemService systemService;
	

	private static Logger logger = (Logger) LoggerFactory.getLogger(SystemControl.class);

	@Resource
	private SysProdRelService sysProdRelService;

	
	/**
	 * 
	 * @return List<System>
	 */
	@RequestMapping(value = "/system", method = RequestMethod.GET)
	@ResponseBody
	public List<System> queryAll() {
		logger.info("SystemControl查询所有");
		return systemService.queryAll();
	}
	

	/**
	 * 
	 * @param sys system
	 */
	@RequestMapping(value = "/system", method = RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody System sys) {
		logger.info("SystemControl添加入库");
		systemService.add(sys);
		SysProdRel spr = new SysProdRel(sys.getBusinessId(),sys.getSystemId());
		sysProdRelService.add(spr);
	}


	/**
	 * 
	 * @param sys system
	 */
	@RequestMapping(value = "/system/{systemid}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody System sys) {
		logger.info("SystemControl更新"+sys.getSystemId()+"入库");
		systemService.update(sys);
		SysProdRel spr = new SysProdRel(sys.getBusinessId(),sys.getSystemId());
		sysProdRelService.deleteBySystemid(sys.getSystemId());
		sysProdRelService.add(spr);
	}


	/**
	 * 
	 * @param systemid systemid
	 */
	@RequestMapping(value = "/system/{systemid}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("systemid") String systemid) {
		logger.info("删除SystemControl"+systemid+"记录");
		sysProdRelService.deleteBySystemid(systemid);
		systemService.delete(systemid);
	}
	
	/**
	 * 
	 * @param systemid systemid
	 * @return System
	 */
	@RequestMapping(value = "/system/{systemid}", method = RequestMethod.GET)
	@ResponseBody
	public System query(@PathVariable("systemid") String systemid) {
		logger.info("SystemControl查询:"+systemid);
		return systemService.query(systemid);
	}
}
