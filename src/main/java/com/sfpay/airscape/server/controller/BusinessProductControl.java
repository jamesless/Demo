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

import com.sfpay.airscape.server.service.BusinessProductService;
import com.sfpay.airscape.server.vo.BusinessProduct;
import com.sfpay.airscape.server.vo.auxiliary.ErrorInfo;

/**
 * @author sfhq593 time 2015年12月25日10:07:12
 * 
 */
@Controller
public class BusinessProductControl {
	@Resource
	BusinessProductService businessProductService;
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(BusinessProductControl.class);

	/***
	 * 
	 * @param businessId
	 *            businessId
	 * @return 业务产品信息
	 */
	@RequestMapping(value = "/biz/{businessId}", method = RequestMethod.GET)
	@ResponseBody
	public BusinessProduct query(@PathVariable("businessId") String businessId) {
		return businessProductService.query(businessId);
	}

	/***
	 * 
	 * @param businessProduct
	 *            businessProduct
	 */
	@RequestMapping(value = "/biz", method = RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody BusinessProduct businessProduct) {
		logger.info("BusinessProduct添加入库");
		businessProductService.add(businessProduct);
	}

	/**
	 * 
	 * @param businessProduct
	 *            businessProduct
	 */
	@RequestMapping(value = "/biz/{businessId}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody BusinessProduct businessProduct) {
		logger.info("BusinessProduct更新"+businessProduct.getBusinessId()+"入库");
		businessProductService.update(businessProduct);
	}

	/**
	 * 
	 * @param businessId
	 *            businessId
	 */
	@RequestMapping(value = "/biz/{businessId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("businessId") String businessId) {
		logger.info("删除BusinessProduct"+businessId+"记录");
		businessProductService.delete(businessId);
	}

	/**
	 * 
	 * @return 返回所有业务产品
	 */
	@RequestMapping(value = "/biz", method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessProduct> queryAll() {
		return businessProductService.queryAll();

	}

	/**
	 * 针对每个controller做异常处理
	 * 
	 * @param e
	 *            e
	 * @return 异常对象
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo exception(Throwable e) {
		logger.error(e.toString());
		return new ErrorInfo("", e.getMessage());
	}
}
