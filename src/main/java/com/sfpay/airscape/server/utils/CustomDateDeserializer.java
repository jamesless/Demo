package com.sfpay.airscape.server.utils;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sfpay.framework2.core.util.DateUtil;

/**
 *
 * 
 * @author sfhq593 2016年1月8日13:36:30 自定义返回JSON 数据格中日期格式化处理
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		return DateUtil.parseDate(jp.getText(), DateUtil.YYYY_MM_DD_HH_MM_SS);
	}
}
