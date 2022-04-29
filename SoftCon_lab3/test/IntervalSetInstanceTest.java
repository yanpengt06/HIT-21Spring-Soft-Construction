import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import itf.IntervalSet;

public class IntervalSetInstanceTest {

	@Test
	/**
	 * 	test strategy:
	 * 	首次插入时间段，再次插入相同标签的时间段，应更新。
	 */		
	public void testinsert()
	{
		IntervalSet<String> is1 = IntervalSet.empty();
		is1.insert(0, 10, "a");
		assertEquals(0,is1.start("a"));
		is1.insert(10, 20, "a");
		assertEquals(10,is1.start("a"));
	}
	
	@Test
	/**
	 * test strategy:
	 * 简单地插入时间段，获取相应标签集
	 */	
	public void testlabels()
	{
		IntervalSet<String> is1 = IntervalSet.empty();
		is1.insert(0, 10, "a");
		is1.insert(20, 30, "b");
		Set<String> labels = new HashSet<>();
		labels.add("a");
		labels.add("b");
		assertEquals(labels, is1.labels());
	}
	
	@Test 
	/**
	 * 	test strategy:针对标签是否存在于时间段集合中，进行分类测试。
	 */
	public void testremove()
	{
		IntervalSet<String> is1 = IntervalSet.empty();
		is1.insert(0, 10, "a");
		assertEquals(true, is1.remove("a"));
		assertEquals(false, is1.remove("b"));
	}
	@Test
	/**
	 *	 test strategy:针对目标标签是否存在进行分类测试
	 */	
	public void teststart()
	{
		IntervalSet<String> is1 = IntervalSet.empty();
		is1.insert(0, 10, "a");
		assertEquals(0,is1.start("a"));
		assertEquals(-1,is1.start("b"));
	}

	@Test
	/**
	 *	 test strategy:针对目标标签是否存在进行分类测试
	 */	
	public void testend()
	{
		IntervalSet<String> is1 = IntervalSet.empty();
		is1.insert(0, 10, "a");
		assertEquals(10,is1.end("a"));
		assertEquals(-1,is1.end("b"));
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
