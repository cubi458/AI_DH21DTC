package lab2_3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthLimitedSearch implements ISearchAlgo{
    private int depth;

    public DepthLimitedSearch(int depth){
        this.depth = depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

    @Override
    public Node execute(Node root, String goal) {
        root.setPathCost(0);
        root.setParent(null);
        root.setDepth(0);

        Stack<Node> frontier = new Stack<Node>();
        Set<Node> explored = new HashSet<Node>();
        frontier.add(root);

        while(!frontier.isEmpty()){
            Node node = frontier.pop();
            if(node.getDepth() <= depth){
                if(node.getLabel().equals(goal)){
                    return node;
                }
                explored.add(node);

                List<Node> childs = node.getChildrenNodes();
                for(int i = childs.size() - 1; i >= 0; i--){
                    Node child = childs.get(i);
                    if(!explored.contains(child)){
                        child.setParent(node);
                        child.setDepth(node.getDepth() + 1);
                        frontier.add(child);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        int depth = this.depth;
        setDepth(Integer.MAX_VALUE);
        Node startNode = execute(root, start);
        if(startNode != null){
            setDepth(depth);
            return execute(startNode, goal);
        }

        return null;
    }
}
