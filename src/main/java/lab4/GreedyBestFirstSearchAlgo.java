package lab4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        root.setG(0);
        root.setParent(null);

        Comparator comparator = new NodeComparatorFactory().getHComarator();
        PriorityQueue<Node> frontier = new PriorityQueue<>(comparator);
        Set<Node> explore = new HashSet<>();
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            if (node.getLabel().equals(goal)) {
                return node;
            }
            explore.add(node);
            for(Node child : node.getChildrenNodes()){
                if(!frontier.contains(child) && !explore.contains(child)){
                    child.setParent(node);
                    frontier.add(child);
                }
            }
        }

        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Node s = execute(root, start);
        if(start != null){
            return execute(s, goal);
        }
        return null;
    }
}
