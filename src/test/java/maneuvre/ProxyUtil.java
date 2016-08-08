package maneuvre;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** 
 * @author  sfhq1588 
 * @date 创建时间：2016年7月7日 下午2:37:50 
 * @description
 */
public class ProxyUtil implements InvocationHandler {
	private Object target;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before....");
		Object obj = method.invoke(target, args);
		System.out.println("after....");
		return obj;
	}

	public ProxyUtil(Object target) {
		this.target = target;
	}


	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	
}
