package adt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import itf.IntervalSet;

public class CommonIntervalSet<L> implements IntervalSet<L> {

	private final Map<L,Interval> intervals = new HashMap<>();

//	AF:
//	属性也是一个时间段的集合，与场景中的时间段集合构成一一映射关系。
//	RI：
//	由于是intervalset，根据其限定条件，不允许集合中出现两个具有相同标签的时间段，这在insert中保证了；
//	每个时间段应满足合法性(start >=0 && end >=0 && start<=end )，这在Interval构造器中保证了。
//	label非空	
//	safety from RE:
//	private访问权限控制，final引用不可变，防御式拷贝。  
	public CommonIntervalSet() {
		super();
	}

	public void insert(long start,long end, L label)
	{
		intervals.put(label, new Interval(start,end));
		checkRep();
	}

	public Set<L> labels()
	{
		return intervals.keySet();
	}
	
	public boolean remove(L label)
	{
		if(!intervals.keySet().contains(label))
			return false;
		else
		{
			intervals.remove(label);
			return true;
		}
	}

	public long start(L label)
	{
		if(!intervals.keySet().contains(label))
			return -1;
		return intervals.get(label).getStart();
	}
	
	public long end(L label)
	{
		if(!intervals.keySet().contains(label))
			return -1;
		return intervals.get(label).getEnd();
		
	}


	@Override
	public String toString() {
		String result = "{";
		for(L label : intervals.keySet())
		{
			Interval i = intervals.get(label);
			result  = result + label + "=";
			result += i;
			result += ",";
		}
		if(result.length()>1)
			result  = result.substring(0, result.length()-1);
		result+="}";
		return result;
	}
	
	private void  checkRep()
	{
		for(L label : intervals.keySet())
			assert label != null;
	}
	
	public long getStart()
	{
		long minstart = Integer.MAX_VALUE,maxend  = Integer.MIN_VALUE;
		for(L label : labels())
		{
			if(start(label) < minstart)
				minstart = start(label);
			if(end(label) > maxend)
				maxend = end(label);
		}
		return minstart;
	}

	public long getEnd()
	{
		long maxend  = Integer.MIN_VALUE;
		for(L label : labels())
		{
			if(end(label) > maxend)
				maxend = end(label);
		}
		return maxend;
		
	}
	public Set<L> getLabels(long i)
	{
		Set<L> labels = new HashSet<>();
		for(L label : labels())
		{
			if(start(label) <= i && end(label) >= i+1)
				labels.add(label);
		}
		return labels;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntervalSet<String> is = new CommonIntervalSet<>();
		System.out.println(is);
	}

}
