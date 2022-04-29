package P2;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {

	
	@Test
	/*
	 * testStrategy:暂不考虑重名的社交网络，顶点是否在图中进行测试。
	 */
	public void testaddVertex() {
		FriendshipGraph graph = new FriendshipGraph();
		Person alice =  new Person("Alice");
		Person bob = new Person("Bob");
		assertEquals(true,graph.addVertex(alice));
		assertEquals(true,graph.addVertex(bob));
		assertEquals(false,graph.addVertex(bob));
	}
	@Test
	/*
	 * testStrategy:按照边是否已经在图中进行分类测试。
	 */
	public void testaddEdge()
	{
		FriendshipGraph graph = new FriendshipGraph();
		Person alice =  new Person("Alice");
		Person bob = new Person("Bob");
		Person charlie = new Person("Charlie");
		graph.addVertex(alice);
		graph.addVertex(bob);
		graph.addVertex(charlie);
		assertEquals(true,graph.addEdge(alice, bob));
		assertEquals(true,graph.addEdge(bob, alice));
		assertEquals(false,graph.addEdge(alice, bob));
	}
	@Test
	/*
	 * testStrategy:按照两个点连通，不连通，两个点为同一个点进行测试。
	 */
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
