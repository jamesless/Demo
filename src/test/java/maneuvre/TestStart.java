package maneuvre;

import java.lang.reflect.Proxy;



/** 
 * @author  sfhq1588 
 * @date 创建时间：2016年7月7日 下午2:43:25 
 * @description
 */
public class TestStart {
	
	public static void main(String[] args) {
		Object target = new TestImpl();
		ProxyUtil proxyUtil = new ProxyUtil(target);
		TestInterface proxyInterface = (TestInterface)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), TestImpl.class.getInterfaces(), proxyUtil);
		proxyInterface.add("add le");
		proxyInterface.getTest(123);
	}
	

}
