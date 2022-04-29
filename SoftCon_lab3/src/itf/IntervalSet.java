package itf;

import java.util.Set;
import adt.CommonIntervalSet;

public interface IntervalSet<L> {
	
	/**
	 *	 一个时间段集合，每一个时间段对应一个标签，假定各个时间段对应的标签不重复。
	 *	时间统一换算为long类型。不允许时间段被重叠，即一个时间段对应多个标签。
	 *	一个标签只对应一个时间段。
	 *	@param <L> 标签类型，一定是不可变类型。
	 */	
	
	/**
	 * 	创建一个空的时间段集合
	 * 
	 *  @return IntervalSet<L> 一个空的时间段集合
	 */
	public static <L> IntervalSet<L> empty()
	 {
		return new CommonIntervalSet<L>();	 
	 }
	
		/**
		 * 		插入一个时间段以及标签,若标签重复则用新时间段直接覆盖原时间段
		 * 		@param start 时间段开始时刻
		 * 		@param end 	时间段终止时刻
		 * 		@param label 时间段对应标签
		 * 		@return boolean 是否成功插入
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
	 * 		获取一个标签对应时间段的开始时刻
	 * 		@param label 需要获取时刻对应的标签
	 * 		@return long 标签对应时间段的开始时刻
	 * 
	 */	
	public long start(L label);
	
	/**
	 * 		获取一个标签对应时间段的终止时刻
	 * 		@param label 需要获取时刻对应的标签
	 * 		@return long 标签对应时间段的终止时刻
	 * 
	 */	
	
	public long end(L label);

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
 *		@return Set<L> 对应的标签集合，可为空集
*/	
	public Set<L> getLabels(long i);

}
