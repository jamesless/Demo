package com.sfpay.airscape.server.dao;

import java.util.List;
/**
 * 
 * @author sfhq1588
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * 
	 * @param t t
	 */
	public void add(T t);
	
	/**
	 * 
	 * @param t t
	 */
	public void update(T t);
	
	/**
	 * 
	 * @param id id
	 */
	public void delete(String id);
	
	/**
	 * 
	 * @param id id
	 * @return t
	 */
	public T query(String id);
	
	/**
	 * 
	 * @param t t
	 * @return List<T>
	 */
	public List<T> query(T t);
	
	/**
	 * 
	 * @return List<T>
	 */
	public List<T> queryAll();
}
