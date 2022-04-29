package P3.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import P3.src.FriendshipGraph;
import P3.src.Person;

public class FriendshipGraphTest {

	
	@Test

	public void testaddVertex() {
		FriendshipGraph graph = new FriendshipGraph();
		List<Person> persons = graph.getPersons();
		Person alice =  new Person("Alice");
		Person bob = new Person("Bob");
		graph.addVertex(alice);
		graph.addVertex(bob);
		assertEquals(alice,persons.get(0));
		assertEquals(bob,persons.get(1));
	}
	@Test
	
	public void testaddEdge()
	{
		FriendshipGraph graph = new FriendshipGraph();
		Person alice =  new Person("Alice");
		Person bob = new Person("Bob");
		Person charlie = new Person("Charlie");
		List<ArrayList<Integer>> relationship = graph.getRelationship();
		graph.addVertex(alice);
		graph.addVertex(bob);
		graph.addVertex(charlie);
		graph.addEdge(alice, bob);
		graph.addEdge(bob, alice);
		assertEquals(true,1==relationship.get(0).get(1));
		assertEquals(true,1==relationship.get(1).get(0));
		assertEquals(true,0==relationship.get(0).get(0));
		assertEquals(true,0==relationship.get(1).get(1));
		assertEquals(true,0==relationship.get(0).get(2));
		assertEquals(true,0==relationship.get(2).get(0));

	}
	@Test
	
	public void testgetDistance()
	{
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person alice = new Person("Alice");
		Person bob = new Person("Bob");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addVertex(alice);
		graph.addVertex(bob);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		graph.addEdge(alice, kramer);
		graph.addEdge(kramer, alice);
		graph.addEdge(alice, bob);
		graph.addEdge(bob, alice);
		assertEquals(1,graph.getDistance(rachel, ross));
		assertEquals(2,graph.getDistance(rachel, ben));
		assertEquals(0,graph.getDistance(rachel, rachel));
		assertEquals(-1,graph.getDistance(rachel, alice));
		assertEquals(2,graph.getDistance(kramer, bob));
	}
}
