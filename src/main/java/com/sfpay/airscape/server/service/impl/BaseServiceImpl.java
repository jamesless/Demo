package com.sfpay.airscape.server.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.sfpay.airscape.server.dao.AppDependencesDao;
import com.sfpay.airscape.server.dao.ApplicationDao;
import com.sfpay.airscape.server.dao.ArchitectureLevelDao;
import com.sfpay.airscape.server.dao.BusinessProductDao;
import com.sfpay.airscape.server.dao.CircleDependenceDao;
import com.sfpay.airscape.server.dao.DepenAnalysisDao;
import com.sfpay.airscape.server.dao.ErrorMessageDao;
import com.sfpay.airscape.server.dao.LoginInfoDao;
import com.sfpay.airscape.server.dao.PackageTypeDao;
import com.sfpay.airscape.server.dao.ParaDicDao;
import com.sfpay.airscape.server.dao.RoleDao;
import com.sfpay.airscape.server.dao.SubSysAppInfoDao;
import com.sfpay.airscape.server.dao.SubSystemDao;
import com.sfpay.airscape.server.dao.SvnRepositoryDao;
import com.sfpay.airscape.server.dao.SysProdRelDao;
import com.sfpay.airscape.server.dao.SystemDao;
import com.sfpay.airscape.server.dao.TaskWorkingRecDao;
import com.sfpay.airscape.server.dao.UserDao;
import com.sfpay.airscape.server.service.BaseService;
/**
 * 
 * @author sfhq1588
 * @param T vo类对象
 * 
 * 集中处理所有子类的dao注入
 * 约定：
 * 1.所有的dao命名都要与注入dao的类名一致
 * 2.所有的dao注入都要把get方法写在本类中
 * 3.所有继承的子类都必须把对应的pojo类（vo类）作为泛型传进来
 */
@SuppressWarnings("all")
public class BaseServiceImpl<T> implements BaseService<T>{
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(BaseServiceImpl.class);
	
	/**dao实体*/
	Object daoInstance;
	/**service传入的vo实体类 */
	Class<?> serviceClass;
	
	@Resource
	protected ApplicationDao applicationDao;
	@Resource
	protected AppDependencesDao appDependencesDao;
	@Resource
	protected ArchitectureLevelDao architectureLevelDao;
	@Resource
	protected BusinessProductDao businessProductDao;
	@Resource
	protected CircleDependenceDao circleDependenceDao;
	@Resource
	protected DepenAnalysisDao depenAnalysisDao;
	@Resource
	protected ErrorMessageDao errorMessageDao;
	@Resource
	protected PackageTypeDao packageTypeDao;
	@Resource
	protected ParaDicDao paraDicDao;
	@Resource
	protected SubSysAppInfoDao subSysAppInfoDao;
	@Resource
	protected SubSystemDao subSystemDao;
	@Resource
	protected SvnRepositoryDao svnRepositoryDao;
	@Resource
	protected SysProdRelDao sysProdRelDao;
	@Resource
	protected SystemDao systemDao;
	@Resource
	protected TaskWorkingRecDao taskWorkingRecDao;
	@Resource
	protected LoginInfoDao loginInfoDao;
	@Resource
	protected UserDao userDao;
	@Resource
	protected RoleDao roleDao;
	
	/**
	 * 获取子类service的实体
	 */
	public BaseServiceImpl() {
		ParameterizedType baseParameterizedTypeClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		serviceClass = (Class<?>) baseParameterizedTypeClass.getActualTypeArguments()[0];
	}
	
