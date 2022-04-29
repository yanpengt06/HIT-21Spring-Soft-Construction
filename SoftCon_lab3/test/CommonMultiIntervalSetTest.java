import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adt.CommonIntervalSet;
import adt.CommonMultiIntervalSet;
import itf.IntervalSet;
import itf.MultiIntervalSet;

public class CommonMultiIntervalSetTest extends MultiIntervalSetInstanceTest{

	@Test
	/**
	 * test strategy:简单实例测试
	 */
	public void testtoString()
	{
		MultiIntervalSet<String> mis = MultiIntervalSet.empty();
		mis.insert(0, 10, "a");
		mis.insert(20, 30, "b");
		mis.insert(30, 40, "a");
		assertEquals("[a={0=[0, 10],1=[30, 40]},b={0=[20, 30]}]"
					,mis.toString());
	}
	
	@Test
	/**
	 * 	test strategy:利用intervalset构造multiintervalset，简单测试是否成功
	 */	
	public void testConstructor()
	{
		IntervalSet<String> is = new CommonIntervalSet<>();
		is.insert(0, 10, "a");
		is.insert(20, 30, "b");
		MultiIntervalSet<String> mis = new CommonMultiIntervalSet<>(is);
		assertEquals(2,mis.labels().size());
	} 
	@Test
	/**
	 * test strategy:简单实例测试
	 */
	public void testgetStartandEnd()
	{
		MultiIntervalSet<String> mis = MultiIntervalSet.empty();
		mis.insert(0, 10, "a");
		mis.insert(20, 30, "b");
		mis.insert(30, 40, "a");
		mis.insert(25, 35, "d");
		assertEquals(0,mis.getStart());
		assertEquals(40,mis.getEnd());

	}
	@Test
	/**
	 * test strategy:简单实例测试
	 */
	public void testgetLabel()
	{
		MultiIntervalSet<String> mis = MultiIntervalSet.empty();
		mis.insert(0, 10, "a");
		mis.insert(20, 30, "b");
		mis.insert(30, 40, "a");
		mis.insert(25, 35, "d");
		assertEquals(2,mis.getLabels(25).size());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiIntervalSet<String> mis = MultiIntervalSet.empty();
		mis.insert(0, 10, "a");
		mis.insert(20, 30, "b");
		mis.insert(30, 40, "a");
		System.out.println(mis);
	}

}
