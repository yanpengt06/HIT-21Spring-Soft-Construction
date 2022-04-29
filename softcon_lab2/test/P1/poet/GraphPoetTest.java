/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
	/*
	 * testStrategy:桥接词>1个，=1个，0个，一个测试包含在两个中。
	 */
    @Test
    public void testpoem() throws IOException
    {
    	GraphPoet corpusGraph = new GraphPoet(new File("test/P1/poet/test1.txt"));
    	assertEquals("I will have a pleasant day.",corpusGraph.poem("I will have a day."));
    	GraphPoet corpusGraph2 = new GraphPoet(new File("test/P1/poet/test2.txt"));
    	assertEquals("This is a test.",corpusGraph2.poem("This is a test."));
    }
}
