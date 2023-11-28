package lab2_3;

import java.util.LinkedList;
import java.util.Queue;

public class BFSTreeSearch implements ISearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        try{
            root.setPathCost(0);
            root.setParent(null);

            Queue<Node> frontier = new LinkedList<Node>();
            frontier.add(root);

            while(!frontier.isEmpty()){
                Node node = frontier.poll();
                if(node.getLabel().equals(goal)){
                    return node;
                }

                for(Node child : node.getChildrenNodes()){
                    Node clone = (Node) child.clone();
                    clone.setParent(node);
                    frontier.add(clone);
                }
            }

            return null;
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Node startNode = execute(root, start);
        if(startNode != null){
            return execute(startNode, goal);
        }

        return null;
    }
}
