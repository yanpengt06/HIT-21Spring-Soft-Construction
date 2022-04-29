import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adt.Interval;

public class IntervalTest {

	
	@Test
	/**
	 * test strategy:对一个interval进行反复get与set测试
	 */	
	public void testgetterAndSetter()
	{
		Interval i  = new Interval(10,20);
		assertEquals(10,i.getStart());
		assertEquals(20,i.getEnd());
		i.setStart(5);
		assertEquals(5,i.getStart());
		i.setEnd(25);
		assertEquals(25,i.getEnd());
	}
	
	@Test
	/**
	 * test strategy:做一次简单测试，interval不考虑空情况，三元组缺失任意一个都不构成interval
	 */		
	public void testtoString()
	{
		Interval i  = new Interval(10,20);
		assertEquals("[10, 20]",i.toString());
	}
}
