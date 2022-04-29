package adt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.APIs;
import itf.IntervalSet;
import itf.MultiIntervalSet;

public class CourseSchedule {

	private final LocalDate beginDate;
	private final int week;
	private final List<Course> clist = new ArrayList<>();
	private final MultiIntervalSet<Course> mis = new NoOverlapMultiIntervalSetImpl<>();
	private final Map<Course,Integer> unArrangedTimeMap = new HashMap<>();
	private final Map<Course,Boolean> ArrangedMap = new HashMap<>();
	
//	AF：beginDate为学期开始日期，week为学期周数，clist对应课程信息，mis完成一周的课程安排，两个map标记是否安排满学时
//	RI:week > 0 ,其他在调用类mis与course类中得到保证 && beginDate != null
//	safety from RE:private 访问权限控制，final引用不可变，不对外返回引用
	
	
	public CourseSchedule(String begin,int week)
	{
		this.week = week;
		this.beginDate = LocalDate.parse(begin);
		checkRep();
	}
	
	/**
	 * 增加课程信息
	 * @param courses 课程列表
	 */
	public void addCourse(List<Course> courses)
	{
		clist.addAll(courses);
		for(Course c : clist)
		{
			unArrangedTimeMap.put(c, c.getWeekTime());
			ArrangedMap.put(c, false);
		}
	}

	/**
	 * 	手工排课
	 * 	@param c 待安排课程，weekDay 安排在周几，time 安排在哪个时段，只允许输入8，10，13，15，19之一
	 * 	
	 */	
	public void manualScheduleCourse(Course c,int weekDay ,int time)
	{
		int courseOrder = getCourseOrder(weekDay,time);
		mis.insert((courseOrder-1)*2+1,(courseOrder-1)*2+3 , c);
		unArrangedTimeMap.put(c, unArrangedTimeMap.get(c) - 2);
		if(unArrangedTimeMap.get(c) == 0)
			ArrangedMap.put(c, true);
	}
	
	
	private int getCourseOrder(int weekDay,int time)
	{
		Map<Integer,Integer> courseOrder  = new HashMap<>();
		courseOrder.put(8, 1);
		courseOrder.put(10, 2);
		courseOrder.put(13, 3);
		courseOrder.put(15, 4);
		courseOrder.put(19, 5);		
		return (weekDay-1)*5 + courseOrder.get(time);
	}
	
	/**
	 *	 展示未安排满学时的课程
	 */
	public void showUnArrangedCourses()
	{
		System.out.println("尚未排满的课如下所示：");
		for(Course c : ArrangedMap.keySet())
		{
			if(ArrangedMap.get(c) == false)
				System.out.print(c+"\n");
		}
	}

	/**
	 * 计算一周的空闲时间比例
	 * @return double 比例值[0,1]
	 */	
	public double freeTimeRatio()
	{
		mis.insert(1, 1,  new Course(0," "," "," ",0));
		mis.insert(51, 51, new Course(0," "," "," ",0));
		return APIs.calcFreeTimeRatio(mis);
	}

	/**
	 * 计算一周排课重叠时间比例
	* @return double 比例值[0,1]
	 */	
	public double conflictTimeRatio()
	{
		mis.insert(1, 1,  new Course(0," "," "," ",0));
		mis.insert(51, 51, new Course(0," "," "," ",0));
		return APIs.calcConflictRatio(mis);
	}

	/**
	 * 	查询任意一个日期的课表
	 * @param date 待查询日期，输入格式：yyyy-mm-dd
	 */	
	public void checkCourseTabel(String date)
	{
		LocalDate ld = LocalDate.parse(date);
		long delta = ld.toEpochDay()-this.beginDate.toEpochDay();
		long weekday = delta % 7 + 1;
		long start = (weekday-1)*10+1;
		long end = 10*weekday +1;
		for(Course c : mis.labels())
		{
			IntervalSet<Integer> is = mis.intervals(c);
			for(int i = 0; i < is.labels().size();i++)
			{
				long cstart = is.start(i);
				long cend = is.end(i);
				if(cstart >= start && cend <= end)
				{
					System.out.println(c + ":" + start%10 + "-" + cend%10 +"\n");
				}
			}
		}

		
	}
	
	private void checkRep()
	{
		assert week >=0;
		assert beginDate != null;
	}
	
	public List<Course> getCourses()
	{
		return new ArrayList<Course>(clist);		//防御式拷贝
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CourseSchedule cs = new CourseSchedule("2021-02-27",18);
		List<Course> clist = new ArrayList<>();
		Course c = new Course(1,"rjgz","xhc","zx",4);
		clist.add(c);
		cs.addCourse(clist);
		cs.manualScheduleCourse(c, 1, 8);
		cs.showUnArrangedCourses();
		cs.manualScheduleCourse(c, 3, 8);
		cs.showUnArrangedCourses();
		System.out.println(cs.conflictTimeRatio());
		cs.checkCourseTabel("2021-02-27");
	}

}
