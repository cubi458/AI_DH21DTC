package lab2_3;

import java.util.Comparator;

public class NodeComparatorFactory {
    public Comparator<Node> getPathCostComparator(){
        return new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                double result = o1.getPathCost() - o2.getPathCost();
                if (result == 0) {
                    return o1.getLabel().compareTo(o2.getLabel());
                } else {
                    return (result > 0) ? (1) : (-1);
                }
            }
        };
    }
}
