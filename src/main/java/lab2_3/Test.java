package lab2_3;

import junit.framework.TestCase;

/**
 * Hãy chạy từng test case để xem kết quả
 */
public class Test extends TestCase {
    public void testDFSGraphSearch(){
        ISearchAlgo dfsGraphSearch = new DepthFirstSearchAlgo();

        Node nodeS = createTestSet2(); //Có thể thay bằng createTestSet1()
        Node result = dfsGraphSearch.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));

        result = dfsGraphSearch.execute(nodeS, "A", "G");
        System.out.println(NodeUtils.printPath(result));

        result = dfsGraphSearch.execute(nodeS, "E", "H");
        System.out.println(NodeUtils.printPath(result));
    }

    public void testBFSGraphSearch(){
        ISearchAlgo bfsGraphSearch = new BreadthFirstSearchAlgo();

        Node nodeS = createTestSet2(); //Có thể thay bằng createTestSet1()
        Node result = bfsGraphSearch.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));

        result = bfsGraphSearch.execute(nodeS, "A", "G");
        System.out.println(NodeUtils.printPath(result));

        result = bfsGraphSearch.execute(nodeS, "E", "H");
        System.out.println(NodeUtils.printPath(result));
    }

    public void testDFSTreeSearch(){
        ISearchAlgo dfsTreeSearch = new DFSTreeSearch();

        Node nodeS = createTestSet1(); //Có thể thay bằng createTestSet2()
        Node result = dfsTreeSearch.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));

        result = dfsTreeSearch.execute(nodeS, "A", "G");
        System.out.println(NodeUtils.printPath(result));

        result = dfsTreeSearch.execute(nodeS, "E", "H");
        System.out.println(NodeUtils.printPath(result));
    }

    public void testBFSTreeSearch(){
        ISearchAlgo bfsTreeSearch = new BFSTreeSearch();

        Node nodeS = createTestSet1(); //Có thể thay bằng createTestSet2()
        Node result = bfsTreeSearch.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));

        result = bfsTreeSearch.execute(nodeS, "A", "G");
        System.out.println(NodeUtils.printPath(result));

        result = bfsTreeSearch.execute(nodeS, "E", "H");
        System.out.println(NodeUtils.printPath(result));
    }

    public void testUninformCostSearch(){
        ISearchAlgo uninformCostSearch = new UninformCostSearchAlgo();

        Node nodeS = createTestSet2(); //Có thể thay bằng createTestSet1()
        Node result = uninformCostSearch.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));
        System.out.println(result.getPathCost());

        result = uninformCostSearch.execute(nodeS, "A", "G");
        System.out.println(NodeUtils.printPath(result));
        System.out.println((result != null) ? (result.getPathCost()):(0));

        result = uninformCostSearch.execute(nodeS, "E", "H");
        System.out.println(NodeUtils.printPath(result));
        System.out.println((result != null) ? (result.getPathCost()):(0));
    }

    public void testDepthLimitedSearch(){
        ISearchAlgo depthLimit2 = new DepthLimitedSearch(2);

        Node nodeS = createTestSet1(); //Có thể thay bằng createTestSet2()
        Node result = depthLimit2.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));

        result = depthLimit2.execute(nodeS, "A", "G");
        System.out.println(NodeUtils.printPath(result));

        result = depthLimit2.execute(nodeS, "E", "H");
        System.out.println(NodeUtils.printPath(result));
    }


    public static Node createTestSet1(){
        Node nodeS = new Node("S");
        Node nodeA = new Node("A"); Node nodeB = new Node("B");
        Node nodeC = new Node("C"); Node nodeD = new Node("D");
        Node nodeE = new Node("E"); Node nodeF = new Node("F");
        Node nodeG = new Node("G"); Node nodeH = new Node("H");
        nodeS.addEdge(nodeA, 5); nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4); nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4); nodeB.addEdge(nodeG, 6);
        nodeC.addEdge(nodeF, 2); nodeD.addEdge(nodeH, 7);
        nodeE.addEdge(nodeG, 6); nodeF.addEdge(nodeG, 1);

        return nodeS;
    }

    public static Node createTestSet2(){
        Node nodeStart = new Node("S");
        Node nodeA = new Node("A"); Node nodeB = new Node("B");
        Node nodeC = new Node("C"); Node nodeD = new Node("D");
        Node nodeE = new Node("E"); Node nodeF = new Node("F");
        Node nodeH = new Node("H"); Node nodeP = new Node("P");
        Node nodeQ = new Node("Q"); Node nodeR = new Node("R");
        Node nodeGoal = new Node("G");

        nodeStart.addEdge(nodeD, 3); nodeStart.addEdge(nodeE, 9); nodeStart.addEdge(nodeP, 1);
        nodeB.addEdge(nodeA, 2);
        nodeC.addEdge(nodeA, 2);
        nodeD.addEdge(nodeB, 1); nodeD.addEdge(nodeC, 8); nodeD.addEdge(nodeE, 2);
        nodeE.addEdge(nodeH, 1); nodeE.addEdge(nodeR, 9);
        nodeF.addEdge(nodeC, 5); nodeF.addEdge(nodeGoal, 5);
        nodeH.addEdge(nodeP, 4); nodeH.addEdge(nodeQ, 4);
        nodeP.addEdge(nodeQ, 15);
        nodeQ.addEdge(nodeR, 3);
        nodeR.addEdge(nodeF, 5);

        return nodeStart;
    }
}
