import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adt.CommonIntervalSet;
import adt.CommonMultiIntervalSet;
import api.APIs;
import itf.IntervalSet;
import itf.MultiIntervalSet;

public class APIsTest {

	/**
	 * test strategy:均为简单实例测试
	 */	
	@Test
	public void testSimilarity()
	{
		MultiIntervalSet<String> s1 = new CommonMultiIntervalSet<>();
		MultiIntervalSet<String> s2 = new CommonMultiIntervalSet<>();
		s1.insert(0, 5, "a");
		s1.insert(10, 20, "b");
		s1.insert(20, 25, "a");
		s1.insert(25, 30, "b");
		s2.insert(0, 5, "c");
		s2.insert(10, 20, "b");
		s2.insert(20, 35, "a");
		assertEquals(true,Math.abs(APIs.Similarity(s1, s2)-0.42857142857142855) < 1e-6);
	}
	@Test
	public void testCalcConfilctRatio()
	{
		IntervalSet<String> is = new CommonIntervalSet<>();
		is.insert(0, 10, "a");
		is.insert(20,30, "b");
		is.insert(15, 25, "c");
		is.insert(25, 35, "d");
		assertEquals(true,Math.abs(APIs.calcConflictRatio(is)-0.2857142857142857) < 1e-6);
	}
	
	@Test
	public void testCalcFreeTimeRatio()
	{
		IntervalSet<String> is = new CommonIntervalSet<>();
		is.insert(0, 10, "a");
		is.insert(20,30, "b");
		is.insert(15, 25, "c");
		is.insert(25, 35, "d");
		assertEquals(true,Math.abs(APIs.calcFreeTimeRatio(is)-0.14285714285714285) < 1e-6);
	}
	
	@Test
	public void testCalcFreeTimeRatio2()
	{
		MultiIntervalSet<String> mis = new CommonMultiIntervalSet<>();
		mis.insert(0, 10, "a");
		mis.insert(20,30, "b");
		mis.insert(15, 25, "c");
		mis.insert(25, 35, "a");
		assertEquals(true,Math.abs(APIs.calcFreeTimeRatio(mis)-0.14285714285714285) < 1e-6);
	}

	@Test
	public void testCalcConflictTimeRatio2()
	{
		MultiIntervalSet<String> mis = new CommonMultiIntervalSet<>();
		mis.insert(0, 10, "a");
		mis.insert(20,30, "b");
		mis.insert(15, 25, "c");
		mis.insert(25, 35, "a");
		assertEquals(true,Math.abs(APIs.calcConflictRatio(mis)-0.2857142857142857) < 1e-6);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println((double)1 / 7);
	}

}
