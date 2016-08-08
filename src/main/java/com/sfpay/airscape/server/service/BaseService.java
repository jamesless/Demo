package com.sfpay.airscape.server.service;

import java.util.List;

/**
 * 
 * @author sfhq1588
 * @param t 实体对象
 */
public interface BaseService<T> {
	/**
	 * service公共新增方法
	 * @param t 实体对象
	 */
	public void add(T t);
	
	/**
	 * service公共更新方法
	 * @param t 实体对象
	 */
	public void update(T t);
	
	/**
	 * service公共删除方法
	 * @param id id
	 */
	public void delete(String id);
	
	/**
	 * 根据ID查询数据
	 * @param id id
	 * @return T 实体数据
	 */
	public T query(String id);
	
	/**
	 * 根据实体查询数据
	 * @param t 实体对象
	 * @return T 实体数据
	 */
	public List<T> query(T t);
	
	/**
	 * 查询所有数据
	 * @return List<T> 实体集合
	 */
	public List<T> queryAll();

}
