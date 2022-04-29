package adt;
public class Employee {

	//immutable
	
	private final String name;
	private final String position;
	private final String telnum;
	
//	AF: (name,position,telnum)->employee，三元组实现对一个员工的映射
//	RI:三元组非空
//	safety from RE:private访问权限控制，final引用不可变，字符串本身是不可变对象，因而不存在表示泄露问题。
	
	public Employee(String name, String position, String telnum) {
		super();
		this.name = name;
		this.position = position;
		this.telnum = telnum;
		checkRep();
	}


	public String getName() {
		return name;
	}




	public String getPosition() {
		return position;
	}




	public String getTelnum() {
		return telnum;
	}

	private void checkRep()
	{
		assert name!=null;
		assert position!=null;
		assert telnum!=null;
	}


	@Override
	public String toString() {
		return "Employee [name=" + name + ", position=" + position + ", telnum=" + telnum + "]";
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (!name.equals(other.name))
			return false;
		if (!position.equals(other.position))
			return false;
		if (!telnum.equals(other.telnum))
			return false;
		return true;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
