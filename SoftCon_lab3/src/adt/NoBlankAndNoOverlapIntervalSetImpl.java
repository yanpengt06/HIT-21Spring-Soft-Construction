package adt;

import itf.NoBlankIntervalSet;

public class NoBlankAndNoOverlapIntervalSetImpl<L> extends NoOverlapIntervalSetImpl<L> implements NoBlankIntervalSet<L> {

	private final long start_time;
	private final long end_time;
	
//	AF：在NoOverlapIntervalSet的基础上，在start_time - end_time 这段时间内不允许有空白时间段。
//	RI：start_time <= end_time
//	safety from RE: private 访问权限控制，final 引用不可变，由于是基本数据类型，没有表示泄露问题。 
	
	public NoBlankAndNoOverlapIntervalSetImpl(long start_time, long end_time) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		checkRep();
	}

	@Override
	public boolean checkBlank()
	{
		long end = start_time;
		int flag ;
		while(true)
		{
			flag = 0;
			for(L label : super.labels())
			 {
				 if(super.start(label) == end)
				 {
					 end = super.end(label);
					 flag = 1;
					 break;
				 }
			 }
			if(flag == 0)
				break;
		}
		 if(end != end_time)
			 return true;
		 return false;
	}

	private void checkRep()
	{
		assert start_time <= end_time;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NoBlankAndNoOverlapIntervalSetImpl<String> is = new NoBlankAndNoOverlapIntervalSetImpl<String>(0,100);
		is.insert(1, 3, "zhangsan");
		is.insert(3, 12, "lisi");
		is.insert(12, 14, "wangwu");
		is.insert(14, 21, "a");
		is.insert(21, 23, "b");
		is.insert(23, 31, "c");
		is.insert(31, 38, "e");
		System.out.println(is.labels().size());
		System.out.println(is.checkBlank());
		
	}

}
