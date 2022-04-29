package P3.src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FriendshipGraph {

	private List<Person> persons;
	private List<ArrayList<Integer>> relationship;
	
	
	public FriendshipGraph()
	{
		persons = new ArrayList<Person>();
		relationship = new ArrayList<ArrayList<Integer>>();
	}
	
	
	
	public List<Person> getPersons() {
		return persons;
	}



	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}



	public List<ArrayList<Integer>> getRelationship() {
		return relationship;
	}



	public void setRelationship(List<ArrayList<Integer>> relationship) {
		this.relationship = relationship;
	}



	private void adjustScale()
	{
		relationship.add(new ArrayList<Integer>());
		for(int i = 0;i < persons.size()-1;i++)
		{
			relationship.get(i).add(0);
		}
		for(int i = 0;i < persons.size()-1;i++)
		{
				relationship.get(persons.size()-1).add(0);
		}
		relationship.get(persons.size()-1).add(0);			//无环图
	}
	
	public void addVertex(Person p)
	{
		for(int i = 0;i < persons.size();i++)
		{
			if(persons.get(i).getName().equals(p.getName()))
			{
				System.out.println("You have input two persons with the same name!!!");
				throw new RuntimeException("The graph doesn't work!");
			}	
		}
		persons.add(p);
		adjustScale();
	}
	public void addEdge(Person p1,Person p2 )
	{
		int i = 0,j = 0;
		i = personToInt(p1);
		j = personToInt(p2);
		relationship.get(i).set(j, 1);
		
	}
	
	public boolean isAcquaitant(Person p1,Person p2)
	{
		int i = 0;
		int j = 0;
		i = personToInt(p1);
		j = personToInt(p2);
		return relationship.get(i).get(j).equals(1);
	}
	
	public int getDistance(Person p1,Person p2)
	{
		int n = persons.size();
		int[] visited = new int[n];
		int i = personToInt(p1);
		int j = personToInt(p2);
		if(i==j)
			return 0;
		int length = 0;
		int dis = 0;
		int head;
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		visited[i] = 1;
		while(!q.isEmpty())
		{
			length = q.size();
			dis++;
			for(int k = 1;k <= length;k++)
			{
				head = q.remove();
				for(int h = 0;h < persons.size();h++)
				{
					if(relationship.get(head).get(h).equals(1))	//与head认识
					{
						if(h == j)
							return dis;
						if(visited[h]==0)
						{
							visited[h] = 1;
							q.add(h);
						}
					}
				}
			}
		}
		return -1;
	}
	
	private int personToInt(Person p)
	{
		for(int i = 0;i < persons.size();i++)
		{
			if(persons.get(i).getName().equals(p.getName()))
				return i;
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
//		System.out.println(graph.isAcquaitant(rachel,ross));
//		System.out.println(graph.isAcquaitant(ben,ross));
//		System.out.println(graph.isAcquaitant(kramer, ben));
//		System.out.println(graph.isAcquaitant(kramer, rachel));
		System.out.println(graph.getDistance(rachel, ross));
		//should print 1
		System.out.println(graph.getDistance(rachel, ben));
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		//should print -1
		System.out.println(graph.getDistance(kramer, bob));
		//should print 2
		System.out.println(graph.getDistance(ross, alice));
		//should print -1
		
	}

}
