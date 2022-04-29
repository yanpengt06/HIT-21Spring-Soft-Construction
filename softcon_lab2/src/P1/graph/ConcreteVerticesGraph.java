/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
//    顶点的属性包括了边的描述，一个顶点集就对应了一个图，是一个AF。
    // Representation invariant:
    //   TODO
//    vertex中的权要大于0；源点与终点必须包含在顶点集中。vertices中不能有重复的顶点。
    // Safety from rep exposure:
    //   TODO
//    vertices是引用不可变的可变对象，private在一定程度上防止了RE，但是一定要进行防御式拷贝（作为返回值时）。
    // TODO constructor
    public ConcreteVerticesGraph()
    {
    	
    }
    
    // TODO checkRep
    private void checkRep()
    {
    	Set<Vertex<L>> testSet = new HashSet<Vertex<L>>();
    	testSet.addAll(vertices);
    	assert testSet.size() == vertices.size();
    }
    @Override public boolean add(L vertex) {
    	for(Vertex<L> v :vertices)
    	{
    		if(v.getName().equals(vertex))
    		{
    			return false;
    		}
    	}
    	vertices.add(new Vertex<L>(vertex));
    	checkRep();
    	return true;

    }
    
    @Override public int set(L source, L target, int weight) {
    	Vertex<L> v =null, v_source = null, v_target = null;
    	int preweight;
    	this.add(source);
    	this.add(target);
    	for(int i = 0;i < vertices.size();i++)
    	{
    		 v = vertices.get(i);
    		 if(v.getName().equals(source))
    			 v_source = v;
    		 if(v.getName().equals(target))
    			 v_target = v;	 
    	}
    	preweight = v_source.addTarget(target, weight);
    	v_target.addSource(source, weight);
    	checkRep();
    	return preweight;
    }
    
    @Override public boolean remove(L vertex) {
    	Vertex<L> toRemove = null;
    	for(Vertex<L> v : vertices)
    	{
    		if(v.getName().equals(vertex))
    		{
    			toRemove = v;
    			break;
    		}
    		else
    		{
    			if(v.getSource().containsKey(vertex))
    				v.removeSource(vertex);
    			if(v.getTarget().containsKey(vertex))
    				v.removeTarget(vertex);
    		}
    	}
    	checkRep();
    	return vertices.remove(toRemove);
    }
    
    @Override public Set<L> vertices() {
    	Set<L> result  = new HashSet<>();
    	for(Vertex<L> v : vertices)
    	{
    		result.add(v.getName());
    	}
    	checkRep();
    	return result;
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> result = new HashMap<L, Integer>();
    	Vertex<L> target_vtx = null;
    	for(Vertex<L> v : vertices)
    	{
    		if(v.getName().equals(target))
    			{
    				target_vtx = v;
    	    		break;
    			}
    	}
    	for( L name : target_vtx.getSource().keySet())
    	{
    		result.put(name, target_vtx.getSource().get(name));
    	}
    	checkRep();
    	return result;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Vertex<L> source_vtx = null;
    	for(Vertex<L> v : vertices)
    	{
    		if(v.getName().equals(source))
    			{
    				source_vtx = v;
    	    		break;
    			}
    	}
    	checkRep();
    	return new HashMap<L, Integer>(source_vtx.getTarget());
    }
    
    // TODO toString()
    @Override
    public String toString()
    {
    	String graph = "";
    	for(Vertex<L> v : vertices)
    	{
    		graph += v;
    		graph += "\n";
    	}
    return graph;
    }
}


class Vertex<L> {
    
    // TODO fields
    private final L name;
    private final Map<L,Integer> source;
    private final Map<L,Integer> target;
    // Abstraction function:
    //   TODO
//    name映射为点的名字（标识符），source映射为所有源点与入边的集合，target映射为所有终点与出边的集合，这样映射为唯一的一个顶点。
    // Representation invariant:
    //   TODO
//    name != null && 每条边的权值大于0 
    // Safety from rep exposure:
    //   TODO
//    private具有一定的安全性；三个属性均为引用不可变的，且String类型是不可变类型，故name不存在表示泄露问题。\
//    source与target采用防御式拷贝防止RE。
    // TODO constructor
    
