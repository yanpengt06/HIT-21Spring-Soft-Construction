import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adt.Course;

public class CourseTest {

	@Test
	/**
	 * 	test strategy:简单实例测试getter
	 */	
	public void testCourse()
	{
		Course c = new Course(1,"math","xhc","zx",4);
		assertEquals(1,c.getId());
		assertEquals("zx",c.getLoc());
		assertEquals("math",c.getName());
		assertEquals("xhc",c.getTeacherName());
	}

	@Test
	/**
	 * 	test strategy:简单实例测试
	 */	
	public void testtoString()
	{
		Course c = new Course(1,"math","xhc","zx",4);
		assertEquals("[cid=1, name=math, teacherName=xhc, loc=zx, weekTime=4]",c.toString());
	}

	@Test
	/**
	 * 	test strategy:只要id相等就认为是同一个进程
	 */	
	public void testequals()
	{
		Course c = new Course(1,"math","xhc","zx",4);
		Course c2 = new Course(1,"english","xhc","zx",4);
		assertEquals(true,c.equals(c2));
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Course c = new Course(1,"math","xhc","zx",4);
		System.out.println(c);
	}

}
