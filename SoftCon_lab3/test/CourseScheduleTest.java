import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import adt.Course;
import adt.CourseSchedule;

public class CourseScheduleTest {

	@Test
	/**
	 * 	test strategy:简单实例测试
	 */			
	public void testaddCourseAndSoOn()
	{
		CourseSchedule cs = new CourseSchedule("2021-02-27",18);
		List<Course> clist = new ArrayList<>();
		Course c = new Course(1,"rjgz","xhc","zx",4);
		clist.add(c);
		cs.addCourse(clist);
		cs.manualScheduleCourse(c, 1, 8);
		cs.showUnArrangedCourses();
		cs.manualScheduleCourse(c, 3, 8);
		cs.showUnArrangedCourses();
	}
	
	@Test
	/**
	 *	 test strategy:简单实例测试
	 */	
	public void testfreeTimeRatio()
	{
		CourseSchedule cs = new CourseSchedule("2021-02-27",18);
		List<Course> clist = new ArrayList<>();
		Course c = new Course(1,"rjgz","xhc","zx",4);
		clist.add(c);
		cs.addCourse(clist);
		cs.manualScheduleCourse(c, 1, 8);
		assertEquals(true,Math.abs(0.96-cs.freeTimeRatio()) < 1e-6);
		cs.manualScheduleCourse(c, 3, 8);
		assertEquals(true,Math.abs(0.92-cs.freeTimeRatio()) < 1e-6);
	}
	
	@Test
	/**
	 * 	test strategy:
	 */	
	public void testconflictTimeRatio()
	{
		CourseSchedule cs = new CourseSchedule("2021-02-27",18);
		List<Course> clist = new ArrayList<>();
		Course c = new Course(1,"rjgz","xhc","zx",4);
		clist.add(c);
		cs.addCourse(clist);
		cs.manualScheduleCourse(c, 1, 8);
		assertEquals(true,Math.abs(0-cs.conflictTimeRatio()) < 1e-6);
		cs.manualScheduleCourse(c, 3, 8);
		assertEquals(true,Math.abs(0-cs.conflictTimeRatio()) < 1e-6);		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
