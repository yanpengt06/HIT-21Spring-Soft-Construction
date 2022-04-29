package P2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import P1.graph.ConcreteEdgesGraph;
import P1.graph.Graph;

public class FriendshipGraph {

	private final Graph<Person> fsGraph = new ConcreteEdgesGraph<>();
	
	public FriendshipGraph()
	{
	}
/**
 * 	@return 已存在返回flase，否则true
*/	
	public boolean addVertex(Person p)
	{
		return fsGraph.add(p);
	}

	/**
	 * @return 已存在返回false，否则true
	 */
	public boolean addEdge(Person p1,Person p2 )
	{
		 if(fsGraph.set(p1, p2, 1)==0)
			 return true;
		 else return false;
	}

	/**
	 * @param p1,p2为图上两个人。
	 * 
	 * @return p1与p2之间的距离，若二者不连通，则返回-1，若p1==p2，返回0.
	 */
	public int getDistance(Person p1,Person p2)
	{
		Set<Person> visited = new HashSet<>();
		int dis = 0;
		if(p1.getName() == p2.getName())
			return 0;
		Queue<Person> q = new LinkedList<>();
		q.add(p1);
		visited.add(p1);
		while(!q.isEmpty())
		{
			int length = q.size();
			dis++;
			for(int i = 0;i < length;i++)
			{
				Person head = q.remove();
				for(Person p :fsGraph.targets(head).keySet())
				{
					if(!visited.contains(p))
					{
						if(p.getName().equals(p2.getName()))
						{
							return dis;
						}
						else
						{
							q.add(p);
							visited.add(p);
						}
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		FriendshipGraph graph = new FriendshipGraph();
//		Person rachel = new Person("Rachel");
//		Person ross = new Person("Ross");
//		Person ben = new Person("Ben");
//		Person kramer = new Person("Kramer");
//		Person alice = new Person("Alice");
//		Person bob = new Person("Bob");
//		graph.addVertex(rachel);
//		graph.addVertex(ross);
//		graph.addVertex(ben);
//		graph.addVertex(kramer);
//		graph.addVertex(alice);
//		graph.addVertex(bob);
//		graph.addEdge(rachel, ross);
//		graph.addEdge(ross, rachel);
//		graph.addEdge(ross, ben);
//		graph.addEdge(ben, ross);
//		graph.addEdge(alice, kramer);
//		graph.addEdge(kramer, alice);
//		graph.addEdge(alice, bob);
//		graph.addEdge(bob, alice);
////		System.out.println(graph.isAcquaitant(rachel,ross));
////		System.out.println(graph.isAcquaitant(ben,ross));
////		System.out.println(graph.isAcquaitant(kramer, ben));
////		System.out.println(graph.isAcquaitant(kramer, rachel));
//		System.out.println(graph.getDistance(rachel, ross));
//		//should print 1
//		System.out.println(graph.getDistance(rachel, ben));
//		//should print 2
//		System.out.println(graph.getDistance(rachel, rachel));
//		//should print 0
//		System.out.println(graph.getDistance(rachel, kramer));
//		//should print -1
//		System.out.println(graph.getDistance(kramer, bob));
//		//should print 2
//		System.out.println(graph.getDistance(ross, alice));
//		//should print -1

	}

}
