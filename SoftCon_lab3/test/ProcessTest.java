import static org.junit.Assert.assertEquals;

import org.junit.Test;
import adt.Process;

public class ProcessTest {

	@Test
	/**
	 * 	test strategy:简单实例测试getter
	 */	
	public void testProcess()
	{
		Process p = new Process(1,"a",10,20);
		assertEquals(1,p.getId());
		assertEquals("a",p.getName());
		assertEquals(10,p.getMin_time());
		assertEquals(20,p.getMax_time());
	}

	@Test
	/**
	 * 	test strategy:简单实例测试
	 */	
	public void testtoString()
	{
		Process p = new Process(1,"a",10,20);
		assertEquals("Process [id=1, name=a, min_time=10, max_time=20]",p.toString());
	}

	@Test
	/**
	 * 	test strategy:只要id相等就认为是同一个进程
	 */	
	public void testequals()
	{
		Process p = new Process(1,"a",10,20);
		Process p2 = new Process(1,"b",30,40);
		assertEquals(true,p.equals(p2));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Process p = new Process(1,"a",10,20);
		System.out.println(p);
	}

}
