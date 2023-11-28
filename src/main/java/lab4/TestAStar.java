package lab4;

public class TestAStar {
	public static void main(String[] args) {
		Node s = createTestSet1();

		AStarSearchAlgo aStar = new AStarSearchAlgo();
		System.out.println(aStar.isAdmissibleH(s, "G"));
	}

	public static Node createTestSet1(){
		Node s = new Node("S", 6);
		Node b = new Node("B", 4);
		Node a = new Node("A", 4);
		Node c = new Node("C", 4);
		Node d = new Node("D", 3.5);
		Node e = new Node("E", 1);
		Node f = new Node("F", 1);
		Node g = new Node("G", 0);

		s.addEdge(b, 3);
		s.addEdge(a, 2);
		a.addEdge(c, 3);
		b.addEdge(d, 3);
		b.addEdge(c, 1);
		c.addEdge(e, 3);
		c.addEdge(d, 1);
		d.addEdge(f, 2);
		f.addEdge(g, 1);
		e.addEdge(g, 2);

		return s;
	}

	public static Node createTestSet3(){
		Node s = new Node("S", 500);
		Node a = new Node("A", 295);
		Node b = new Node("B", 499);
		Node c = new Node("C", 498);
		Node d = new Node("D", 290);
		Node e = new Node("E", 100);
		Node g = new Node("G", 0);

		s.addEdge(a, 5);
		s.addEdge(b, 1);
		a.addEdge(d, 5);
		b.addEdge(c, 1);
		c.addEdge(d, 1);
		d.addEdge(e, 5);
		e.addEdge(g, 999);

		return s;
	}
}
