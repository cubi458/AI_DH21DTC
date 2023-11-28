package lab9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		// Enter your code here
		List<Node> successors = new ArrayList<>();

		for(int i = 0; i < data.size(); i++){
			for(int j = i + 1; j < data.size(); j++){
				List<Integer> childData = new ArrayList<>(data);
				int tokens1 = childData.get(i);
				int tokens2 = childData.get(j);

				if(tokens1 != tokens2){
					childData.remove(i);
					childData.remove(j - 1);
					childData.add(tokens1 + tokens2);

					Node childNode = new Node();
					childNode.addAll(childData);
					successors.add(childNode);
				}
			}
		}
		return successors;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		for(int tokens : this.data){
			if(tokens > 1){
				return false;
			}
		}
		return true;
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}
	public int minimax(int depth, boolean isMax){
		if(isTerminal()){
			return (isMax) ? 1 : 0;
		}
		if (isMax){
			int maxEval = Integer.MAX_VALUE;
			List<Node> successors = getSuccessors();
			for(Node child : successors){
				int eval = child.minimax(depth - 1, false);
				maxEval = Math.max(maxEval, eval);
			}
			return maxEval;
		}else{
			int minEval = Integer.MAX_VALUE;
			List<Node> successors = getSuccessors();
			for(Node child : successors){
				int eval = child.minimax(depth - 1, true);
				minEval = Math.min(minEval, eval);
			}
			return minEval;
		}
	}
	public Node bestMoveForMin(){
		List<Node> successors = getSuccessors();
		int bestEval = Integer.MAX_VALUE;
		Node bestMove = null;

		for (Node child : successors){
			int eval = child.minimax(Integer.MAX_VALUE, false);
			if(eval < bestEval){
				bestEval = eval;
				bestMove = child;
			}
		}
		return bestMove;
	}
}
