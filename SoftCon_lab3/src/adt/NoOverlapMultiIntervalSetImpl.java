package adt;

import itf.IntervalSet;
import itf.NoOverlapIntervalSet;

public class NoOverlapMultiIntervalSetImpl<L> extends CommonMultiIntervalSet<L> implements NoOverlapIntervalSet<L>{

//	AF：在multiIntervalSet的基础上，不允许时间段重叠，这通过insert保证了，从而实现一个到NoOverlapmultiintervalset的映射。
//	RI：start <= end  && label !=null ，这在父类中都得到了check
//	safety from RE:不存在新增表示泄露问题。
	
	@Override
	public void insert(long start,long end, L label) 
	{
		for(L eachlabel : super.labels())
		{
			IntervalSet<Integer> is = super.intervals(eachlabel);
			for(int i = 0 ; i < is.labels().size();i++)
			{
				if(start < is.end(i) && end > is.start(i))
				{
					return;
				}
			}
		}
		super.insert(start, end, label);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
