package adt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import itf.MultiIntervalSet;

public class ProcessSchedule {

	private final MultiIntervalSet<Process> mis = new NoOverlapMultiIntervalSetImpl<>();
	private final Map<Process, Boolean> doneMap = new HashMap<>();
	private final Map<Process, Integer> doneTimeMap = new HashMap<>();
	
//	AF:一组进程放置在两个map中，doneMap存放进程的是否完成信息，doneTimeMap存放进程已经执行时间信息，是NoOverlap的multiintervalset，映射到现实空间的一个进程安排
//	RI：process四元组非空，这在调用类中得到check，两个map的第二项不允许为空，其他在调用类中得到check。
//	safety from RE:private 访问权限控制，final引用不可变，不对外返回引用，无getter
	
	/**
	 * 	加入一组进程，初始化相关信息
	 * @param processes 待加入进程列表
	 */	
	public void addProcess(List<Process> processes)
	{
		for(Process p : processes)
		{
			doneMap.put(p, false);
			doneTimeMap.put(p, 0);
		}
		checkRep();
	}
	
	/**
	 * 	模拟随机调度进程，调度策略为：随意选取未完成的进程，在剩余最大时间中选取一个时间开始模拟cpu上执行。
	 */	
	public void simRanCall()
	{
		int label = 0,flag = 0;
		while(true)
		{
			flag  = 0;
			for(Process p: doneMap.keySet())
			{
				if(doneMap.get(p) == false)
				{
					flag = 1;
					Random rand = new Random();
					int nowexeTime = rand.nextInt(p.getMax_time()-doneTimeMap.get(p));
					mis.insert(label, label + nowexeTime, p);
					int exedTime = doneTimeMap.get(p);
					doneTimeMap.put(p, exedTime + nowexeTime);
					if(doneTimeMap.get(p) >= p.getMin_time())
					{
						doneMap.put(p, true);
					}
					label += nowexeTime;
					break;
				}
			}
			if(flag == 0)
				break;
		}
		checkRep();
		
	}
	
	/**
	 * 	模拟随机调度进程，调度策略为：优先选取距离最大执行时间最近的进程，
	 * 	在剩余最大时间中随机选取一个时间开始模拟cpu上执行。
	 */	

	public void simMinPriCall()
	{
		int label = 0,minremainTime = Integer.MAX_VALUE;
		Process priop = null;
		while(true)
		{
			minremainTime = Integer.MAX_VALUE;
			priop = null;
			for(Process p: doneMap.keySet())
			{
				if(doneMap.get(p) == false)
				{
					int remainTime = p.getMax_time() - doneTimeMap.get(p);
					if(remainTime < minremainTime)
					{
						minremainTime = remainTime;
						priop = p;
					}
				}
			}
			if(priop != null)
			{
				Random rand = new Random();
				int nowexeTime = rand.nextInt(priop.getMax_time()-doneTimeMap.get(priop));
				mis.insert(label, label + nowexeTime, priop);
				int exedTime = doneTimeMap.get(priop);
				doneTimeMap.put(priop, exedTime + nowexeTime);
				if(doneTimeMap.get(priop) >= priop.getMin_time())
				{
					doneMap.put(priop, true);
				}
				label += nowexeTime;
			}
			else
			{
				break;
			}

		}
		checkRep();

	}
	
	
	public void showCallResult()
	{
		System.out.println(mis);
	}
	
	private void checkRep()
	{
		for(Process p : doneMap.keySet())
		{
			assert doneMap.get(p) != null;
		}
		for(Process p : doneTimeMap.keySet())
		{
			assert doneTimeMap.get(p) != null;
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProcessSchedule ps = new ProcessSchedule();
		List<Process> processes = new ArrayList<Process>();
		processes.add(new Process(1,"p1",30,60));
		processes.add(new Process(2,"p2",10,20));
		ps.addProcess(processes);
		ps.simMinPriCall();
		ps.showCallResult();
	}

}
