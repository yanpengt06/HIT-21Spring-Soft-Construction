import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adt.Employee;

public class EmployeeTest {

	@Test
/**	
 * 	test strategy:简单地测试getter
*/		
	public void testEmployee()
	{
		Employee e = new Employee("Alice","院长","12345678912");
		assertEquals("Alice",e.getName());
		assertEquals("院长",e.getPosition());
		assertEquals("12345678912",e.getTelnum());
	}
	@Test
	/**
	 * 	test strategy:简单实例测试
	 */	
	public void testtoString()
	{
		Employee e = new Employee("Alice","院长","12345678912");
		assertEquals("Employee [name=Alice, position=院长, telnum=12345678912]",e.toString());
	}
	
	@Test
	/**
	 * 	test strategy:三元组相等才认为是相同职工
	 */			
	
	public void testequals()
	{
		Employee e = new Employee("Alice","院长","12345678912");
		Employee e2 = new Employee("Alice","院长","12345678912");		
		assertEquals(true,e.equals(e2));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee e = new Employee("Alice","院长","12345678912");
		System.out.println(e);
	}

}
