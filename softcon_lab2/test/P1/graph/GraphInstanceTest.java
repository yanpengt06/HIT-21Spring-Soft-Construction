/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
	/*
	 * testStrategy:加入的点已经在图中或者不在图中，分类测试。
	 */
    @Test
    public void testadd()
    {
    	Graph<String> g = emptyInstance();
    	assertEquals(true,g.add("a"));
    	assertEquals(true,g.add("b"));
    	assertEquals(false,g.add("b"));
    }

	/*
	 * testStrategy:依据weight>0,<0或者=0与set的边是否存在在原图中划分，覆盖测试。
	 */
    @Test
    public void testset()
    {
    	Graph<String> g = emptyInstance();
    	g.add("a");
    	g.add("b");
    	assertEquals(0,g.set("a", "b", 2));
    	assertEquals(2,g.set("a", "b", 3));
    	assertEquals(-1,g.set("a", "b", -1));
    	assertEquals(3,g.set("a", "b", 0));
    }

	/*
	 * testStrategy:remove的顶点是否存在在图中划分，测试。
	 */
    @Test
    public void testremove()
    {
    	Graph<String> g = emptyInstance();
    	g.add("a");
    	g.add("b");
    	assertEquals(true,g.remove("b"));
    	assertEquals(false,g.remove("b"));
    }

	/*
	 * testStrategy:按照是否为空图划分测试。
	 */
    @Test
    public void testvertices()
    {
    	Graph<String> g = emptyInstance();
    	g.add("a");
    	g.add("b");
    	Set<String> vertices = new HashSet<>();
    	vertices.add("a");
    	vertices.add("b");
    	assertEquals(vertices,g.vertices());
    	Graph<String> g2 = emptyInstance();
    	assertEquals(new HashSet<String>(),g2.vertices());
    }

	/*
	 * testStrategy:一个顶点是否具有源点划分测试。
	 */
    @Test
    public void testsources()
    {
    	Graph<String> g = emptyInstance();
    	g.add("a");
    	g.add("b");
    	g.add("c");
    	g.set("a", "c", 1);
    	g.set("b", "c", 2);
    	Map<String,Integer> sources = new HashMap<>();
    	sources.put("a", 1);
    	sources.put("b",2);
    	assertEquals(sources,g.sources("c"));
    	Graph<String> g2 = emptyInstance();
    	g2.add("a");
    	assertEquals(new HashMap<String, Integer>(),g2.sources("a"));
    }
	/*
	 * testStrategy:一个顶点是否具有终点划分测试。
	 */
    @Test
    public void testtargets()
    {
    	Graph<String> g = emptyInstance();
    	g.add("a");
    	g.add("b");
    	g.add("c");
    	g.set("a", "b", 1);
    	g.set("a", "c", 2);
    	Map<String,Integer> targets = new HashMap<>();
    	targets.put("b", 1);
    	targets.put("c",2);
    	assertEquals(targets,g.targets("a"));
    	Graph<String> g2 = emptyInstance();
    	g2.add("a");
    	assertEquals(new HashMap<String, Integer>(),g2.targets("a"));
    }

}	
