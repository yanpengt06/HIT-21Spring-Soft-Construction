package adt;

import itf.NoOverlapIntervalSet;

public class NoOverlapIntervalSetImpl<L> extends CommonIntervalSet<L> implements NoOverlapIntervalSet<L>{

//	AF:在intervalset基础上，不允许时间重叠，通过insert的override实现
//	RI：start<=end,label != null,这在父类insert方法中保证了
//	safety from RE:无新增表示泄露问题
	
	@Override 
	public void insert(long start,long end, L label)
	{
		for(L eachlabel : super.labels())
		{
			if(start < super.end(eachlabel) && end > super.start(eachlabel))
			{
				return;
			}
		}
		super.insert(start, end, label);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}

}
