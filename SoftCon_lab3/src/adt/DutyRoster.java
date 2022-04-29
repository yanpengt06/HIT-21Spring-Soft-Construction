package adt;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import api.APIs;

public class DutyRoster {
	
	private  NoBlankAndNoOverlapMultiIntervalSetImpl<Employee> is;
	private  LocalDate dateStart;
	private  LocalDate dateEnd;
	private final List<Employee> elist = new ArrayList<>();
	
//	AF:dataStart--开始安排工作日期，dateEnd--结束安排工作日期（需要安排），is是一个intervalset，实现一个从start到end的排班管理系统
//	RI：dataStart 不在 dataEnd之后，其他RI由应用的子类型保证
//	safe from RE：private访问权限控制，final引用不可变，返回时做防御式拷贝。
	public DutyRoster()
	{
		
	}

	/**
	 * 	设置排班日期：从start日期排班至end日期，end也需排班
	 * 	@param start 排班开始日期，格式要求：yyyy-mm-dd；end 排班结束日期
	 * 	
	 */	
	public boolean setDutyPeriod(String start,String end)
	{
		LocalDate dateStart = LocalDate.parse(start);
		LocalDate dateEnd = LocalDate.parse(end);
		if(dateEnd.toEpochDay() - dateStart.toEpochDay() < 0)
			return false;
		this.dateEnd  = dateEnd;
		this.dateStart = dateStart;
		long days = getDay(dateEnd);
		is = new NoBlankAndNoOverlapMultiIntervalSetImpl<Employee>(1,days+1);
		checkRep();
		return true;
	}
	
	/**
	 * 	添加员工信息
	 * 	@param listToAdd 待添加员工信息列表
	 * 
	 */		
	public void addEmployees(List<Employee> listToAdd)
	{
		elist.addAll(listToAdd);
	}
	
	/**
	 * 	去除员工相关信息，包括已安排排班表
	 * 	@param 待去除员工信息
	 * 	@return boolean 是否成功去除，否为不存在该员工的信息
	 */	
	public boolean removeEmployee(Employee e)
	{
		if(elist.remove(e) == false)
			return false;
		is.remove(e);
		elist.remove(e);
		return true;
	}

	/**
	 * 	手动设置排班
	 * 	@param e 员工信息， startDate 排班开始日期， endDate 排班结束日期，需要安排
	 * 	
	 */
	public void setDuty(Employee e,String startDate,String endDate)
	{
		long start,end;
		try
		{
			 start = getDay(LocalDate.parse(startDate));
			 end = getDay(LocalDate.parse(endDate));
		}
		catch (DateTimeParseException e1)
		{
			throw new RuntimeException("日期类型错误，请检查后重新运行APP！");
		}
		is.insert(start, end+1, e);
	}

	/**
	 * 	判断是否已经排满
	 * 	@return boolean 是否排满
	 */
	public boolean isFull()
	{
		return !is.checkBlank();
	}
	
	/**
	 * 	展示排班信息
	 * 	
	 */
	public void showDuty()
	{
		
		for(Employee e : is.labels())
		{
//			System.out.print(e.getName() + ":" + this.dateStart.plusDays( is.start(e)-1) + "~" + this.dateStart.plusDays( is.end(e)-2) + "\n");
		}
	
	}
	
	/**
	 * 	计算空闲时间比例
	 * 	@return double [0,1] 比例值
	 */	
	public double freeRatio()
	{
		long days = getDay(dateEnd);
		is.insert(1, 1, new Employee(" "," "," "));
		is.insert(days+1, days+1, new Employee(" "," "," "));
		return APIs.calcFreeTimeRatio(is);
	}
	
	/*
	 * 	获得一个日期是排班的第几天，辅助用
	 */	
	private long getDay(LocalDate date)
	{
		return date.toEpochDay() - this.dateStart.toEpochDay() + 1;
	}
		
	/**
	 *	 根据现有职工以及排班区间自动排班，采用平均排班。
	 *	
	 */	
	public void autoDistributeDuty()
	{
		int personNum = elist.size();
		long end = getDay(dateEnd);
		long avgDay = end / personNum;
		long extraDay = end % personNum;
		long start = 1;
		for(int i = 0; i < elist.size()-1; i++)
		{
			Employee e = elist.get(i);
			is.insert(start, start + avgDay, e);
			start += avgDay;
		}
		is.insert(start, start+avgDay+extraDay, elist.get(elist.size()-1));
		assert !is.checkBlank();
	}
	
	private void checkRep()
	{
		Period p = Period.between(dateStart, dateEnd);
		assert p.getDays() >= 0;
	}
	
	public List<Employee> getEmployees()
	{
		return new ArrayList<Employee>(elist);		//防御式拷贝
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		DutyRoster dr = new DutyRoster();
//		List<Employee> elist = new ArrayList<>();
//		elist.add(new Employee("a","院长","123456"));
//		elist.add(new Employee("b","副院长","123456"));
//		dr.addEmployees(elist);
//		dr.setDutyPeriod("2021-03-06", "2021-03-12");
//		dr.autoDistributeDuty();
//		dr.showDuty();
		LocalDate start = LocalDate.parse("2021-01-23");
		LocalDate end = LocalDate.parse("2021-01-23");
		System.out.println(end.toEpochDay()-start.toEpochDay());
	}

}
