package api;

import java.util.HashSet;
import java.util.Set;

import adt.CommonIntervalSet;
import itf.IntervalSet;
import itf.MultiIntervalSet;

public class APIs {

	public static <L> double Similarity(MultiIntervalSet<L> s1, MultiIntervalSet<L> s2)
	{
		long start1 = s1.getStart(),end1 = s1.getEnd();
		long start2  = s2.getStart(),end2 = s2.getEnd();
		long similarity = 0;
		long length1 = end1 - start1,length2 = end2 - start2;
		long length = length1 > length2 ? length1 : length2;
		for(int i = (int) start1; i < end1;i++)
		{
			Set<L> labels1 = s1.getLabels(i);
			Set<L> labels2 = s2.getLabels(i);
			if(labels1.isEmpty())
				;
			else 
			{
				if(labels1.equals(labels2))
					similarity ++;
			}
		}
		return (double)similarity / length;
		
	}
	
	public static <L> double calcConflictRatio(IntervalSet<L> set)
	{
		long start = set.getStart();
		long end = set.getEnd();
		long conflict = 0;
		long length = end - start;
		Set<L> labels = new HashSet<>();
		for(int i = (int) start; i < end; i++)
		{
			labels = set.getLabels(i);
			if(labels.size() >=2)
				conflict++;
		}
		return (double)conflict / length;
	}
	
	public static <L> double calcConflictRatio(MultiIntervalSet<L> set)
	{
		long start = set.getStart();
		long end = set.getEnd();
		long conflict = 0;
		long length = end - start;
		Set<L> labels = new HashSet<>();
		for(int i = (int) start; i < end; i++)
		{
			labels = set.getLabels(i);
			if(labels.size() >=2)
				conflict++;
		}
		return (double)conflict / length;
		
	}
	
	public static <L> double calcFreeTimeRatio(IntervalSet<L> set)
	{
		long start = set.getStart();
		long end = set.getEnd();
		long free = 0;
		long length = end - start;
		Set<L> labels = new HashSet<>();
		for(int i = (int) start; i < end; i++)
		{
			labels = set.getLabels(i);
			if(labels.size() ==0)
				free++;
		}
		return (double)free / length;

	}
	
	public static <L> double calcFreeTimeRatio(MultiIntervalSet<L> set)
	{
		long start = set.getStart();
		long end = set.getEnd();
		long free = 0;
		long length = end - start;
		Set<L> labels = new HashSet<>();
		for(int i = (int) start; i < end; i++)
		{
			labels = set.getLabels(i);
			if(labels.size() ==0)
				free++;
		}
		return (double)free / length;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntervalSet<String> is = new CommonIntervalSet<>();
		is.insert(0, 10, "a");
		is.insert(20,30, "b");
		is.insert(15, 25, "c");
		is.insert(25, 35, "d");
		System.out.println(APIs.calcConflictRatio(is));
	}

}
