package com.sfpay.airscape.server.utils;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sfpay.framework2.core.util.DateUtil;

/**
 * 
 * @author sfhq593 2016年1月8日13:36:30 自定义返回JSON 数据格中日期格式化处理
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider)
			throws IOException{
		jsonGenerator.writeString(DateUtil.format(value, DateUtil.YYYY_MM_DD_HH_MM_SS));
	}
}