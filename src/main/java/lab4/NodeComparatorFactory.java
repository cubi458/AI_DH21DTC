package lab4;

import java.util.Comparator;

public class NodeComparatorFactory {
    public Comparator<Node> getFComarator(){
        return new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                double result = o1.getF() - o2.getF();
                if (result == 0) {
                    return o1.getLabel().compareTo(o2.getLabel());
                } else {
                    return (result > 0) ? (1) : (-1);
                }
            }
        };
    }

    public Comparator<Node> getHComarator(){
        return new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                double result = o1.getH() - o2.getH();
                if (result == 0) {
                    return o1.getLabel().compareTo(o2.getLabel());
                } else {
                    return (result > 0) ? (1) : (-1);
                }
            }
        };
    }

    public Comparator<Node> getGComarator(){
        return new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                double result = o1.getG() - o2.getG();
                if (result == 0) {
                    return o1.getLabel().compareTo(o2.getLabel());
                } else {
                    return (result > 0) ? (1) : (-1);
                }
            }
        };
    }
}
