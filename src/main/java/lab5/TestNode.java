package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TestNode {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("src/lab5/asset/PuzzleMap.txt",
				"src/lab5/asset/PuzzleGoalState.txt");
		IPuzzleAlgo solver = new GreedyBestFirstSearch();
		Node goalNode = solver.execute(p);

		Stack<Node> stack = new Stack<>();
		Node trace = goalNode;
		while(trace != null){
			stack.add(trace);
			trace = trace.getParent();
		}


		while (!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}
}
