import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import adt.DutyRoster;
import adt.Employee;

public class DutyRosterTest {

	@Test
	/**
	 * test strategy: 日期合法不合法进行分类测试
	 */
	public void testsetDutyPeriod()
	{
		DutyRoster dr = new DutyRoster();
		assertEquals(true,dr.setDutyPeriod("2021-03-08", "2021-03-21"));
		assertEquals(false,dr.setDutyPeriod("2021-03-08", "2021-02-28"));
	}
	
	@Test	
/**
 * 		test strategy:简单实例测试，添加员工后删除，根据删除的员工是否在员工列表中分类测试
 * 
*/		
	public void testaddEmployeesAndRemove()
	{
		DutyRoster dr = new DutyRoster();
		List<Employee> elist = new ArrayList<>();
		elist.add(new Employee("a","院长","123456"));
		elist.add(new Employee("b","副院长","123456"));
		dr.addEmployees(elist);
		dr.setDutyPeriod("2021-03-06", "2021-03-12");
		assertEquals(true, dr.removeEmployee(new Employee("a","院长","123456")));
		assertEquals(false, dr.removeEmployee(new Employee("d","院长","123456")));		

	}

	@Test	
/**
 * 		test strategy:手动排班分别测试排满或者没排满
 * 
*/		
	public void testisFullandsetDuty()
	{
		DutyRoster dr = new DutyRoster();
		List<Employee> elist = new ArrayList<>();
		Employee a =  new Employee("a","院长","123456");
		Employee b =  new Employee("b","副院长","123456");
		elist.add(a);
		elist.add(b);
		dr.addEmployees(elist);
		dr.setDutyPeriod("2021-03-06", "2021-03-12");
		dr.setDuty(a, "2021-03-06", "2021-03-07");
		dr.setDuty(b, "2021-03-08", "2021-03-11");
		dr.showDuty();
		assertEquals(false,dr.isFull());
		dr.removeEmployee(b);
		dr.addEmployees(elist);
		dr.setDuty(b, "2021-03-08", "2021-03-12");
		assertEquals(true,dr.isFull());
	}
	@Test	
/**
 * 		test strategy:简单实例测试
 * 
*/		
	public void testfreeRatio()
	{
		DutyRoster dr = new DutyRoster();
		List<Employee> elist = new ArrayList<>();
		Employee a =  new Employee("a","院长","123456");
		Employee b =  new Employee("b","副院长","123456");
		elist.add(a);
		elist.add(b);
		dr.addEmployees(elist);
		dr.setDutyPeriod("2021-03-06", "2021-03-12");
		dr.setDuty(a, "2021-03-06", "2021-03-07");
		dr.setDuty(b, "2021-03-08", "2021-03-11");
		assertEquals(true,Math.abs(dr.freeRatio()-0.14285714285714285) < 1e-6);
	}
	@Test	
/**
 * 		test strategy:
 * 
*/		
	public void testautoDistributeDuty()
	{
		DutyRoster dr = new DutyRoster();
		List<Employee> elist = new ArrayList<>();
		Employee a =  new Employee("a","院长","123456");
		Employee b =  new Employee("b","副院长","123456");
		elist.add(a);
		elist.add(b);
		dr.addEmployees(elist);
		dr.setDutyPeriod("2021-03-06", "2021-03-12");
		dr.autoDistributeDuty();
		assertEquals(true,dr.isFull());
	}

}
