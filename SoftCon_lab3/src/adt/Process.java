package adt;

public class Process {

	//immutable
	
	private final int id;
	private final String name;
	private final int min_time;
	private final int max_time;
	
//	AF:由pid映射到唯一一个进程，每个进程有相应的三项信息。
//	RI：四元组非空，id>=0 && min_time >=0 && max_time>=0 && min_time <=max_time
//	safe from RE: private访问权限控制，final int不可变，字符串为引用不可变，本身也是不可变类型，不存在表示泄露。
	
	public Process(int id, String name, int min_time, int max_time) {
		super();
		this.id = id;
		this.name = name;
		this.min_time = min_time;
		this.max_time = max_time;
		checkRep();
	}



	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public int getMin_time() {
		return min_time;
	}



	public int getMax_time() {
		return max_time;
	}

	


	@Override
	public String toString() {
		return "[pid=" + id + ", name=" + name + ", min_time=" + min_time + ", max_time=" + max_time + "]";
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Process other = (Process) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private void checkRep()
	{
		assert name != null;
		assert id >=0;
		assert min_time >= 0;
		assert max_time >=0;
		assert min_time <= max_time;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
