package itf;

public interface NoOverlapIntervalSet<L> {
	
//	特定的insert方法，重叠的时间段禁止插入
	public void insert(long start,long end, L label) ;
		
}
