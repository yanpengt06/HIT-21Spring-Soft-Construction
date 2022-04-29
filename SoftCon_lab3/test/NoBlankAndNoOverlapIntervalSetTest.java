import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adt.NoBlankAndNoOverlapIntervalSetImpl;

public class NoBlankAndNoOverlapIntervalSetTest {

	@Test
	/**
	 * test strategy:重叠的时间段应不允许插入，且0，30之间无空白检查应为假，不对NoBlank NoOverlap 做单独测试。
	 */	 
	public void testNoblkandNoolp()
	{
		NoBlankAndNoOverlapIntervalSetImpl<String> is =  new NoBlankAndNoOverlapIntervalSetImpl<String>(0,30);
		is.insert(0, 10, "a");
		is.insert(10, 20, "b");
		is.insert(15, 25, "d");
		is.insert(20, 30, "c");
		assertEquals(3,is.labels().size());
		assertEquals(false,is.checkBlank());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
