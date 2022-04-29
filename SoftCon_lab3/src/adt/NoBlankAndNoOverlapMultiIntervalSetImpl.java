package adt;

import itf.IntervalSet;
import itf.NoBlankIntervalSet;

public class NoBlankAndNoOverlapMultiIntervalSetImpl<L> extends NoOverlapMultiIntervalSetImpl<L> implements NoBlankIntervalSet<L> {

	private long startTime;
	private long endTime;
	
	public NoBlankAndNoOverlapMultiIntervalSetImpl(long startTime,long endTime)
	{
		this.startTime  = startTime;
		this.endTime = endTime;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkBlank() {
		// TODO Auto-generated method stub
		long end = startTime;
		int flag ;
		while(true)
		{
			flag = 0;
			for(L label : super.labels())
			 {
				IntervalSet<Integer> is = super.intervals(label);
				for(int i = 0; i < is.labels().size();i++)
				{
					if(is.start(i) == end)
					{
						end = is.end(i);
						flag = 1;
					}
				}
			 }
			if(flag == 0)
				break;
		}
		 if(end != endTime)
			 return true;
		 return false;

		
	}

}
