/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   TODO
//    graph就是一个语料库转换而来的有向图，就是一个GraphPoet，故AF为自身到自身的一个映射。
    // Representation invariant:
    //   TODO
//    graph中的每个顶点满足大小写不敏感、每个word无空行空字符等等条件，以及权满足上述注释的定义。
    // Safety from rep exposure:
    //   TODO
//    private具有一定的安全性，由于graph是可变类型，需要注意防御式拷贝。
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	BufferedReader toread=new BufferedReader(new FileReader(corpus));
    	String line;
    	String[] words;
    	List<String> wordlist=new ArrayList<>();
    	while((line=toread.readLine())!=null) {  	//将文件一行字符存入list中
    		line.replace(".", " ");
    		words=line.split(" ");
    		wordlist.addAll(Arrays.asList(words));
    	}
    	toread.close();
    	for(int i=0;i<wordlist.size()-1;i++) {
    		String from=wordlist.get(i).toLowerCase();  //变为小写String
    		String to=wordlist.get(i+1).toLowerCase();
    		int lastWeight  = 0;
    		lastWeight = graph.set(from, to, 1);   //graph中加入所有点边
    		if(lastWeight != 0)
    		{
    			graph.set(from, to, lastWeight+1);
    		}
    	}
    	checkRep();
    }
    
    // TODO checkRep
    private void checkRep()
    {
    	Set<String> vertices = new HashSet<>();
    	vertices = graph.vertices();
    	for(String v : vertices)
    	{
    		assert(v!=null);
    		assert(!v.contains(" "));
    		assert(!v.contains("\n"));
    	}
    }
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	String[] input_words = null;
    	String from = "",to = "";
    	Set<String> intersection;
    	String result = "";
    	Map<String, Integer> targets,sources;
    	String new_input = input.replace(".", " ");
    	input_words = new_input.split(" ");
    	for(int i = 0;i < input_words.length-1;i++)
    	{
    		 from = input_words[i].toLowerCase();
    		 to = input_words[i+1].toLowerCase();
    		 targets = graph.targets(from);
    		 sources = graph.sources(to);
    		 intersection = sources.keySet();
    		 intersection.retainAll(targets.keySet());
    		 if(!intersection.isEmpty())
    		 {
    			 String bridge = "";
    			 int maxValue = Integer.MIN_VALUE;
    			 for(String word: intersection)
    			 {
    				 if(targets.get(word)+sources.get(word) > maxValue)
    					 {
    					 	maxValue = targets.get(word)+sources.get(word);
    					 	bridge = word;
    					 }
    			 }
    			 input_words[i] = input_words[i]+ " "+bridge;
    		 }
    	}
    	for(int i = 0;i < input_words.length;i++)
    	{
    		result += input_words[i];
    		result += " ";
    	}
    	result = result.stripTrailing();
    	result += ".";
    	return result;
    }
    
    // TODO toString()
    @Override 
    public String toString()
    {
    	return "this GraphPoet has a HashCode of " + this.hashCode();
    }
}
