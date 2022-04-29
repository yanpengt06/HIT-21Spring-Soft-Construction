package itf;

import java.util.Set;

import adt.CommonMultiIntervalSet;

public interface MultiIntervalSet<L> {

	/**
	 *	 一个时间段集合，每一个时间段对应一个标签，各个时间段对应的标签可重复。
	 *	时间统一换算为long类型。不允许时间段被重叠，即一个时间段对应多个标签。
	 *	一个标签可对应多个时间段。
	 *	@param <L> 标签类型，一定是不可变类型。
	 */	
	
	
	/**
	 * 	创建一个空的时间段集合
	 * 
	 *  @return IntervalSet<L> 一个空的时间段集合
	 */
	public static <L> MultiIntervalSet<L> empty()
	 {
		return new CommonMultiIntervalSet<L>();
		
	 }
	
		/**
		 * 		插入一个时间段以及标签
		 * 		@param start 时间段开始时刻
		 * 		@param end 	时间段终止时刻
		 * 		@param label 时间段对应标签
		 * 		
		 */	
	public void insert(long start,long end, L label);
	
	/**
	 * 		获取该集合中所有标签构成的集合
	 * 		@return Set<L> 所有标签构成的集合。
	 * 
	 */	
	public Set<L> labels();
	
	/**
	 * 		去除某一个标签对应的时间段
	 * 		@param label 待去除的标签
	 * 		@return boolean 是否成功去除
	 */	
	public boolean remove(L label);
	

	/**
	 * 	获取与某个标签关联的所有时间段
	 *	@param label 标签
	 *	@return IntervalSet<Integer> 与标签关联的所有时间段
	 */	
	public IntervalSet<Integer> intervals(L label);
	
	
	/**
	 * 	获取multiintervalset起始时刻
	 */
	public long getStart();
	
	/**
	 * 	获取multiintervalset终止时刻
	 */	
	public long getEnd();
	
/**
 * 		用于判断i，i+1时段有几个interval对应
 *		@param i i,i+1时段
 *		@return Set<L> 对应的标签集合
*/	
	public Set<L> getLabels(long i);
	
}
	
