package com.sfpay.airscape.server.exception;

/**
 * @author sfhq593 TODO 自定义查找不到pom文件时抛出的异常问题进行捕获
 * 
 */
public class FindPomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	public FindPomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param message
	 *            message
	 * @param cause
	 *            cause
	 * @param enableSuppression
	 *            enableSuppression
	 * @param writableStackTrace
	 *            writableStackTrace
	 */
	public FindPomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param message
	 *            message
	 * @param cause
	 *            cause
	 */
	public FindPomException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param message
	 *            message
	 */
	public FindPomException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param cause cause
	 */
	public FindPomException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
