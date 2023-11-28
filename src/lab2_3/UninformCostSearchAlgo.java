package lab2_3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UninformCostSearchAlgo implements ISearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        root.setPathCost(0);
        root.setParent(null);
        Comparator<Node> comparator = new NodeComparatorFactory().getPathCostComparator();
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(comparator);
        frontier.add(root);

        while(!frontier.isEmpty()){
            Node node = frontier.poll();
            if(node.getLabel().equals(goal)){
                return node;
            }

            for(Edge childEdge : node.getChildren()){
                Node childNode = childEdge.getEnd();
                double newCost = node.getPathCost() + childEdge.getWeight();
                if(frontier.contains(childNode) && childNode.getPathCost() > newCost){
                    frontier.remove(childNode);
                }
                if (!frontier.contains(childNode)){
                    childNode.setParent(node);
                    childNode.setPathCost(newCost);
                    frontier.add(childNode);
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
