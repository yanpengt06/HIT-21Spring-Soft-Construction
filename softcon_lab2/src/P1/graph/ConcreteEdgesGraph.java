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
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    //    由顶点集以及边集即可唯一确定一个有向图。是一个到有向图的映射（AF）。
    //    边集映射到了图上的每一条边，顶点集映射到了图上的每一个顶点。
    // Representation invariant:
    //   TODO
    //    边集中的源点与终点必须包含在顶点集中，权值大于0；两个顶点间最多一条边。
    // Safety from rep exposure:
    //   TODO
    //    vertices与edges均为引用不可变且是private的，但是都是可变类型，为了防止表示泄露，做defensive copy.
    // TODO constructor	
    public ConcreteEdgesGraph()
    {
    	
    }
    // TODO checkRep
    private void checkRep()
    {
    	for(Edge<L> e:edges)
    	{
    		assert e.getWeight() >= 0;
    		assert vertices.contains(e.getSource());
    		assert vertices.contains(e.getTarget());
    	}
    }
    @Override public boolean add(L vertex) {
    	checkRep();
    	return vertices.add(vertex);
    }
    
    @Override public int set(L source, L target, int weight) {
    	vertices.add(source);
    	vertices.add(target);
    	Edge<L> e_existed = null;
    	int oldWeight = 0;
    	if(weight < 0)
    		return -1;
    	if(weight == 0)
    	{
    		for(Edge<L> e : edges)
    		{
    			if(e.getSource().equals(source) && e.getTarget().equals(target))
    			{
    				e_existed  = e;
    				break;
    			}
    		}
    		if(e_existed != null)
    			{
    				oldWeight = e_existed.getWeight();
    				edges.remove(e_existed);
    			}
    		else 
    			return 0;
    	}
    	if(weight > 0)
    	{
    		for(Edge<L> e : edges)
    		{
    			if(e.getSource().equals(source) && e.getTarget().equals(target))
    			{
    				e_existed  = e;
    				break;
    			}
    		}
    		if(e_existed != null)
    		{
    			oldWeight = e_existed.getWeight();
    			edges.remove(e_existed);
        		edges.add(new Edge<L>(source,target,weight));
    		}
    		else
    		{
    			edges.add(new Edge<L>(source,target,weight));
    		}
    	}
    	checkRep();
    	return oldWeight;
    }
    
    @Override public boolean remove(L vertex) {
    	if(!vertices.contains(vertex))
    		return false;
    	vertices.remove(vertex);
    	for(Edge<L> e : edges)
    	{
    		if(e.getSource().equals(vertex) || e.getTarget().equals(vertex))
    			edges.remove(e);
    	}
    	checkRep();
    	return true;
    }
    
    @Override public Set<L> vertices() {
    	return new HashSet<L>(vertices);		//防御式拷贝
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> result = new HashMap<>();
    	for(Edge<L> e: edges)
    	{
    		if(e.getTarget().equals(target))
    		{
    			result.put(e.getSource(), e.getWeight());
    		}
    	}
    	checkRep();
    	return result;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> result = new HashMap<>();
    	for(Edge<L> e : edges)
    	{
    		if(e.getSource().equals(source))
    			result.put(e.getTarget(), e.getWeight());
    	}
    	checkRep();
    	return result;
    }
    
    // TODO toString()
    @Override
    public String toString()
    {
    	String graph = "";
    	for(Edge<L> e : edges)
    	{
    		graph += e;
    		graph += "\n";
    	}
    	return graph;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    private final L source;
    private final L target;
    private final int weight;
    // Abstraction function:
    //   TODO
//    source映射到边的源点，target映射到边的终点，weight映射到边权。
    // Representation invariant:
    //   TODO
//    weight > 0 && source和target出现在vertices集合中 && source !=null && target != null
    // Safety from rep exposure:
    //   TODO
//    首先三者都是private的，这是较为安全的；source与target、weight均为不可变类型，edge类就是immutable，不存在RE问题。
    // TODO constructor
    
	public Edge(L source, L target, int weight) {
		super();
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}
    // TODO methods
    
	public L getSource() {
		return source;
	}

	public L getTarget() {
		return target;
	}

	public int getWeight() {
		return weight;
	}
    
    // TODO checkRep
    private void checkRep()
    {
    	assert weight > 0;
    	assert source !=null;
    	assert target !=null;
    }
    
    // TODO toString()
    @Override 
    public String toString()
    {
    	return "Edge:" + this.source + " to " + this.target + " with weight of " + this.weight;
    }
}
