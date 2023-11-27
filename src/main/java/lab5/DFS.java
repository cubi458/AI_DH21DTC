package lab5;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DFS extends AbstractSearch{
    public DFS() {
        super("DFS");
    }

    @Override
    public Node execute(Puzzle model) {
        Stack<Node> frontier = new Stack<>();
        Set<Node> explored = new HashSet<>();

        model.getInitialState().setParent(null);
        model.getInitialState().setG(0);
        frontier.add(model.getInitialState());

        int count = 0;
        while (!frontier.isEmpty()){
            Node currentState = frontier.pop();
            count++;
            if(count % 10000 == 0){
                System.out.println(count);
            }
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