	/**
	 * 获取dao实际类型
	 */
	private void init()	{
		String className = serviceClass.getSimpleName();
		String lowerHead = className.substring(0,1).toLowerCase();
		String bodyName = className.substring(1, className.length());
		Class<?> baseClass = this.getClass();
		try {
			Method daoMethod=baseClass.getMethod("get"+lowerHead.toUpperCase()+bodyName+"Dao",null);
			daoInstance=daoMethod.invoke(this,null);
		} catch (NoSuchMethodException e) {
			LOGGER.error(e.toString());
			e.printStackTrace();
		} catch (SecurityException e) {
			LOGGER.error(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			LOGGER.error(e.toString());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.toString());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			LOGGER.error(e.getTargetException().toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 该功能只为排除CI上的warning
	 * @param message
	 */
	private void exeLogger(String message){
		LOGGER.info("执行"+message);
	}
	
	@Override
	public void add(T t) {
		exeLogger(serviceClass.getSimpleName()+"的添加语句");
		exeMethod("add",t);
	}

	@Override
	public void update(T t) {
		exeLogger(serviceClass.getSimpleName()+"的更新语句");
		exeMethod("update",t);
	}

	@Override
	public void delete(String id) {
		exeLogger(serviceClass.getSimpleName()+"的删除语句");
		exeMethod("delete",id);
	}

	@Override
	public T query(String id) {
		exeLogger(serviceClass.getSimpleName()+"的查询语句");
		List<T> result = exeMethod("query",id);
		if(result.size() > 0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<T> query(T t) {
		exeLogger(serviceClass.getSimpleName()+"的查询实体语句");
		List<T> result = exeMethod("query",t);
		return result;
	}

	@Override
	public List<T> queryAll() {
		exeLogger(serviceClass.getSimpleName()+"的查询所有数据语句");
		List<T> result = exeMethod("queryAll",null);
		return result;
	}
	
	/**
	 * 反射调用dao相对应methodName的方法
	 * @param methodName	调用的方法名
	 * @param param			方法参数
	 * @return List
	 */
	private List<T> exeMethod(String methodName,Object param) {
		init();
		
		List<T> result = null;
		List<T> invoke = new ArrayList<T>();
		Method queryMethod = null;
		
		try {
			if(param instanceof String){
				queryMethod=daoInstance.getClass().getDeclaredMethod(methodName,new Class[]{String.class});
				invoke.add((T) queryMethod.invoke(daoInstance,param));
			}else if(param instanceof Object){
				queryMethod=daoInstance.getClass().getDeclaredMethod(methodName,new Class[]{Object.class});
				invoke = (List<T>)queryMethod.invoke(daoInstance,param);
			}else{
				queryMethod=daoInstance.getClass().getDeclaredMethod(methodName,null);
				invoke = (List<T>)queryMethod.invoke(daoInstance,null);
			}
		} catch (NoSuchMethodException e) {
			LOGGER.error(e.toString());
		} catch (SecurityException e) {
			LOGGER.error(e.toString());
		} catch (IllegalAccessException e) {
			LOGGER.error(e.toString());
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.toString());
		} catch (InvocationTargetException e) {
			LOGGER.error(e.getTargetException().toString());
		}
		result = invoke != null ? new ArrayList<>(invoke) : null;
		return result;
	}


	public ApplicationDao getApplicationDao() {
		return this.applicationDao;
	}

	public AppDependencesDao getAppDependencesDao() {
		return appDependencesDao;
	}

	public ArchitectureLevelDao getArchitectureLevelDao() {
		return architectureLevelDao;
	}

	public BusinessProductDao getBusinessProductDao() {
		return businessProductDao;
	}

	public CircleDependenceDao getCircleDependenceDao() {
		return circleDependenceDao;
	}

	public DepenAnalysisDao getDepenAnalysisDao() {
		return depenAnalysisDao;
	}

	public ErrorMessageDao getErrorMessageDao() {
		return errorMessageDao;
	}

	public PackageTypeDao getPackageTypeDao() {
		return packageTypeDao;
	}

	public ParaDicDao getParaDicDao() {
		return paraDicDao;
	}

	public SubSysAppInfoDao getSubSysAppInfoDao() {
		return subSysAppInfoDao;
	}

	public SubSystemDao getSubSystemDao() {
		return subSystemDao;
	}

	public SvnRepositoryDao getSvnRepositoryDao() {
		return svnRepositoryDao;
	}

	public SysProdRelDao getSysProdRelDao() {
		return sysProdRelDao;
	}

	public SystemDao getSystemDao() {
		return systemDao;
	}

	public TaskWorkingRecDao getTaskWorkingRecDao() {
		return taskWorkingRecDao;
	}

	public Object getDaoInstance() {
		return daoInstance;
	}

	public Class<?> getServiceClass() {
		return serviceClass;
	}

	public LoginInfoDao getLoginInfoDao() {
		return loginInfoDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

}
