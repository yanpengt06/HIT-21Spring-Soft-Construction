import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adt.CommonIntervalSet;
import itf.IntervalSet;

public class CommonIntervalSetTest extends IntervalSetInstanceTest{

	@Test
	/**
	 * test strategy:分空集与非空集进行测试。
	 */
	public void testtoString()
	{
		IntervalSet<String> is = new CommonIntervalSet<>();
		is.insert(0, 10, "a");
		is.insert(20, 30, "b");
		assertEquals("{a=[0, 10],b=[20, 30]}"
				,is.toString());
		IntervalSet<String> is2 = new CommonIntervalSet<>();
		assertEquals("{}"
						,is2.toString());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntervalSet<String> is = new CommonIntervalSet<>();
		is.insert(0, 10, "a");
		is.insert(20, 30, "b");
		System.out.println(is);
	}

}
