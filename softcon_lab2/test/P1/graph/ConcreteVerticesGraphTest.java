/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testToString()
    {
    	Graph<String> g = emptyInstance();
    	g.add("a");
    	g.add("b");
    	g.add("c");
    	g.set("a", "b", 1);
    	g.set("b", "c", 2);
    	assertEquals("Vertex:a Source(Name-Weight): Target(Name-Weight):b-1,\n"+
    			"Vertex:b Source(Name-Weight):a-1, Target(Name-Weight):c-2,\n"+
    			"Vertex:c Source(Name-Weight):b-2, Target(Name-Weight):\n",g.toString());
    	Graph<String> g2 = emptyInstance();
    	assertEquals("",g2.toString());
    	Graph<String> g3 = emptyInstance();
    	g3.add("a");
    	assertEquals("Vertex:a Source(Name-Weight): Target(Name-Weight):\n",g3.toString());
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
/*    
    testStrategy:简单测试顶点名字即可。checkRep保证顶点名字非空
*/    
    @Test
    public void testGetName()
    {
    	Vertex<String> v = new Vertex<>("a");
    	assertEquals("a",v.getName());
    }
/*    
    testStrategy:给a加两个源点b、c，判断源点集合是否相等。
*/    
    @Test
    public void testGetSource()
    {
    	Vertex<String> v = new Vertex<>("a");
    	v.addSource("b", 1);
    	v.addSource("c", 2);
    	Map<String,Integer> map = new HashMap<>();
    	map.put("b", 1);
    	map.put("c", 2);
    	assertEquals(map,v.getSource());
    }
 /*    
    testStrategy:给a加两个终点b、c，判断终点集合是否相等。
*/    

    @Test
    public void testGetTarget()
    {
    	Vertex<String> v = new Vertex<>("a");
    	v.addTarget("b", 1);
    	v.addTarget("c", 2);
    	Map<String,Integer> map = new HashMap<>();
    	map.put("b", 1);
    	map.put("c", 2);
    	assertEquals(map,v.getTarget());
    }

	/*
	 * testStrategy:按照weight>0,=0,<0分类， 按照加入的点是否在原图中进行覆盖测试。
	 */	     
    @Test
    public void testaddSource()
    {
    	Vertex<String> v = new Vertex<>("v");
    	assertEquals(0,v.addSource("a", 1));
    	assertEquals(1,v.addSource("a", 0));
    	assertEquals(0,v.addSource("a", 2));
    	assertEquals(2,v.addSource("a", 3));
    	assertEquals(-1,v.addSource("b", -1));
    }
	/*
	 * testStrategy:与addSource类似，按照上述划分覆盖测试
	 */    
    @Test
    public void testaddTarget()
    {
    	Vertex<String> v  = new Vertex<String>("v");
    	assertEquals(0,v.addTarget("a", 1));
    	assertEquals(1,v.addTarget("a", 0));
    	assertEquals(0,v.addTarget("a", 2));
    	assertEquals(2,v.addTarget("a", 3));
    	assertEquals(-1,v.addTarget("b", -1));
    }

	/*
	 * testStrategy:按照去除的点是否时该顶点的源点分别进行测试。
	 */
    @Test
    public void testremoveSource()
    {
    	Vertex<String> v  = new Vertex<>("v");
    	v.addSource("a", 1);
    	assertEquals(1,v.removeSource("a"));
    	assertEquals(0,v.removeSource("a"));
    }

	/*
	 * testStrategy:同上分两类测试。
	 */
    @Test
    public void testremoveTarget()
    {
    	Vertex<String> v  = new Vertex<>("v");
    	v.addTarget("a", 1);
    	assertEquals(1,v.removeTarget("a"));
    	assertEquals(0,v.removeTarget("a"));    	
    }
}

