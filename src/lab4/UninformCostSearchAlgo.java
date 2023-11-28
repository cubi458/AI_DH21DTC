package lab4;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UninformCostSearchAlgo {
    public Node execute(Node root, String goal) {
        root.setG(0);
        root.setParent(null);

        Comparator<Node> gc = new NodeComparatorFactory().getGComarator();
        PriorityQueue<Node> frontier = new PriorityQueue<>(gc);
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            if (node.getLabel().equals(goal)) {
                return node;
            }

            for (Edge childEdge : node.getChildren()) {
                Node childNode = childEdge.getEnd();
                double newCost = node.getG() + childEdge.getWeight();
                if (frontier.contains(childNode) && childNode.getG() > newCost) {
                    frontier.remove(childNode);
                }
                if (!frontier.contains(childNode)) {
                    childNode.setParent(node);
                    childNode.setG(newCost);
                    frontier.add(childNode);
                }
            }
        }

        return null;
    }
}
