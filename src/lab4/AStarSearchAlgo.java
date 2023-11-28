package lab4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStarSearchAlgo implements IInformedSearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        root.setG(0);
        root.setParent(null);

        Comparator fc = new NodeComparatorFactory().getFComarator();
        PriorityQueue<Node> frontier = new PriorityQueue<>(fc);
        Set<Node> explored = new HashSet<>();
        frontier.add(root);

        while (!frontier.isEmpty()) {
            System.out.println(frontier);
            Node node = frontier.poll();
            if (node.getLabel().equals(goal)) {
                return node;
            }
            explored.add(node);

            for(Edge edge : node.getChildren()){
                Node child = edge.getEnd();
                double newG = node.getG() + edge.getWeight();
                boolean inFrontier = frontier.contains(child);
                boolean inExplored = explored.contains(child);
                boolean shouldAddToFrontier = false; //Nếu shouldAdd = true thì thêm vô frontier
                if (child.getG() > newG){
                    //Trong trường hợp F(oldChild) > F(newChild) thì sẽ có 2 trường hợp xảy ra
                    if(inFrontier){
                        //TH1: oldChild trong frontier và newChild tốt hơn
                        //Thay thế oldChild = newChild
                        frontier.remove(child);
                        shouldAddToFrontier = true;
                    }
                    else if(inExplored){
                        //TH2: oldChild đã xét qua, nhưng newChild tốt hơn
                        //Thêm newChild vào lại frontier
                        shouldAddToFrontier = true;
                    }
                }
                else if(!inExplored && !inFrontier){
                    //Đây là TH child chưa được xét qua
                    shouldAddToFrontier = true;
                }
                // Suy ra các trường hợp sau sẽ không được bỏ vào frontier
                // (1): newChild tệ hơn oldChild trong frontier
                // (2): newChild tệ hơn oldChild trong explored

                if(shouldAddToFrontier){
                    child.setG(newG);
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

    // Lấy mọi Node có thể duyệt qua được từ root (Dùng BFS)
    private List<Node> getAllReachableNode(Node root){
        Queue<Node> queue = new LinkedList<>();
        Set<Node> explored = new HashSet<>();

        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            explored.add(node);

            for(Node child : node.getChildrenNodes()){
                if(!explored.contains(child)){
                    queue.add(child);
                }
            }
        }

        return explored.stream().toList();
    }

    public boolean isAdmissibleH(Node root, String goal){
        // h(n) được coi là admissible nếu:
        // Với mọi n, h(n) <= h*(n)
        // Vậy nên để kiểm chứng h(n) là admissble
        // thì phải lấy mọi Node trong graph (hoặc ít nhất là các Node có thể duyệt qua được)
        List<Node> nodes = getAllReachableNode(root);

        // Sử dụng Uninform bởi nó đảm bảo tính completeness và optimally
        UninformCostSearchAlgo searcher = new UninformCostSearchAlgo();
        for(Node node : nodes){
            Node goalNode = searcher.execute(node, goal);
//            System.out.println(
//                    "Node: " + node.getLabel() +
//                    ", Cost: " + goalNode.getG() +
//                            ", H:" + node.getH());
            if(goalNode != null && goalNode.getG() < node.getH()){
                return false;
            }
        }

        return true;
    }
}
