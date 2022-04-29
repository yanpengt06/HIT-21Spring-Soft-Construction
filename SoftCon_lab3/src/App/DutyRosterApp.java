package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adt.DutyRoster;
import adt.Employee;

public class DutyRosterApp {

	public static  void Menu() throws IOException
	{
    	BufferedReader toread=new BufferedReader(new FileReader(new File("src/App/txt/dutymenu.txt")));
    	String line;
    	while((line=toread.readLine())!=null) {  	//将文件一行字符存入line中
    		System.out.println(line);
    	}
    	toread.close();
	}
	
	public  static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Menu();
		DutyRoster dr = new DutyRoster();
		String opt;
		while(true)
		{
			Scanner in = new Scanner(System.in);
			opt = in.nextLine();
			if(opt.equals("1"))
			{
				System.out.println("请输入排班时间段（eg：2021-07-03~2021-07-04）：");
				String period = in.nextLine();
				String[] dates  = period.split("~");
				String startDate = dates[0];
				String endDate = dates[1];
				dr.setDutyPeriod(startDate, endDate);
			}
			else if(opt.equals("2"))
			{
				System.out.println("Reading employee inf from employee.txt");
		    	BufferedReader toread=new BufferedReader(new FileReader(new File("src/App/txt/employee.txt")));
		    	String line;
		    	List<Employee> elist = new ArrayList<>();
		    	while((line=toread.readLine())!=null) {  	//将文件一行字符存入list中
		    		String[] employee = line.split(" ");
		    		elist.add(new Employee(employee[0],employee[1],employee[0]));
		    	}
		    	dr.addEmployees(elist);
		    	toread.close();
		    	System.out.println("read successfully");
			}
			else if(opt.equals("3"))
			{
				System.out.println("Please input manually distribution inf:(eg:zhangsan 2021-07-03 2021-07-04)");
				String[] distrb = in.nextLine().split(" ");
				Employee e = null;
				for(Employee eache : dr.getEmployees())
				{
					if(eache.getName().equals(distrb[0]))
						e = eache;
				}
				if(e == null)
				{
					System.out.println("不存在这个职工，请重新添加职工信息！");
					continue;
				}
				dr.setDuty(e, distrb[1], distrb[2]);
			}
			else if(opt.equals("4"))
			{
				System.out.println("正在自动排班.......");
				dr.autoDistributeDuty();
				System.out.println("自动排班成功！");
			}
			else if(opt.equals("5"))
			{
				System.out.println("排班表如下：");
				dr.showDuty();
			}
			else if(opt.equals("6"))
			{
				System.out.println("正在检查是否排满：");
				System.out.println(dr.isFull());
			}
			else if(opt.equals("q"))
				break;
			else if(opt.equals("7"))
			{
		    	BufferedReader toread=new BufferedReader(new FileReader(new File("src/App/txt/test8.txt")));
		    	String line;
		    	List<Employee> elist = new ArrayList<>();
		    	while((line=toread.readLine())!=null) {  	//将文件一行字符存入list中
		    		if(line.matches("Employee(.*)"))
		    		{
		    			while(!(line = toread.readLine()).matches("}"))
		    			{
		    				String[] employee = line.split("[{},]");
		    				employee[0] = employee[0].replaceAll("[^A-Za-z]", "");
		    				elist.add(new Employee(employee[0],employee[1],employee[2]));
		    			}
		    			dr.addEmployees(elist);
		    		}
		    		if(line.matches("Period(.*)"))
		    		{
		    			String[] dates = line.split("[{},]");
		    			dr.setDutyPeriod(dates[1],dates[2]);
		    		}
		    		if(line.matches("Roster(.*)"))
		    		{
		    			while(!(line = toread.readLine()).matches("}"))
		    			{
		    				String[] duty = line.split("[{},]");
		    				duty[0]  = duty[0].replaceAll("[^A-Za-z]", "");
		    				String name = duty[0];
		    				Employee e1 = null;
		    				for( Employee e : dr.getEmployees())
		    				{
		    					if(e.getName().equals(name))
		    					{
		    						e1 = e;
		    						break;
		    					}
		    				}
		    				if(e1 == null)
		    				{
		    					System.out.println("此员工信息未录入，请重新添加");
		    					continue;
		    				}
		    				dr.setDuty(e1, duty[1], duty[2]);
		    			}
		    		}
		    	}
		    	toread.close();
		    	System.out.println("已完成人工排班");
			}
		}
    }
		
	}

