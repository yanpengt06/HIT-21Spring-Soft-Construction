import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import itf.IntervalSet;
import itf.MultiIntervalSet;

public class MultiIntervalSetInstanceTest {

	@Test
	/**
	 * test strategy:插入重复标签应能正常插入
	 */
	public void testinsert()
	{
		MultiIntervalSet<String> mis = MultiIntervalSet.empty();
		mis.insert(0, 10, "a");
		mis.insert(30, 40, "a");
		IntervalSet<Integer> is = mis.intervals("a");
		assertEquals(0,is.start(0));
		assertEquals(30,is.start(1));
	}
	@Test
	/**
	 * test strategy:简单地实例测试
	 */
	public void testlabels()
	{
		MultiIntervalSet<String> mis = MultiIntervalSet.empty();
		Set<String> labels = new HashSet<>();
		labels.add("a");
		labels.add("b");
		mis.insert(0, 10, "a");
		mis.insert(20, 30, "b");
		mis.insert(30, 40, "a");
		assertEquals(labels,mis.labels());
	}
	
	@Test	
	/**
	 * 	test strategy:待去除的标签是否在时间段集合中分类测试
	 */	
	public void testremove()
	{
		MultiIntervalSet<String> mis = MultiIntervalSet.empty();
		mis.insert(0, 10, "a");
		mis.insert(20, 30, "b");
		mis.insert(30, 40, "a");
		assertEquals(true,mis.remove("a"));
		assertEquals(false,mis.remove("c"));
	}
	
	@Test
	/**
	 * 	test strategy:intervals的返回值是一个不带重复标签的IntervalSet,故调用start方法进行实例测试。
	 */			
	public void testintervals()
	{
		MultiIntervalSet<String> mis = MultiIntervalSet.empty();
		mis.insert(0, 10, "a");
		mis.insert(20, 30, "b");
		mis.insert(30, 40, "a");
		assertEquals(0,mis.intervals("a").start(0));
		assertEquals(30,mis.intervals("a").start(1));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
