/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }

	/*
	 * testStrategy:仅仅创建Integer作为标识符的图，并通过简单的sources方法测试。
	 */
    // TODO test other vertex label types in Problem 3.2
    @Test
    public void testInteger()
    {
    	Graph<Integer> g = Graph.empty();
    	g.add(5);
    	g.add(4);
    	g.set(4, 5, 10);
    	Map<Integer,Integer> sources  = new HashMap<>();
    	sources.put(4,10);
    	assertEquals(sources,g.sources(5));
    }
}
