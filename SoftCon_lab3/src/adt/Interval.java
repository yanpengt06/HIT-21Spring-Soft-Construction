package adt;
public class Interval {

	private long start;
	private long end;
	
//	AF:时间段开始时刻：start，终止时刻：end。由这二元组映射到一个唯一的时间段。
//	RI：start>=0,end>=0
//	safety from RE:private访问权限控制，label引用不可变，label自身是不可变类型。
	
	public Interval(long start, long end) {
		super();
		this.start = start;
		this.end = end;
		checkRep();
	}

	

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
	
	


	public long getStart() {
		return start;
	}



	public long getEnd() {
		return end;
	}




	public void setStart(long start) {
		this.start = start;
		checkRep();
	}



	public void setEnd(long end) {
		this.end = end;
		checkRep();
	}

	private void checkRep()
	{
		assert start>=0;
		assert end>=0;
		assert start<=end;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interval i  = new Interval(10,20);
		System.out.println(i);
	}

}
