package lab2_3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        root.setPathCost(0);
        root.setParent(null);

        Stack<Node> frontier = new Stack<Node>();
        Set<Node> explored = new HashSet<Node>();
        frontier.add(root);

        while(!frontier.isEmpty()){
            Node node = frontier.pop();
            if(node.getLabel().equals(goal)){
                return node;
            }
            explored.add(node);

            List<Node> childs = node.getChildrenNodes();
            for(int i = childs.size() - 1; i >= 0; i--){
                Node child = childs.get(i);
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
