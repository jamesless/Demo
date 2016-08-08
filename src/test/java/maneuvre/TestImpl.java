package maneuvre;
/** 
 * @author  sfhq1588 
 * @date 创建时间：2016年7月7日 下午2:19:13 
 * @description
 */
public class TestImpl implements TestInterface{

	@Override
	public void add(String str) {
		System.out.println("add start "+str);
	}

	@Override
	public void getTest(int number) {
		System.out.println("get start "+number);
	}

}
