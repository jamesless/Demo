package com.sfpay.airscape.server.vo.auxiliary;

import java.io.Serializable;

/**
 * @author sfhq593 2015年12月24日10:46:32 controller 层返回信息的实体类
 */
public class ErrorInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String err;

	/**
	 * 
	 */
	public ErrorInfo() {
		super();
	}

	/**
	 * 
	 * @param url url
	 * @param err err
	 */
	public ErrorInfo(String url, String err) {
		super();
		this.url = url;
		this.err = err;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

}
