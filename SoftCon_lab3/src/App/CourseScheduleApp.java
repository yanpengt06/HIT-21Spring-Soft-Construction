package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adt.Course;
import adt.CourseSchedule;
import adt.Employee;

public class CourseScheduleApp {

	public static void Menu() throws IOException
	{
    	BufferedReader toread=new BufferedReader(new FileReader(new File("src/App/txt/coursemenu.txt")));
    	String line;
    	while((line=toread.readLine())!=null) {  	//将文件一行字符存入line中
    		System.out.println(line);
    	}
    	toread.close();

	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Menu();
		String opt;
		CourseSchedule cs = null;
		while(true)
		{
			Scanner in = new Scanner(System.in);
			opt = in.nextLine();
			String oneline;
			if(opt.equals("1"))
			{
				System.out.println("请输入学期开始时间与总周数:(eg:2021-03-02 18)");
				oneline  = in.nextLine();
				String[] set = oneline.split(" ");
				cs = new CourseSchedule(set[0],Integer.parseInt(set[1]));
				System.out.println("初始化完毕");
			}
			else if(opt.equals("2"))
			{
				System.out.println("正在添加课程信息...");
		    	BufferedReader toread=new BufferedReader(new FileReader(new File("src/App/txt/courses.txt")));
		    	String line;
		    	List<Course> clist = new ArrayList<Course>();
		    	while((line=toread.readLine())!=null) {  	//将文件一行字符存入list中
		    		String[] course = line.split("-");
		    		clist.add(new Course(Integer.parseInt(course[0]), course[1],course[2],course[3], Integer.parseInt(course[4])));
		    	}
		    	toread.close();
				cs.addCourse(clist);
				System.out.println("添加完毕");
			}
			else if(opt.equals("3"))
			{
				System.out.println("请输入手工排课信息：");
				oneline = in.nextLine();
				String[] schedule = oneline.split("-");
				Course c = null;
				for(Course eachCourse : cs.getCourses())
				{
					if(eachCourse.getId() == Integer.parseInt(schedule[0]))
						c = eachCourse;
				}
				if(c== null)
				{
					System.out.println("课程未录入， 请重新录入!!");
					break;
				}
				cs.manualScheduleCourse(c, Integer.parseInt(schedule[1]), Integer.parseInt(schedule[2]));
				System.out.println("排课成功！");
			}
			else if(opt.equals("4"))
			{
				System.out.println("checking...");
				cs.showUnArrangedCourses();
				System.out.println("周课程空闲率为：" + cs.freeTimeRatio());
				System.out.println("周课程重复率为："  + cs.conflictTimeRatio());
			}
			else if(opt.equals("5"))
			{
				System.out.println("请输入一个日期:");
				String date = in.nextLine();
				cs.checkCourseTabel(date);
			}
			else if(opt.equals("q"))
				break;

    }
	}

}
