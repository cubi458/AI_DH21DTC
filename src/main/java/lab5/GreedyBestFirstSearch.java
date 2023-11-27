package lab5;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class GreedyBestFirstSearch extends AbstractSearch{

    public GreedyBestFirstSearch() {
        super("Greedy");
    }

    @Override
    public Node execute(Puzzle model) {
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
        Set<Node> explored = new HashSet<>();

        model.getInitialState().setParent(null);
        model.getInitialState().setG(0);
        frontier.add(model.getInitialState());

        while (!frontier.isEmpty()){
            Node currentState = frontier.poll();
            if(currentState.equals(model.getGoalState())){
                return currentState;
            }
            explored.add(currentState);

            for(Node successor : model.getSuccessors(currentState)){
                if(!explored.contains(successor) && !frontier.contains(successor)){
                    successor.setParent(currentState); //Dùng cho việc truy vết
                    successor.setG(currentState.getG() + 1);
                    frontier.add(successor);
                }
            }
        }

        return null;
    }
}
