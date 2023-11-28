package lab5;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class HillClimbing extends AbstractSearch{
    public HillClimbing() {
        super("Hill Climbing");
    }

    @Override
    public Node execute(Puzzle model) {
        Node currentNode = model.getInitialState();

        model.getInitialState().setParent(null);
        model.getInitialState().setG(0);

        while (currentNode != null){
            if(currentNode.equals(model.getGoalState())){
                return currentNode;
            }

            Node parent = currentNode;
            boolean canMove = false;
            for(Node successor : model.getSuccessors(currentNode)){
                if(currentNode.getH() > successor.getH()){
                    successor.setG(parent.getG() + 1); // Set G
                    successor.setParent(parent); //Dùng cho việc truy vết
                    currentNode = successor;
                    canMove = true;
                }
            }
            if(!canMove){
                return null;
            }
        }

        return null;
    }
}
