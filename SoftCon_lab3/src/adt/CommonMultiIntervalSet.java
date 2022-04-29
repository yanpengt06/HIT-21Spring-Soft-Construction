package adt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import itf.IntervalSet;
import itf.MultiIntervalSet;

public class CommonMultiIntervalSet<L> implements MultiIntervalSet<L> {

	private final Map<L,IntervalSet<Integer>> map = new HashMap<>();
	
//	AF：map是一个标签到时间段集合的映射，时间段集合的标签是int，一个顺序标号
//	RI：	每个时间段应满足合法性(start >=0 && end >=0 && start<=end) ，这在Interval构造器中保证了。标签非空。
//	safety from RE:private 访问权限控制，final 引用不可变。
	public CommonMultiIntervalSet() {
		super();
	}

	public CommonMultiIntervalSet(IntervalSet<L> initial)
	{
		for(L label : initial.labels())
		{
			this.insert(initial.start(label), initial.end(label), label);
		}
	}
	
	public void insert(long start,long end, L label)
	{
		if(!map.keySet().contains(label))
		{
			IntervalSet<Integer> is = new CommonIntervalSet<>();
			is.insert(start, end, 0);
			map.put(label,is );
		}
		else
		{
			IntervalSet<Integer> is = map.get(label);
			is.insert(start, end, is.labels().size());
		}
		checkRep();
	}

	public Set<L> labels()
	{
		return map.keySet();
	}
	
	public boolean remove(L label)
	{
		if(!map.containsKey(label))
		{
			return false;
		}
		else
		{
			map.remove(label);
			return true;
		}
	}


	@Override
	public String toString() {
		String result = "[";
		for(L label : map.keySet())
		{
			result = result + label + "=";
			result += map.get(label);
			result += ",";
		}
		result  = result.substring(0, result.length()-1);
		result += "]";
		return result;
	}
	
	private void  checkRep()
	{
		for(L label : map.keySet())
		{
			assert label != null;
		}
	}

	public IntervalSet<Integer> intervals(L label)
	{
		IntervalSet<Integer> is = map.get(label);
		if(is == null)
			return null;
		int n = is.labels().size();
		for(int i = 0; i < n; i++ )
		{
			for(int j = i+1; j < n;j++)
			{
				if(is.start(j) < is.start(i))
				{
					long i_start = is.start(i);
					long j_start = is.start(j);
					long i_end = is.end(i);
					long j_end = is.end(j);
					is.remove(i);
					is.remove(j);
					is.insert(i_start, i_end, j);
					is.insert(j_start, j_end, i);
				}
			}
		}
		
		return is;
	}

	public long getStart()
	{
		long minstart = Integer.MAX_VALUE,start = 0;
		for(L label : map.keySet())
		{
			IntervalSet<Integer> is = intervals(label);
			start = is.start(0);
			if(start < minstart)
				minstart = start;
		}
		return minstart;
	}
	
	public long getEnd()
	{
		long maxend = Integer.MIN_VALUE,end = 0;
		for(L label : labels())
		{
			IntervalSet<Integer> is = intervals(label);
			end = is.end(is.labels().size()-1);
			if(end > maxend)
				maxend  = end;
		}
		return maxend;
		
	}
	
	public Set<L> getLabels(long i)
	{
		Set<L> labels = new HashSet<>();
		for(L label : labels())
		{
			IntervalSet<Integer> is = intervals(label);
			for(int j = 0; j < is.labels().size();j++)
			{
				if(is.start(j) <= i && is.end(j) >= i+1)
				{
					labels.add(label);
					break;
				}
		}
		}
		return labels;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
