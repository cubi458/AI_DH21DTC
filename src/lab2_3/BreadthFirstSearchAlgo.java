package lab2_3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearchAlgo implements ISearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        root.setPathCost(0);
        root.setParent(null);

        Queue<Node> frontier = new LinkedList<Node>();
        Set<Node> explored = new HashSet<Node>();
        frontier.add(root);

        while(!frontier.isEmpty()){
            Node node = frontier.poll();
            if(node.getLabel().equals(goal)){
                return node;
            }
            explored.add(node);

            for(Node child : node.getChildrenNodes()){
                if(!explored.contains(child)){
                    child.setParent(node);
                    frontier.add(child);
                }
            }
        }

        return null;
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