    public Vertex(L name) {
		super();
		this.name = name;
		this.source = new HashMap<L,Integer>();
		this.target = new HashMap<L,Integer>();
		checkRep();
	}
    // TODO methods

	public L getName() {
		return name;
	}


	public Map<L, Integer> getSource() {
		return new HashMap<L,Integer>(source);
	}


	public Map<L, Integer> getTarget() {
		return new HashMap<L,Integer>(target);
	}

	/**
	 * 为该顶点添加一个源点
	 * 
	 * @param newVertex---新源点名字, weight ---- 权重>=0
	 * 
	 * @return preweight 若新添加的源点已是源点，返回旧权重；若新添加源点不是源点，返回0，即总是返回旧权重。若权小于0，返回-1
	 */	
	public int addSource(L newVertex,int weight)
	{
		Integer preweight = 0;
		if(weight < 0)
			return -1;
		if(weight == 0)
		{
			preweight = this.removeSource(newVertex);
		}
		if(weight > 0)
		{
			preweight = this.source.put(newVertex, weight);
			if(preweight == null)
			{
				preweight = 0;
			}
		}
		checkRep();
		return preweight;
	}
  
	/**
	 * 去除一个源点
	 * 
	 * @param vertex---待去除源点名
	 * 
	 * @return preweight 若待去除的源点是源点中的一个，返回旧权；若待去除的源点不是源点集中的一个，返回0，即总是返回旧权。
	 */	
	public int removeSource(L vertex)
	{
		Integer preweight;
		preweight = this.source.remove(vertex);
		if(preweight == null)
		{
			preweight = 0;
		}
		checkRep();
		return preweight;
	}

	/**
	 * 增加一个目标点
	 * 
	 * @param name---目标点名字，weight---权重
	 * 
	 * @return preweight 若增加目标点是点集中的一个，则返回旧权；若增加目标点不是点集之一，返回0，即总是返回旧权。
	 * 若weight<0,return -1;
	 */	
	public int addTarget(L name, int weight)
	{
		Integer preweight = 0;
		if(weight < 0)
		{
			return -1;
		}
		if(weight == 0)
		{
			preweight = this.removeTarget(name);
		}
		if(weight > 0)
		{
			preweight = this.target.put(name, weight);
			if(preweight ==null)
				preweight = 0;
		}
		checkRep();
		return preweight;
	}
	
	/**
	 * 去除一个目标点
	 * 
	 * @param name---目标点名字
	 * 
	 * @return preweight 若待去除目标点是目标点中的一个，则返回旧权；若不是，返回0，即总是返回旧权。
	 */	
	public int removeTarget(L name)
	{
		Integer preweight = 0;
		preweight = this.target.remove(name);
		if(preweight == null)
		{
			preweight = 0;
		}
		checkRep();
		return preweight;
	}
    // TODO checkRep
    private void checkRep()
    {
    	assert this.name != null;
    	for(L name : this.getSource().keySet())
    	{
    		assert this.getSource().get(name) > 0;
    	}
    	for(L name : this.getTarget().keySet())
    	{
    		assert this.getTarget().get(name) > 0;
    	}
    }
    
    // TODO toString()
    @Override
    public String toString()
    {
    	String result = "";
    	result = "Vertex:" + this.getName() + " Source(Name-Weight):";
    	for(L name:this.getSource().keySet())
    	{
    		result  = result + name + "-" + this.getSource().get(name) + ",";
    	}
    	result += " Target(Name-Weight):";
    	for(L name:this.getTarget().keySet())
    	{
    		result  = result + name + "-" + this.getTarget().get(name) + ",";
    	}
    	return result;
    }
}
