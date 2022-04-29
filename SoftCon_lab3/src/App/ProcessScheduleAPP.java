package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adt.Process;
import adt.ProcessSchedule;

public class ProcessScheduleAPP {

	public static void Menu() throws IOException
	{
	   	BufferedReader toread=new BufferedReader(new FileReader(new File("src/App/txt/procmenu.txt")));
    	String line;
    	while((line=toread.readLine())!=null) {  	//将文件一行字符存入line中
    		System.out.println(line);
    	}
    	toread.close();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Menu();
		ProcessSchedule ps = new ProcessSchedule();
		while(true)
		{
			Scanner in =  new Scanner(System.in);
			String opt = in.nextLine();
			if(opt.equals("1"))
			{
				System.out.println("正在从文件中读取进程组信息...");
		    	BufferedReader toread=new BufferedReader(new FileReader(new File("src/App/txt/process.txt")));
		    	String line;
		    	List<Process> processes  = new ArrayList<>();
		    	while((line=toread.readLine())!=null) {  	//将文件一行字符存入list中
		    		String[] process = line.split("-");
		    		processes.add(new Process(Integer.parseInt(process[0]),process[1],Integer.parseInt(process[2]),Integer.parseInt(process[3])));
		    	}
	    		ps.addProcess(processes);
		    	toread.close();
				System.out.println("读取完成");
			}
			else if(opt.equals("2"))
			{
				System.out.println("正在模拟随机调度");
				ps.simRanCall();
				System.out.println("模拟调度完成");
			}
			else if(opt.equals("3"))
			{
				System.out.println("正在模拟最短优先调度...");
				ps.simMinPriCall();
				System.out.println("模拟调度完成");
			}
			else if(opt.equals("4"))
			{
				System.out.println("模拟过程展示如下：");
				ps.showCallResult();
			}
			else if(opt.equals("q"))
			{
				System.out.println("App已退出");
				break;
			}
		}
	}

}
