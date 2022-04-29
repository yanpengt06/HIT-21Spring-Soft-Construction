package adt;
public class Course {

	//immutable
	
	private final int id;
	private final String name;
	private final String teacherName;
	private final String loc;
	private	final int weekTime;
	
//	AF:由课程id映射至一门唯一的课程，该课程具有名字，教师姓名以及上课地点等信息。
//	RI：name != null && id >=0 && teachername != null && loc != null
//	safety from RE: private访问权限控制，final引用不可变，一个基本类型，三个字符串类型，均为不可变类型，不存在表示泄露问题
	
	public Course(int id, String name, String teacherName, String loc,int weekTime) {
		super();
		this.id = id;
		this.name = name;
		this.teacherName = teacherName;
		this.loc = loc;
		this.weekTime = weekTime;
		checkRep();
	}



	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public String getTeacherName() {
		return teacherName;
	}



	public String getLoc() {
		return loc;
	}

	public int getWeekTime()
	{
		return weekTime;
	}
	

	@Override
	public String toString() {
		return "[cid=" + id + ", name=" + name + ", teacherName=" + teacherName + ", loc=" + loc + ", weekTime=" + weekTime  +"]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private void checkRep()
	{
		assert name != null;
		assert id >=0;
		assert loc != null;
		assert teacherName != null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
