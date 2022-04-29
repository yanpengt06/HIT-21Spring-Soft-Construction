/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
	/*
	 * 	构造以下有向图：1.a--->b--->c, 2.a 3.null
	 */    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testToString()
    {
    	Graph<String> g = emptyInstance();
    	g.add("a");
    	g.add("b");
    	g.add("c");
    	g.set("a", "b", 2);
    	g.set("b", "c", 1);
    	assertEquals("Edge:a to b with weight of 2\n"
    			+ "Edge:b to c with weight of 1\n",g.toString());
    	Graph<String> g2 = emptyInstance();
    	assertEquals("",g2.toString());
    	Graph<String> g3 = emptyInstance();
    	g3.add("a");
    	assertEquals("",g3.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    
    // TODO tests for operations of Edge
	/*
	 * TestStrategy 有checkRep，测试一条边的源点即可。
	 */    
    @Test
    public void testGetSource()
    {
    	Edge<String> e1 = new Edge<String>("A","B",25);
    	assertEquals("A",e1.getSource());    	
    }
	/*
	 * TestStrategy 有checkRep，测试一条边的终点即可。
	 */    
    @Test
    public void testGetTarget()
    {
    	Edge<String> e  = new Edge<String>("A","B",25);
    	assertEquals("B",e.getTarget());
    }
	/*
	 * TestStrategy 有checkRep，测试一条边的权值即可。
	 */    
    @Test
    public void testGetWeight()
    {
    	Edge<String> e  = new Edge<String>("A","B",25);
    	assertEquals(25,e.getWeight());
    }
    

}
